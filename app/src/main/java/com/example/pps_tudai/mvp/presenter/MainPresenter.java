package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.view.MainView;

public class MainPresenter {

    private final MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void onLoginPressed() {
        mainView.showWelcomeMessage();
    }

    public void onSignUpPressed() {
        mainView.showRegistrationScreen();
    }


//    @Override
//    public void onLoginPressed() {
//        String username = loginView.getUsername();
//        String password = loginView.getPassword();
//        boolean isAuthenticated = loginModel.authenticateUser(username, password);
//        if (isAuthenticated) {
//            loginView.showWelcomeScreen();
//        } else {
//            loginView.showOnAuthenticationErrorMessage();
//        }
//    }

}
