package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.widget.Toast;
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
            Toast.makeText(activityWeak.get(), "Welcome to the app", Toast.LENGTH_LONG).show();
        }
    }

    public void showRegistrationScreen() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), "Complete the next form", Toast.LENGTH_LONG).show();
        }
    }

}
