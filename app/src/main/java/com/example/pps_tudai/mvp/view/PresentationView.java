package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pps_tudai.R;
import com.example.pps_tudai.activity.LoginActivity;
import com.example.pps_tudai.activity.MainActivity;
import com.example.pps_tudai.activity.RegistrationActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PresentationView {

    // activity should never be exposed publicly
    private WeakReference<Activity> activityWeak;
    @BindView(R.id.welcome)
    TextView welcome;
    @BindView(R.id.logo)
    ImageView portada;

    public PresentationView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
    }

    public ImageView getPortadaImage() {
        return portada;
    }

    public TextView getWelcomeMessage() {
        return welcome;
    }

    public void setWelcomeMessage(TextView welcome) {
        this.welcome = welcome;
    }

    public void showMainScreen() {
        if (activityWeak.get() != null) {
            Intent main = new Intent(activityWeak.get(), MainActivity.class);
            activityWeak.get().startActivity(main);
        }
    }

    public void llega() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), activityWeak.get().getString(R.string.register_complete_message), Toast.LENGTH_SHORT).show();
        }
    }
}
