package com.example.pps_tudai.mvp.presenter;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

import com.example.pps_tudai.activity.AvatarSelectActivity;
import com.example.pps_tudai.bus.ApplyAvatarButtonDialogObserver;
import com.example.pps_tudai.bus.CancelAvatarButtonDialogObserver;
import com.example.pps_tudai.bus.DetailsAvatarRequestObserver;
import com.example.pps_tudai.bus.RxBus;
import com.example.pps_tudai.mvp.model.AvatarSelectModel;
import com.example.pps_tudai.mvp.view.AvatarSelectView;
import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;
import com.example.pps_tudai.adapter.AvatarAdapter;
import com.example.pps_tudai.utils.DialogUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pps_tudai.utils.IntUtils.MARVEL_TIMESTAMP;
import static com.example.pps_tudai.utils.IntUtils.ZERO;
import static com.example.pps_tudai.utils.StringUtils.DOT;
import static com.example.pps_tudai.utils.StringUtils.MARVEL_API_KEY;
import static com.example.pps_tudai.utils.StringUtils.MARVEL_HASH;

public class AvatarSelectPresenter {

    private final AvatarSelectView avatarView;
    private final AvatarSelectModel avatarModel;
    private Call<AvatarAPIResponse> avatarCall;
    private List<AvatarAPIResponse.Result> avatarData;
    private int userId;
    private String userImage;
    private String userEmail;


    public AvatarSelectPresenter(AvatarSelectView avatarView, AvatarSelectModel avatarModel, int id) {
        this.avatarView = avatarView;
        this.avatarModel = avatarModel;
        this.userId = id;
        this.init();
    }

    public void init() {
        callAvatarService();
    }

    public void callAvatarService() {
        avatarView.showLoader();
        avatarCall = avatarModel.getAvatarsDataFromService(MARVEL_TIMESTAMP, MARVEL_API_KEY, MARVEL_HASH);

        avatarCall.enqueue(new Callback<AvatarAPIResponse>() {
            @Override
            public void onResponse(Call<AvatarAPIResponse> call, Response<AvatarAPIResponse> response) {
                if (!response.isSuccessful()) {
                    avatarView.showContactAPINotSuccessful(String.valueOf(response.code()));
                    avatarView.hideLoader();
                } else {
                    avatarData = response.body().getData().getResults();
                    avatarView.setAdapter(new AvatarAdapter(avatarData, userId));
                    avatarView.hideLoader();
                }
            }

            @Override
            public void onFailure(Call<AvatarAPIResponse> call, Throwable t) {
                avatarView.showContactAPIFailure(t.getMessage());
                avatarView.hideLoader();
            }
        });
    }

    public void onReturnPressed() {
        avatarView.returnWelcomeActivity(userId, userImage, userEmail);
    }

    public void showAvatarDialog(AvatarAPIResponse.Result avatar) {
        Activity activity = avatarView.getActivity();

        if (activity != null) {
            DialogUtils.avatarDialog(activity, avatar);
        }
    }

    public void onCancelAvatarButtonDialogPressed(AlertDialog dialog) {
        dialog.dismiss();
    }

    public void changeAvatarRequest(AvatarAPIResponse.Result avatar, AlertDialog dialog) {
        String imageUrl = avatar.getThumbnail().getPath() + DOT + avatar.getThumbnail().getExtension();
        if (userId != ZERO) {
            avatarModel.updateImageUrl(userId, imageUrl);
        }
        userImage = imageUrl;
        dialog.dismiss();
    }

    public void registerBus() {
        Activity activity = avatarView.getActivity();

        if (activity != null) {
            RxBus.subscribe(activity, new DetailsAvatarRequestObserver() {
                @Override
                public void onEvent(DetailsAvatarRequestPressed value) {
                    showAvatarDialog(value.getAvatar());
                }
            });

            RxBus.subscribe(activity, new CancelAvatarButtonDialogObserver() {
                @Override
                public void onEvent(CancelAvatarButtonPressed event) {
                    onCancelAvatarButtonDialogPressed(event.getDialog());
                }
            });

            RxBus.subscribe(activity, new ApplyAvatarButtonDialogObserver() {
                @Override
                public void onEvent(ApplyAvatarButtonPressed event) {
                    changeAvatarRequest(event.getAvatar(), event.getDialog());
                }
            });
        }
    }

    public void unregisterBus(AvatarSelectActivity avatarSelectActivity) {
        RxBus.clear(avatarSelectActivity);
    }
}
