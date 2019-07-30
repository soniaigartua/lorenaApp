package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import com.example.pps_tudai.activity.LoginActivity;
import com.example.pps_tudai.activity.RegistrationActivity;
import java.lang.ref.WeakReference;
import butterknife.ButterKnife;

public class MainView {

    // activity should never be exposed publicly
    private WeakReference<Activity> activityWeak;

    public MainView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
    }

    public void showLoginScreen() {
        if (activityWeak.get() != null) {
            Intent login = new Intent(activityWeak.get(), LoginActivity.class);
            activityWeak.get().startActivity(login);
        }
    }

    public void showRegistrationScreen() {
        if (activityWeak.get() != null) {
            Intent register = new Intent(activityWeak.get(), RegistrationActivity.class);
            activityWeak.get().startActivity(register);
        }
    }
}
