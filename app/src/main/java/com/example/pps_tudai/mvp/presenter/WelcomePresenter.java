package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.data.entities.entity.User;
import com.example.pps_tudai.mvp.model.WelcomeModel;
import com.example.pps_tudai.mvp.view.WelcomeView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import static com.example.pps_tudai.utils.IntUtils.ZERO;

public class WelcomePresenter {

    private final WelcomeView welcomeView;
    private final WelcomeModel welcomeModel;
    private int userId;

    public WelcomePresenter(WelcomeView welcomeView, WelcomeModel welcomeModel, int id) {
        this.welcomeView = welcomeView;
        this.welcomeModel = welcomeModel;
        this.userId = id;
        this.init(id);
    }

    public void init(int id) {
        if(id!= ZERO) {
            User user = welcomeModel.getUserById(id);
            welcomeView.configSreen(user);
        }
    }

    public void onSelectAvatarPressed(int id) {
        welcomeView.selectAvatar(id);
    }

    public void onLogoutPressed() {
        welcomeView.logout();
    }

    public void onExercisesListPressed() {
        welcomeView.showExercisesList();
    }

    public void onCounterStepsPressed() {
        welcomeView.showStepsCounterFunction(userId);
    }

    public void showUserData(GoogleSignInAccount account) {
        welcomeView.configSreenGoogleUser(account);
    }
}
