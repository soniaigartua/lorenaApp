package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.RegistrationModel;
import com.example.pps_tudai.mvp.view.RegistrationView;

public class RegistrationPresenter {


    private final RegistrationModel registerModel;
    private final RegistrationView registerView;

    public RegistrationPresenter(RegistrationModel registerModel, RegistrationView registerView) {
        this.registerModel = registerModel;
        this.registerView = registerView;
    }

    public void onRegisterPressed() {
        String name = registerView.getUserName();
        String surname = registerView.getUserSurname();
        String email = registerView.getUserEmail();
        String password = registerView.getPassword();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            registerView.showDataEmptyScreen();
        }
        else {
            registerView.showRegistrationScreenOK();
        }
    }

    public void onCancelPressed() {
        registerView.cancelRegister();
    }

}
