package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.WelcomeModel;
import com.example.pps_tudai.mvp.view.WelcomeView;

public class WelcomePresenter {

    private final WelcomeView welcomeView;
    private final WelcomeModel welcomeModel;
    private int userId;
    private String userImage;
    private String userEmail;

    public WelcomePresenter(WelcomeView welcomeView, WelcomeModel welcomeModel, int id, String userImage, String userEmail) {
        this.welcomeView = welcomeView;
        this.welcomeModel = welcomeModel;
        this.userId = id;
        this.userImage = userImage;
        this.userEmail = userEmail;
        this.init(id, userImage, userEmail);
    }

    private void init(int id, String userImage, String userEmail) {
        welcomeView.configSreen(id, userImage, userEmail);
    }

    public void onSelectAvatarPressed() {
        welcomeView.selectAvatar(userId, userImage, userEmail);
    }

    public void onLogoutPressed() {
        welcomeView.logout();
    }

    public void onExercisesListPressed() {
        welcomeView.showExercisesList();
    }

    public void onCounterStepsPressed(int userid, String image, String email) {
        welcomeView.showStepsCounterFunction(userid, image, email);
    }

}
