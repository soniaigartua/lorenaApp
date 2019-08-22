package com.example.pps_tudai.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pps_tudai.R;
import com.example.pps_tudai.mvp.model.AvatarSelectModel;
import com.example.pps_tudai.mvp.presenter.AvatarSelectPresenter;
import com.example.pps_tudai.mvp.view.AvatarSelectView;
import com.example.pps_tudai.services.avatarService.AvatarServiceCall;
import com.example.pps_tudai.services.avatarService.AvatarServiceGenerator;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.example.pps_tudai.utils.StringUtils.USER_ID;

public class AvatarSelectActivity extends AppCompatActivity {

    AvatarSelectPresenter presenter;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_select);

        Bundle data = getIntent().getExtras();
        userId = data.getInt(USER_ID);
        init();
    }

    public void init () {
        ButterKnife.bind(this);
        AvatarSelectView view = new AvatarSelectView(this, userId);
        AvatarSelectModel model = new AvatarSelectModel(AvatarServiceGenerator.createService(AvatarServiceCall.class));
        presenter = new AvatarSelectPresenter(view, model, userId);
    }

    @OnClick(R.id.btn_return)
    public void btnSaveChangesClicked() {
        presenter.onSavePressed();
    }
}
