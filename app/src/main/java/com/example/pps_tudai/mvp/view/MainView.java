package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.widget.Toast;

import com.example.pps_tudai.R;

import java.lang.ref.WeakReference;
import butterknife.ButterKnife;

public class MainView {

    // activity should never be exposed publicly
    private WeakReference<Activity> activityWeak;

    public MainView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
    }

    public void showWelcomeMessage() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), R.string.welcome_message , Toast.LENGTH_LONG).show();
        }
    }

    public void showRegistrationScreen() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), R.string.sign_up_message , Toast.LENGTH_LONG).show();
        }
    }

}
