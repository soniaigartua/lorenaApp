package com.example.pps_tudai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.example.pps_tudai.R;
import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.AppRoomDataBase;
import com.example.pps_tudai.mvp.model.DialogAvatarModel;
import com.example.pps_tudai.mvp.presenter.DialogAvatarPresenter;
import com.example.pps_tudai.mvp.view.DialogAvatarView;
import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.pps_tudai.utils.StringUtils.DOT;

public class AvatarDialog extends Dialog {

    private AvatarAPIResponse.Result avatar;
    private DialogAvatarPresenter presenter;
    int userId;
    private String imageUrl;

    public AvatarDialog(Context context, AvatarAPIResponse.Result avatar, int id) {
        super(context);
        this.avatar = avatar;
        this.userId = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_avatar_layout);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        DialogAvatarView avatarView = new DialogAvatarView(this);
        avatarView.config(avatar);
        AppRepository appRepository = new AppRepository(AppRoomDataBase.getDatabase(this.getContext()).userDao());
        imageUrl = avatar.getThumbnail().getPath() + DOT + avatar.getThumbnail().getExtension();
        DialogAvatarModel avatarModel = new DialogAvatarModel(appRepository);
        presenter = new DialogAvatarPresenter(avatarView, avatarModel, userId, imageUrl);
    }

    @OnClick(R.id.button_apply)
    public void onApplyPressed() {
        presenter.onApplyPressed();
    }

    @OnClick(R.id.button_cancel)
    public void onCancelPressed() {
        presenter.onCancelPressed();
    }
}
