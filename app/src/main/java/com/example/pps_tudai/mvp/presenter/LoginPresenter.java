package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.LoginModel;
import com.example.pps_tudai.mvp.view.LoginView;
import com.example.pps_tudai.utils.Validator;

public class LoginPresenter {

    private final LoginModel loginModel;
    private final LoginView loginView;

    public LoginPresenter(LoginModel loginModel, LoginView loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
    }

    public void onLoginPressed() {
        String email = loginView.getUserEmail();
        String password = loginView.getPassword();

        if (email.isEmpty() || password.isEmpty()) {
            loginView.showDataEmptyScreen();
        }
        if (Validator.isValid(email)) {
            if (loginModel.checkEmailRegistered(email)) {
                if (loginModel.validateUserByEmailAndPassword(email, password)) {
                    loginView.showWelcomeScreen();
                } else {
                    loginView.showOnAuthenticationErrorMessage();
                }

            }
            else {
                loginView.showNoRegistrationMessage();
            }

        } else {
            loginView.showOnEmailErrorMessage();
        }
    }

    public void onCancelPressed() {
        loginView.cancelLogin();
    }
}
