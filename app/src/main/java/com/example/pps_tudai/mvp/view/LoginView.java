package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.pps_tudai.R;
import com.example.pps_tudai.activity.LoginActivity;
import com.example.pps_tudai.activity.MainActivity;
import com.example.pps_tudai.activity.RegistrationActivity;
import com.example.pps_tudai.mvp.presenter.MainPresenter;

import java.lang.ref.WeakReference;
import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.core.content.ContextCompat.startActivity;

public class LoginView {

    @BindView(R.id.et_email) EditText email;
    @BindView(R.id.et_password) EditText password;
    @BindView(R.id.btn_enter_login) Button btnLogin;
    @BindView(R.id.btn_cancel_login) Button btnCancel;

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
            Toast.makeText(activityWeak.get(), "Password is incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    public void showOnEmailErrorMessage() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), "Email address is incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    public void showWelcomeScreen() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), "Welcome to lorenaApp", Toast.LENGTH_SHORT).show();
        }
    }

    public void showDataEmptyScreen() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), "Email Address or Password can not be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelLogin () {
        if (activityWeak.get() != null) {
            Intent cancel = new Intent(activityWeak.get(), MainActivity.class);
            activityWeak.get().startActivity(cancel);
        }
    }
}
