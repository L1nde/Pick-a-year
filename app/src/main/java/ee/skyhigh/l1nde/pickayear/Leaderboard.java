package ee.skyhigh.l1nde.pickayear;

import android.app.ActivityOptions;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setEnterTransition(fade);
//            overridePendingTransition();

        }
        setContentView(R.layout.activity_leaderboard);

        Intent intent = getIntent();
        if (intent.hasExtra("points"))
            ((TextView)findViewById(R.id.nav_points)).setText(String.valueOf(intent.getIntExtra("points", 0)));


        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityWithTransition();
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

    private void startActivityWithTransition(){
        Intent intent = new Intent(Leaderboard.this, Home.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            supportFinishAfterTransition();
        } else {

            startActivity(intent);
            finish();
        }
    }
}
