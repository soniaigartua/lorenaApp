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
import static com.example.pps_tudai.utils.StringUtils.USER_EMAIL;
import static com.example.pps_tudai.utils.StringUtils.USER_ID;
import static com.example.pps_tudai.utils.StringUtils.USER_IMAGE;

public class WelcomeActivity extends AppCompatActivity {

    private WelcomePresenter presenter;
    private int userId;
    private String userImage;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        userId = getIntent().getExtras().getInt(USER_ID);
        userEmail = getIntent().getExtras().getString(USER_EMAIL);
        userImage = getIntent().getExtras().getString(USER_IMAGE);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        WelcomeView welcomeView = new WelcomeView(this);
        AppRepository appRepository = new AppRepository(AppRoomDataBase.getDatabase(this).userDao());
        WelcomeModel welcomeModel = new WelcomeModel(appRepository);
        presenter = new WelcomePresenter(welcomeView,welcomeModel, userId, userImage, userEmail);
    }

    @OnClick(R.id.btn_logout)
    public void btnLogoutClicked() {
        presenter.onLogoutPressed();
    }

    @OnClick(R.id.image_user)
    public void selectAvatar() {
        presenter.onSelectAvatarPressed();
    }

    @OnClick(R.id.exercise_button)
    public void btnExerciseClicked() {
        presenter.onExercisesListPressed();
    }

    @OnClick(R.id.counter_button)
    public void btnCounterStepsClicked() {
        presenter.onCounterStepsPressed(userId, userImage, userEmail);
    }
}
