package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pps_tudai.R;
import com.example.pps_tudai.activity.MainActivity;
import com.example.pps_tudai.activity.WelcomeActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.pps_tudai.utils.StringUtils.USER_ID;

public class LoginView {

    @BindView(R.id.et_email)
    EditText email;
    @BindView(R.id.et_password)
    EditText password;

    // activity should never be exposed publicly
    private WeakReference<Activity> activityWeak;

    public LoginView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
    }

    public String getUserEmail() {
        return email.getText().toString();
    }

    public String getPassword() {
        return password.getText().toString();
    }

    public void showOnAuthenticationErrorMessage() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), activityWeak.get().getString(R.string.authentication_error_message), Toast.LENGTH_SHORT).show();
        }
    }

    public void showOnEmailErrorMessage() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), activityWeak.get().getString(R.string.incorrect_email_message), Toast.LENGTH_SHORT).show();
        }
    }

    public void showNoRegistrationMessage() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), activityWeak.get().getString(R.string.no_registration_message), Toast.LENGTH_SHORT).show();
            cancelLogin();
        }
    }

    public void showWelcomeScreen(int userId) {
        if (activityWeak.get() != null) {
            clearTextView();
            Intent welcome = new Intent(activityWeak.get(), WelcomeActivity.class);
            welcome.putExtra(USER_ID, userId);
            activityWeak.get().startActivity(welcome);
        }
    }

    public void clearTextView() {
        email.getText().clear();
        password.getText().clear();
    }

    public void showDataEmptyScreen() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), activityWeak.get().getString(R.string.empty_fields_message), Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelLogin() {
        if (activityWeak.get() != null) {
            Intent cancel = new Intent(activityWeak.get(), MainActivity.class);
            activityWeak.get().startActivity(cancel);
        }
    }
}
