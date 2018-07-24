package ee.skyhigh.l1nde.pickayear;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ee.skyhigh.l1nde.pickayear.data.ScoreViewModel;
import ee.skyhigh.l1nde.pickayear.data.entites.ScoreEntity;
import ee.skyhigh.l1nde.pickayear.data.repositories.ScoreRepository;

public class Leaderboard extends AppCompatActivity {

    private ScoreViewModel scoreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);


        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Leaderboard.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        RecyclerView leaderboard = findViewById(R.id.leaderboard);
        leaderboard.setHasFixedSize(true);

        leaderboard.setLayoutManager(new LinearLayoutManager(this));
        final RecyclerAdapter adapter = new RecyclerAdapter();
        leaderboard.setAdapter(adapter);

        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);
        scoreViewModel.getScores().observe(this, new Observer<List<ScoreEntity>>() {
            @Override
            public void onChanged(@Nullable List<ScoreEntity> scoreEntities) {
                adapter.setScores(scoreEntities);
            }
        });
    }
}
