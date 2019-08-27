package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pps_tudai.R;
import com.example.pps_tudai.activity.WelcomeActivity;
import com.example.pps_tudai.adapter.ExerciseAdapter;
import com.example.pps_tudai.services.exerciseService.ExerciseAPIResponse;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseSelectView {

    // activity should never be exposed publicly
    private WeakReference<Activity> activityWeak;
    @BindView(R.id.exercise_sign)
    TextView exercise_sign;
    @BindView(R.id.recycler_view_exercises)
    RecyclerView exerciseListRecycler;
    private LinearLayoutManager layoutManager;
    private ExerciseAdapter adapterRecycler;

    public ExerciseSelectView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
        init();
    }

    public void init() {
        layoutManager = new LinearLayoutManager(getActivity());
        exerciseListRecycler.setLayoutManager(layoutManager);
        exerciseListRecycler.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL));
        adapterRecycler = new ExerciseAdapter(new ArrayList<ExerciseAPIResponse.Result>());
        exerciseListRecycler.setAdapter(adapterRecycler);
        exerciseListRecycler.invalidate();
    }

    public void setAdapter(ExerciseAdapter adapter) {
        exerciseListRecycler.setAdapter(adapter);
        exerciseListRecycler.invalidate();
    }

    public Activity getActivity() {
        return activityWeak.get();
    }

    public void showContactAPIError() {
        Toast.makeText(this.getActivity(), R.string.error_contact_api_data, Toast.LENGTH_LONG).show();
    }

    public void showContactAPINotSuccessful(String code) {
        exercise_sign.setText(code);
    }

    public void showContactAPIFailure(String message) {
        exercise_sign.setText(message);
    }

    public void returnWelcomeActivity() {
        if (activityWeak.get() != null) {
            Intent welcome = new Intent(activityWeak.get(), WelcomeActivity.class);
            activityWeak.get().startActivity(welcome);
        }
    }
}
