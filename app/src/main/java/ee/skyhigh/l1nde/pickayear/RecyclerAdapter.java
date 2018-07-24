package ee.skyhigh.l1nde.pickayear;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ee.skyhigh.l1nde.pickayear.data.ScoreViewModel;
import ee.skyhigh.l1nde.pickayear.data.entites.ScoreEntity;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<ScoreEntity> scores;

    public RecyclerAdapter() {

    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leaderboard_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ScoreEntity scoreEntity = scores.get(i);
        viewHolder.getPositionField().setText(String.valueOf(i+1));
        viewHolder.getScoreField().setText(String.valueOf(scoreEntity.getScore()));
        viewHolder.getDateField().setText(String.valueOf(scoreEntity.getDate()));
    }

    public void setScores(List<ScoreEntity> scores){
        this.scores = scores;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return scores == null ? 0 : scores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView positionField;
        private TextView scoreField;
        private TextView dateField;

        public ViewHolder(View view) {
            super(view);
            positionField = view.findViewById(R.id.position);
            scoreField = view.findViewById(R.id.score);
            dateField = view.findViewById(R.id.date);
        }

        public TextView getPositionField() {
            return positionField;
        }

        public TextView getScoreField() {
            return scoreField;
        }

        public TextView getDateField() {
            return dateField;
        }
    }
}
