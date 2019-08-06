package com.example.pps_tudai.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pps_tudai.R;
import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.AppRoomDataBase;
import com.example.pps_tudai.mvp.model.RegistrationModel;
import com.example.pps_tudai.mvp.presenter.RegistrationPresenter;
import com.example.pps_tudai.mvp.view.RegistrationView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

    private RegistrationPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        RegistrationView registerView = new RegistrationView(this);
        RegistrationModel registerModel = new RegistrationModel(new AppRepository(AppRoomDataBase.getDatabase(this).userDao()));
        registerPresenter = new RegistrationPresenter(registerModel,registerView);
    }

    @OnClick(R.id.btn_enter_register)
    public void btnRegisterClicked() {
        registerPresenter.onRegisterPressed();
    }

    @OnClick(R.id.btn_cancel_register)
    public void btnCancelRegisterClicked() {
        registerPresenter.onCancelPressed();
    }
}
