package com.example.pps_tudai.mvp.view;

import android.app.Dialog;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pps_tudai.R;
import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.example.pps_tudai.utils.IntUtils.AVATAR_HEIGHT;
import static com.example.pps_tudai.utils.IntUtils.AVATAR_WIDTH;
import static com.example.pps_tudai.utils.StringUtils.DOT;

public class DialogAvatarView {

    @BindView(R.id.name_avatar_dialog)
    TextView name;
    @BindView(R.id.img_avatar_dialog)
    ImageView image;

    // activity should never be exposed publicly
    private WeakReference<Dialog> dialog;

    public DialogAvatarView(Dialog dialog) {
        ButterKnife.bind(this, dialog);
        this.dialog = new WeakReference<Dialog>(dialog);

    }

    public void config (AvatarAPIResponse.Result avatar) {
        name.setText(avatar.getName());
        String url = avatar.getThumbnail().getPath() + DOT + avatar.getThumbnail().getExtension();
        Picasso.get().load(url)
                .resize(AVATAR_WIDTH,AVATAR_HEIGHT)
                .into(image);
    }

    public void onApplyPressed() {
        Toast.makeText(dialog.get().getContext(), R.string.changes_applied, Toast.LENGTH_SHORT).show();
        onCancelPressed();
    }

    public void onCancelPressed() {
        dialog.get().dismiss();
    }
}
