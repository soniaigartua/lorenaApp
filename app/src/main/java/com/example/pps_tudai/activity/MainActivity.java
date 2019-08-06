package com.example.pps_tudai.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pps_tudai.R;
import com.example.pps_tudai.mvp.presenter.MainPresenter;
import com.example.pps_tudai.mvp.view.MainView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        MainView view = new MainView(this);
        presenter = new MainPresenter(view);
    }

    @OnClick(R.id.btn_login)
    public void btnLoginClicked() {
        presenter.onLoginPressed();
    }

    @OnClick(R.id.btn_signup)
    public void btnRegistrationClicked() {
        presenter.onRegistrationPressed();
    }

}
