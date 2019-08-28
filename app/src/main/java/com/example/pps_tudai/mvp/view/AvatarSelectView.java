package com.example.pps_tudai.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pps_tudai.R;
import com.example.pps_tudai.activity.WelcomeActivity;
import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;
import com.example.pps_tudai.adapter.AvatarAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.pps_tudai.utils.IntUtils.AVATAR_COLUMNS;
import static com.example.pps_tudai.utils.IntUtils.PERCENTAGE_UPLOADER;

public class AvatarSelectView {

    // activity should never be exposed publicly
    private WeakReference<Activity> activityWeak;
    @BindView(R.id.avatar_sign)
    TextView avatar_sign;
    @BindView(R.id.recycler_view)
    RecyclerView avatarListRecycler;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    private int userId;


    private LinearLayoutManager layoutManager;
    private AvatarAdapter adapterRecycler;

    public AvatarSelectView(Activity activity, int id) {
        ButterKnife.bind(this, activity);
        this.activityWeak = new WeakReference<Activity>(activity);
        this.userId = id;
        init();
    }

    public void init() {
        layoutManager = new GridLayoutManager(getActivity(),AVATAR_COLUMNS);
        avatarListRecycler.setLayoutManager(layoutManager);
        adapterRecycler = new AvatarAdapter(new ArrayList<AvatarAPIResponse.Result>(), userId);
        avatarListRecycler.setAdapter(adapterRecycler);
        avatarListRecycler.invalidate();
    }

    public void setAdapter(AvatarAdapter adapter) {
        avatarListRecycler.setAdapter(adapter);
        avatarListRecycler.invalidate();
    }

    public Activity getActivity() {
        return activityWeak.get();
    }

    public void showContactAPIError() {
        Toast.makeText(this.getActivity(), R.string.error_contact_api_data, Toast.LENGTH_LONG).show();
    }

    public void showContactAPINotSuccessful(String codigo) {
        avatar_sign.setText(codigo);
    }

    public void showContactAPIFailure(String message) {
        avatar_sign.setText(message);
    }

    public void saveChangesAvatar() {
        if (activityWeak.get() != null) {
            Intent welcome = new Intent(activityWeak.get(), WelcomeActivity.class);
            activityWeak.get().startActivity(welcome);
        }
    }

    public void returnWelcomeActivity() {
        if (activityWeak.get() != null) {
            Intent welcome = new Intent(activityWeak.get(), WelcomeActivity.class);
            activityWeak.get().startActivity(welcome);
        }
    }

    public ProgressBar getProgress_bar() {
        return progress_bar;
    }

    public void setProgress_bar(ProgressBar progress_bar) {
        this.progress_bar = progress_bar;
    }

    public void showLoader () {
        progress_bar.setProgress(PERCENTAGE_UPLOADER);
        progress_bar.setVisibility(View.VISIBLE);
    }

    public void hideLoader () {
        progress_bar.setVisibility(View.GONE);
    }
}
