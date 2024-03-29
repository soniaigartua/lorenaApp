package com.example.pps_tudai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pps_tudai.R;
import com.example.pps_tudai.bus.DetailsAvatarRequestObserver;
import com.example.pps_tudai.bus.RxBus;
import com.example.pps_tudai.services.avatarService.AvatarAPIResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.pps_tudai.utils.IntUtils.AVATAR_HEIGHT;
import static com.example.pps_tudai.utils.IntUtils.AVATAR_WIDTH;
import static com.example.pps_tudai.utils.StringUtils.DOT;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder> {

    private List<AvatarAPIResponse.Result> avatarDataList;
    private int userId;

    public AvatarAdapter(List<AvatarAPIResponse.Result> avatarDataList, int id) {
        this.avatarDataList = avatarDataList;
        this.userId = id;
    }

    @Override
    public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View avatarView = inflater.inflate(R.layout.row_avatar_layout, parent, false);

        // Return a new holder instance
        AvatarViewHolder viewHolder = new AvatarViewHolder(avatarView, userId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AvatarViewHolder holder, int position) {
        holder.avatar = avatarDataList.get(position);
        String url = holder.avatar.getThumbnail().getPath() + DOT + holder.avatar.getThumbnail().getExtension();
        Picasso.get().load(url)
                .resize(AVATAR_WIDTH, AVATAR_HEIGHT)
                .into(holder.img_avatar);
    }

    @Override
    public int getItemCount() {
        return avatarDataList.size();
    }

    public class AvatarViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_avatar)
        ImageView img_avatar;
        AvatarAPIResponse.Result avatar;
        int userId;

        public AvatarViewHolder(View itemView, int id) {
            super(itemView);
            this.userId = id;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.img_avatar)
        public void onDetailsClick(View view) {
            RxBus.post(new DetailsAvatarRequestObserver.DetailsAvatarRequestPressed(avatar));
        }

    }
}

