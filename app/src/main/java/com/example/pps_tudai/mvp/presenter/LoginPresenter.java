package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.LoginModel;
import com.example.pps_tudai.mvp.view.LoginView;
import com.example.pps_tudai.utils.Tools;

public class LoginPresenter {

    private final LoginModel loginModel;
    private final LoginView loginView;
    private int userId;

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
        if (Tools.isValid(email)) {
            if (loginModel.checkEmailRegistered(email)) {
                if (loginModel.validateUserByEmailAndPassword(email, password)) {
                    userId = loginModel.getUserId(email, password);
                    loginView.showWelcomeScreen(userId);
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
