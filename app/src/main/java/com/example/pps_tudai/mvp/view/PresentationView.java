package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.view.animation.Animation;
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
    TextView sign_enter;
    @BindView(R.id.logo)
    ImageView img_enter;

    public PresentationView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
    }

    public ImageView getEnterImage() {
        return img_enter;
    }

    public TextView getEnterSign() {
        return sign_enter;
    }

    public void setSignMessage(TextView welcome) {
        this.img_enter = img_enter;
    }

    public void showMainScreen() {
        if (activityWeak.get() != null) {
            Intent main = new Intent(activityWeak.get(), MainActivity.class);
            activityWeak.get().startActivity(main);
        }
    }

    public void initAnimations(Animation animation1, Animation animation2) {
        sign_enter.setAnimation(animation1);
        img_enter.setAnimation(animation2);
    }
}
