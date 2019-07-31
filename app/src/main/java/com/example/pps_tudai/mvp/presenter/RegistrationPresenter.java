package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.RegistrationModel;
import com.example.pps_tudai.mvp.view.RegistrationView;
import com.example.pps_tudai.utils.Validator;

public class RegistrationPresenter {


    private final RegistrationModel registerModel;
    private final RegistrationView registerView;
    private final Validator validator = new Validator();

    public RegistrationPresenter(RegistrationModel registerModel, RegistrationView registerView) {
        this.registerModel = registerModel;
        this.registerView = registerView;
    }

    public void onRegisterPressed() {
        String name = registerView.getUserName();
        String surname = registerView.getUserSurname();
        String email = registerView.getUserEmail();
        String password = registerView.getPassword();
        String password_repeat = registerView.getPasswordRepeat();
        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || password_repeat.isEmpty()) {
            registerView.showDataEmptyScreen();
        }
        if (!validator.isValid(email)) {
            registerView.showErrorEmailRegistrationScreen();
        }
        if (!password.equals(password_repeat)) {
            registerView.showErrorPasswordRegistrationScreen();
        }
        else {
            registerModel.registerUser(name, surname, email, password);
            registerView.showRegistrationScreenOK();
        }
    }

    public void onCancelPressed() {
        registerView.cancelRegister();
    }
}
