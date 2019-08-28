package com.example.pps_tudai.mvp.presenter;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

import com.example.pps_tudai.activity.ExerciseSelectActivity;
import com.example.pps_tudai.adapter.ExerciseAdapter;
import com.example.pps_tudai.bus.CancelExerciseButtonDialogObserver;
import com.example.pps_tudai.bus.DetailsExerciseRequestObserver;
import com.example.pps_tudai.bus.RxBus;
import com.example.pps_tudai.mvp.model.ExerciseSelectModel;
import com.example.pps_tudai.mvp.view.ExerciseSelectView;
import com.example.pps_tudai.services.exerciseService.ExerciseAPIResponse;
import com.example.pps_tudai.services.exerciseService.Result;
import com.example.pps_tudai.utils.DialogUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pps_tudai.utils.IntUtils.EXERCISE_QUANTITY;
import static com.example.pps_tudai.utils.StringUtils.FORMAT_CALL_EXERCISE;

public class ExerciseSelectPresenter {

    private final ExerciseSelectView exerciseView;
    private final ExerciseSelectModel exerciseModel;
    private List<Result> exercisesData;
    private Call<ExerciseAPIResponse> exerciseCall;

    public ExerciseSelectPresenter(ExerciseSelectView exerciseView, ExerciseSelectModel exerciseModel) {
        this.exerciseView = exerciseView;
        this.exerciseModel = exerciseModel;
        callExerciseService();
    }

    public void callExerciseService() {
        exerciseView.showLoader();
        exerciseCall = exerciseModel.getWorkoutDataFromService(EXERCISE_QUANTITY, FORMAT_CALL_EXERCISE);
        exerciseCall.enqueue(new Callback<ExerciseAPIResponse>() {
            @Override
            public void onResponse(Call<ExerciseAPIResponse> call, Response<ExerciseAPIResponse> response) {
                if (!response.isSuccessful()) {
                    exerciseView.showContactAPINotSuccessful(String.valueOf(response.code()));
                    exerciseView.hideLoader();
                } else {
                    exercisesData = response.body().getResults();
                    exerciseView.setAdapter(new ExerciseAdapter(exercisesData));
                    exerciseView.hideLoader();
                }
            }

            @Override
            public void onFailure(Call<ExerciseAPIResponse> call, Throwable t) {
                exerciseView.showContactAPIFailure(t.getMessage());
                exerciseView.hideLoader();
            }
        });
    }

    public void onReturnPressed() {
        exerciseView.returnWelcomeActivity();
    }

    public void showExerciseDialog(Result exercise) {
        Activity activity = exerciseView.getActivity();

        if (activity != null) {
            DialogUtils.exerciseDialog(activity, exercise);
        }
    }

    public void onCancelExerciseButtonDialogPressed(AlertDialog dialog) {
        dialog.dismiss();
    }

    public void registerBus() {
        Activity activity = exerciseView.getActivity();

        if (activity != null) {
            RxBus.subscribe(activity, new DetailsExerciseRequestObserver() {
                @Override
                public void onEvent(DetailsExerciseRequestPressed value) {
                    showExerciseDialog(value.getExercise());
                }
            });

            RxBus.subscribe(activity, new CancelExerciseButtonDialogObserver() {
                @Override
                public void onEvent(CancelExerciseButtonPressed value) {
                    onCancelExerciseButtonDialogPressed(value.getDialog());
                }
            });
        }
    }

    public void unregisterBus(ExerciseSelectActivity exerciseSelectActivity) {
        RxBus.clear(exerciseSelectActivity);
    }
}
