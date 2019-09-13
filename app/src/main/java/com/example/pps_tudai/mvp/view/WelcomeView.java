package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pps_tudai.R;
import com.example.pps_tudai.activity.AvatarSelectActivity;
import com.example.pps_tudai.activity.ExerciseSelectActivity;
import com.example.pps_tudai.activity.MainActivity;
import com.example.pps_tudai.activity.StepsCounterActivity;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.example.pps_tudai.utils.IntUtils.USER_AVATAR_HEIGHT;
import static com.example.pps_tudai.utils.IntUtils.USER_AVATAR_WIDTH;
import static com.example.pps_tudai.utils.StringUtils.USER_EMAIL;
import static com.example.pps_tudai.utils.StringUtils.USER_ID;
import static com.example.pps_tudai.utils.StringUtils.USER_IMAGE;

public class WelcomeView {

    @BindView(R.id.et_email_user)
    TextView email;
    @BindView(R.id.image_user)
    ImageView image_user;

    // activity should never be exposed publicly
    private WeakReference<Activity> activityWeak;

    public WelcomeView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
    }

    public void configSreen(int id, String userImage, String userEmail) {
        if (userImage != null) {
            Picasso.get().load(userImage)
                    .resize(USER_AVATAR_WIDTH,USER_AVATAR_HEIGHT)
                    .into(image_user);
        }
        email.setText(userEmail);
    }

    public void logout() {
        if (activityWeak.get() != null) {
            if (AccessToken.getCurrentAccessToken() != null) {
                LoginManager.getInstance().logOut();
            }
            Intent logout = new Intent(activityWeak.get(), MainActivity.class);
            activityWeak.get().startActivity(logout);
        }
    }

    public void selectAvatar(int userId, String image, String email) {
        if (activityWeak.get() != null) {
            Intent select_avatar = new Intent(activityWeak.get(), AvatarSelectActivity.class);
            select_avatar.putExtra(USER_ID, userId);
            select_avatar.putExtra(USER_IMAGE, image);
            select_avatar.putExtra(USER_EMAIL, email);
            activityWeak.get().startActivity(select_avatar);
        }
    }

    public void showExercisesList() {
        if (activityWeak.get() != null) {
            Intent select_exercise = new Intent(activityWeak.get(), ExerciseSelectActivity.class);
            activityWeak.get().startActivity(select_exercise);
        }
    }

    public void showStepsCounterFunction(int userId, String image, String email) {
        if (activityWeak.get() != null) {
            Intent counter = new Intent(activityWeak.get(), StepsCounterActivity.class);
            counter.putExtra(USER_ID, userId);
            counter.putExtra(USER_IMAGE, image);
            counter.putExtra(USER_EMAIL,email);
            activityWeak.get().startActivity(counter);
        }
    }
}
