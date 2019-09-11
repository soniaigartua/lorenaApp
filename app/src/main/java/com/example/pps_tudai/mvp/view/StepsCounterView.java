package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pps_tudai.R;
import com.example.pps_tudai.activity.WelcomeActivity;
import com.example.pps_tudai.data.entities.entity.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.pps_tudai.utils.IntUtils.USER_AVATAR_HEIGHT;
import static com.example.pps_tudai.utils.IntUtils.USER_AVATAR_WIDTH;
import static com.example.pps_tudai.utils.StringUtils.NO_SENSOR_REGISTERED;

public class StepsCounterView {

    @BindView(R.id.email_user)
    TextView email;
    @BindView(R.id.image_user)
    ImageView image_user;
    @BindView(R.id.number_steps)
    TextView number_steps;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.calories)
    TextView calories;

    // activity should never be exposed publicly
    private WeakReference<Activity> activityWeak;

    public StepsCounterView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
    }

    public void configSreen(User user) {
        if (user.getUrl_image() != null) {
            Picasso.get().load(user.getUrl_image())
                    .resize(USER_AVATAR_WIDTH,USER_AVATAR_HEIGHT)
                    .into(image_user);
        }
        email.setText(user.getEmail());
    }

    public Activity getActivity () {
        return activityWeak.get();
    }

    public void returnWelcomeActivity() {
        if (activityWeak.get() != null) {
            Intent welcome = new Intent(activityWeak.get(), WelcomeActivity.class);
            activityWeak.get().startActivity(welcome);
        }
    }

    public void showNotSenserRegisteredMessagge() {
        Toast.makeText(activityWeak.get(), NO_SENSOR_REGISTERED, Toast.LENGTH_LONG).show();
    }

    public void showMessagge(String mensaje) {
        Toast.makeText(activityWeak.get(), mensaje, Toast.LENGTH_LONG).show();
    }

    public void showCountingSteps(String value) {
        number_steps.setText(String.valueOf(value));
    }

    public void showTravelledDistance(String value) {
        distance.setText(String.valueOf(value));
    }

    public void showConsumedCalories(String value) {
        calories.setText(String.valueOf(value));
    }

    public void configSreenGoogleUser(GoogleSignInAccount account) {
        if (account.getPhotoUrl() != null) {
            Picasso.get().load(account.getPhotoUrl())
                    .resize(USER_AVATAR_WIDTH,USER_AVATAR_HEIGHT)
                    .into(image_user);
        }
        email.setText(account.getEmail());
    }
}
