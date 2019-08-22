package com.example.pps_tudai.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pps_tudai.R;
import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.AppRoomDataBase;
import com.example.pps_tudai.mvp.model.WelcomeModel;
import com.example.pps_tudai.mvp.presenter.WelcomePresenter;
import com.example.pps_tudai.mvp.view.WelcomeView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.pps_tudai.utils.StringUtils.USER_ID;

public class WelcomeActivity extends AppCompatActivity {

    private WelcomePresenter presenter;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Bundle data = getIntent().getExtras();
        userId = data.getInt(USER_ID);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        WelcomeView welcomeView = new WelcomeView(this);
        AppRepository appRepository = new AppRepository(AppRoomDataBase.getDatabase(this).userDao());
        WelcomeModel welcomeModel = new WelcomeModel(appRepository);
        presenter = new WelcomePresenter(welcomeView,welcomeModel, userId);
    }

    @OnClick(R.id.btn_logout)
    public void btnLogoutClicked() {
        presenter.onLogoutPressed();
    }

    @OnClick(R.id.image_user)
    public void selectAvatar() {
        presenter.onSelectAvatarPressed(userId);
    }
}
