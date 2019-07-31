package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import com.example.pps_tudai.R;
import com.example.pps_tudai.activity.MainActivity;
import com.example.pps_tudai.mvp.presenter.MainPresenter;
import java.lang.ref.WeakReference;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginView {

    @BindView(R.id.et_email)
    EditText email;
    @BindView(R.id.et_password)
    EditText password;

    private MainPresenter presenter;

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

    public void showWelcomeScreen() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), activityWeak.get().getString(R.string.welcome_app_message), Toast.LENGTH_SHORT).show();
        }
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
