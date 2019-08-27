package com.example.pps_tudai.utils;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import com.example.pps_tudai.R;
import com.example.pps_tudai.bus.ApplyAvatarButtonDialogObserver;
import com.example.pps_tudai.bus.CancelAvatarButtonDialogObserver;
import com.example.pps_tudai.bus.CancelExerciseButtonDialogObserver;
import com.example.pps_tudai.bus.RxBus;
import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;
import com.example.pps_tudai.services.exerciseService.Result;
import com.squareup.picasso.Picasso;

import io.reactivex.annotations.NonNull;

import static com.example.pps_tudai.utils.IntUtils.AVATAR_HEIGHT;
import static com.example.pps_tudai.utils.IntUtils.AVATAR_WIDTH;
import static com.example.pps_tudai.utils.StringUtils.DOT;

public class DialogUtils {

    public static void avatarDialog(@NonNull final Activity activity, final AvatarAPIResponse.Result avatar) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_avatar_layout, null);

        final ImageView image = view.findViewById(R.id.img_avatar_dialog);
        String imageUrl = avatar.getThumbnail().getPath() + DOT + avatar.getThumbnail().getExtension();
        Picasso.get().load(imageUrl)
                .resize(AVATAR_WIDTH, AVATAR_HEIGHT)
                .into(image);
        Button btnCancel = view.findViewById(R.id.button_cancel);
        Button btnApply = view.findViewById(R.id.button_apply);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.post(new ApplyAvatarButtonDialogObserver.ApplyAvatarButtonPressed(avatar, dialog));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.post(new CancelAvatarButtonDialogObserver.CancelAvatarButtonPressed(dialog));
            }
        });
    }

    public static void exerciseDialog(@NonNull final Activity activity, final Result exercise) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_exercise_layout, null);

        final ImageView image = view.findViewById(R.id.img_exercise_dialog);
        String imageUrl = exercise.getImage();
        Picasso.get().load(imageUrl)
                .resize(AVATAR_WIDTH, AVATAR_HEIGHT)
                .into(image);
        TextView name = view.findViewById(R.id.exercise_name);
        name.setText(SubString.isSubString(imageUrl));
        Button btnCancel = view.findViewById(R.id.button_cancel);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.post(new CancelExerciseButtonDialogObserver.CancelExerciseButtonPressed(dialog));
            }
        });
    }
}
