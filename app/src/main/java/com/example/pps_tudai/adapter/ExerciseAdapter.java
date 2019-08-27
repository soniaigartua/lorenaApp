package com.example.pps_tudai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pps_tudai.R;
import com.example.pps_tudai.bus.DetailsExerciseRequestObserver;
import com.example.pps_tudai.bus.RxBus;
import com.example.pps_tudai.services.exerciseService.ExerciseAPIResponse;
import com.example.pps_tudai.utils.SubString;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<ExerciseAPIResponse.Result> exercisesDataList;

    public ExerciseAdapter(List<ExerciseAPIResponse.Result> exercisesDataList) {
        this.exercisesDataList = exercisesDataList;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View exerciseView = inflater.inflate(R.layout.row_exercise_layout, parent, false);

        // Return a new holder instance
        ExerciseViewHolder viewHolder = new ExerciseViewHolder(exerciseView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        holder.exercise = exercisesDataList.get(position);
        String url = holder.exercise.getImage();
        String name = SubString.isSubString(url);
        holder.name_exercise.setText(name);
    }

    @Override
    public int getItemCount() {
        return exercisesDataList.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_exercise)
        TextView name_exercise;
        ExerciseAPIResponse.Result exercise;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.name_exercise)
        public void onDetailsClick(View view) {
            RxBus.post(new DetailsExerciseRequestObserver.DetailsExerciseRequestPressed(exercise));
        }
    }
}

