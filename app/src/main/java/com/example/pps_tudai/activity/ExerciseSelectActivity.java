package com.example.pps_tudai.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pps_tudai.R;
import com.example.pps_tudai.mvp.model.ExerciseSelectModel;
import com.example.pps_tudai.mvp.presenter.ExerciseSelectPresenter;
import com.example.pps_tudai.mvp.view.ExerciseSelectView;
import com.example.pps_tudai.services.exerciseService.ExerciseServiceGenerator;
import com.example.pps_tudai.services.exerciseService.ExerciseServiceCall;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExerciseSelectActivity extends AppCompatActivity {

    private ExerciseSelectPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_select);

        init();
    }

    public void init () {
        ButterKnife.bind(this);
        ExerciseSelectView view = new ExerciseSelectView(this);
        ExerciseSelectModel model = new ExerciseSelectModel(ExerciseServiceGenerator.createService(ExerciseServiceCall.class));
        presenter = new ExerciseSelectPresenter(view, model);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.registerBus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregisterBus(this);
    }

    @OnClick(R.id.btn_return)
    public void btnSaveChangesClicked() {
        presenter.onReturnPressed();
    }
}
