package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.AvatarSelectModel;
import com.example.pps_tudai.mvp.view.AvatarSelectView;
import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;
import com.example.pps_tudai.adapter.AvatarAdapter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.example.pps_tudai.utils.IntUtils.MARVEL_TIMESTAMP;
import static com.example.pps_tudai.utils.StringUtils.MARVEL_API_KEY;
import static com.example.pps_tudai.utils.StringUtils.MARVEL_HASH;

public class AvatarSelectPresenter {

    private final AvatarSelectView avatarView;
    private final AvatarSelectModel avatarModel;
    private Call<AvatarAPIResponse> avatarCall;
    private List<AvatarAPIResponse.Result> avatarData;
    private int userId;


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
        avatarCall = avatarModel.getAvatarsDataFromService(MARVEL_TIMESTAMP, MARVEL_API_KEY, MARVEL_HASH);

        avatarCall.enqueue(new Callback<AvatarAPIResponse>() {
            @Override
            public void onResponse(Call<AvatarAPIResponse> call, Response<AvatarAPIResponse> response) {
                if (!response.isSuccessful()) {
                    avatarView.showContactAPINotSuccessful(String.valueOf(response.code()));
                } else {
                    avatarData = response.body().getData().getResults();
                    avatarView.setAdapter(new AvatarAdapter(avatarData, userId));
                }
            }

            @Override
            public void onFailure(Call<AvatarAPIResponse> call, Throwable t) {
                avatarView.showContactAPIFailure(t.getMessage());
            }
        });
    }

    public void onSavePressed() {
        avatarView.saveChangesAvatar();
    }
}
