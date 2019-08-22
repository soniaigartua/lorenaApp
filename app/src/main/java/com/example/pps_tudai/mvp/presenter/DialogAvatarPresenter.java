package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.DialogAvatarModel;
import com.example.pps_tudai.mvp.view.DialogAvatarView;

public class DialogAvatarPresenter {

    private final DialogAvatarModel avatarModel;
    private final DialogAvatarView avatarView;
    int userId;
    private String imageUrl;

    public DialogAvatarPresenter(DialogAvatarView avatarView, DialogAvatarModel avatarModel, int id, String url) {
        this.avatarView = avatarView;
        this.avatarModel = avatarModel;
        this.userId = id;
        this.imageUrl = url;
    }

    public void onApplyPressed() {
        avatarModel.updateImageUrl(userId, imageUrl);
        avatarView.onApplyPressed();
    }

    public void onCancelPressed() {
        avatarView.onCancelPressed();
    }
}
