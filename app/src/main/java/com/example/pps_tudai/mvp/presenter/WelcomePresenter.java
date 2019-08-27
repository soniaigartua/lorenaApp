package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.data.entities.entity.User;
import com.example.pps_tudai.mvp.model.WelcomeModel;
import com.example.pps_tudai.mvp.view.WelcomeView;

public class WelcomePresenter {

    private final WelcomeView welcomeView;
    private final WelcomeModel welcomeModel;
    private User user;

    public WelcomePresenter(WelcomeView welcomeView, WelcomeModel welcomeModel, int id) {
        this.welcomeView = welcomeView;
        this.welcomeModel = welcomeModel;
        this.init(id);
    }

    public void init(int id) {
        user = welcomeModel.getUserById(id);
        welcomeView.configSreen(user);
    }

    public void onSelectAvatarPressed(int id) {
        welcomeView.selectAvatar((int)user.getId());
    }

    public void onLogoutPressed() {
        welcomeView.logout();
    }

    public void onExercisesListPressed() {
        welcomeView.showExercisesList();
    }
}
