package com.example.pps_tudai.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pps_tudai.R;
import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.AppRoomDataBase;
import com.example.pps_tudai.mvp.model.LoginModel;
import com.example.pps_tudai.mvp.presenter.LoginPresenter;
import com.example.pps_tudai.mvp.view.LoginView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        LoginView view = new LoginView(this);
        AppRepository appRepository = new AppRepository(AppRoomDataBase.getDatabase(this).userDao());
        LoginModel model = new LoginModel(appRepository);

        presenter = new LoginPresenter(model, view);
    }

    @OnClick(R.id.btn_enter_login)
    public void btnLoginClicked() {
        presenter.onLoginPressed();
    }

    @OnClick(R.id.btn_cancel_login)
    public void btnCancelLoginClicked() {
        presenter.onCancelPressed();
    }
}
