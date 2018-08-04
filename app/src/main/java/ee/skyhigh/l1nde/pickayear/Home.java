package ee.skyhigh.l1nde.pickayear;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setExitTransition(slide);
        }
        setContentView(R.layout.activity_home);
    }

    public void startQuiz(View view) {
        Intent intent = new Intent(this, Main.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            finishAfterTransition();
        } else {
            startActivity(intent);
            finish();
        }

    }

    public void showLeaderboard(View view){
        Intent intent = new Intent(this, Leaderboard.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//            finishAfterTransition();
            supportFinishAfterTransition();
        } else {
            startActivity(intent);
            finish();
        }
    }
}

