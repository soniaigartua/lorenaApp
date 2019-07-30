package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import com.example.pps_tudai.R;
import com.example.pps_tudai.activity.MainActivity;

import java.lang.ref.WeakReference;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationView {

    @BindView(R.id.et_name) EditText name;
    @BindView(R.id.et_surname) EditText surname;
    @BindView(R.id.et_email) EditText email;
    @BindView(R.id.et_password) EditText password;

    // activity should never be exposed publicly
    private WeakReference<Activity> activityWeak;

    public RegistrationView(Activity activity) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
    }

    public String getUserName() {
        return name.getText().toString();
    }

    public String getUserSurname() {
        return surname.getText().toString();
    }

    public String getUserEmail() {
        return email.getText().toString();
    }


    public String getPassword() {
        return password.getText().toString();
    }

    public void showRegistrationScreenOK() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), activityWeak.get().getString(R.string.register_complete_message), Toast.LENGTH_SHORT).show();
        }
    }

    public void showDataEmptyScreen() {
        if (activityWeak.get() != null) {
            Toast.makeText(activityWeak.get(), activityWeak.get().getString(R.string.authentication_error_message), Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelRegister () {
        if (activityWeak.get() != null) {
            Intent cancel = new Intent(activityWeak.get(), MainActivity.class);
            activityWeak.get().startActivity(cancel);
        }
    }
}
