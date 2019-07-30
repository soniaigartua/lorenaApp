package com.example.pps_tudai.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.pps_tudai.R;
import com.example.pps_tudai.data.entities.User;
import com.example.pps_tudai.mvp.model.LoginModel;
import com.example.pps_tudai.mvp.model.RegistrationModel;
import com.example.pps_tudai.mvp.presenter.LoginPresenter;
import com.example.pps_tudai.mvp.presenter.RegistrationPresenter;
import com.example.pps_tudai.mvp.view.LoginView;
import com.example.pps_tudai.mvp.view.RegistrationView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

    private RegistrationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        RegistrationView view = new RegistrationView(this);
        RegistrationModel model = new RegistrationModel();
        presenter = new RegistrationPresenter(model,view);
    }

    @OnClick(R.id.btn_enter_register)
    public void btnRegisterClicked() {
        presenter.onRegisterPressed();
    }

    @OnClick(R.id.btn_cancel_register)
    public void btnCancelRegisterClicked() {
        presenter.onCancelPressed();
    }
}
