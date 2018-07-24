package ee.skyhigh.l1nde.pickayear;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ee.skyhigh.l1nde.pickayear.data.ScoreViewModel;
import ee.skyhigh.l1nde.pickayear.data.entites.ScoreEntity;

public class Main extends AppCompatActivity {

    private QuizHolder quizHolder = new QuizHolder();
    private CheckAlert checkAlert = new CheckAlert();

    private ScoreViewModel scoreViewModel;

    private EditText yearInput;

    private ToggleButton m_choice1;
    private ToggleButton m_choice2;
    private ToggleButton m_choice3;

    private ToggleButton d_choice1;
    private ToggleButton d_choice2;
    private ToggleButton d_choice3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);

        yearInput = findViewById(R.id.year_input);

        m_choice1 = findViewById(R.id.m_choice1);
        m_choice2 = findViewById(R.id.m_choice2);
        m_choice3 = findViewById(R.id.m_choice3);

        d_choice1 = findViewById(R.id.d_choice1);
        d_choice2 = findViewById(R.id.d_choice2);
        d_choice3 = findViewById(R.id.d_choice3);

        nextQuestion();
    }

    public void onMChoiceClick1(View view){
        ToggleButton toggleButton = (ToggleButton) view;
        if (!toggleButton.isChecked()){
            return;
        }
        ((ToggleButton) findViewById(R.id.m_choice2)).setChecked(false);
        ((ToggleButton) findViewById(R.id.m_choice3)).setChecked(false);
    }

    public void onMChoiceClick2(View view){
        ToggleButton toggleButton = (ToggleButton) view;
        if (!toggleButton.isChecked()){
            return;
        }
        ((ToggleButton) findViewById(R.id.m_choice1)).setChecked(false);
        ((ToggleButton) findViewById(R.id.m_choice3)).setChecked(false);
    }

    public void onMChoiceClick3(View view){
        ToggleButton toggleButton = (ToggleButton) view;
        if (!toggleButton.isChecked()){
            return;
        }
        ((ToggleButton) findViewById(R.id.m_choice1)).setChecked(false);
        ((ToggleButton) findViewById(R.id.m_choice2)).setChecked(false);
    }

    public void onDChoiceClick1(View view){
        ToggleButton toggleButton = (ToggleButton) view;
        if (!toggleButton.isChecked()){
            return;
        }
        ((ToggleButton) findViewById(R.id.d_choice2)).setChecked(false);
        ((ToggleButton) findViewById(R.id.d_choice3)).setChecked(false);
    }

    public void onDChoiceClick2(View view){
        ToggleButton toggleButton = (ToggleButton) view;
        if (!toggleButton.isChecked()){
            return;
        }
        ((ToggleButton) findViewById(R.id.d_choice1)).setChecked(false);
        ((ToggleButton) findViewById(R.id.d_choice3)).setChecked(false);
    }

    public void onDChoiceClick3(View view){
        ToggleButton toggleButton = (ToggleButton) view;
        if (!toggleButton.isChecked()){
            return;
        }
        ((ToggleButton) findViewById(R.id.d_choice1)).setChecked(false);
        ((ToggleButton) findViewById(R.id.d_choice2)).setChecked(false);
    }


    public void check(View view){
        if (!m_choice1.isChecked() && !m_choice2.isChecked() && !m_choice3.isChecked()){
            Toast toast = Toast.makeText(this, R.string.month_error, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        } else {
            if (TextUtils.isEmpty(yearInput.getText().toString())){
                Toast toast = Toast.makeText(this, R.string.year_error, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
            else {
                String month;
                if (m_choice1.isChecked()){
                    month = m_choice1.getTextOn().toString();
                } else if (m_choice2.isChecked()){
                    month = m_choice2.getTextOn().toString();
                } else {
                    month = m_choice3.getTextOn().toString();
                }
                int day;
                if (d_choice1.isChecked()){
                    day = Integer.parseInt(d_choice1.getTextOn().toString());
                } else if (d_choice2.isChecked()){
                    day = Integer.parseInt(d_choice2.getTextOn().toString());
                } else {
                    day = Integer.parseInt(d_choice3.getTextOn().toString());
                }
                final int points = calculatePoints(Integer.parseInt(yearInput.getText().toString()), month, day);
                Question last = quizHolder.getLast();
                checkAlert.setMessage("Correct answer is " + last.getDay() + "." + last.getMonth() + "." + last.getYear() + "\nCongrationlation! You got " + points);
                checkAlert.show(getSupportFragmentManager(), "check");
                checkAlert.setOnDismissListener(new CheckAlert.OnDismissListener() {
                    @Override
                    public void onDismiss(CheckAlert checkAlert) {
                        nextQuestion();
                        TextView pointsView = findViewById(R.id.nav_points);
                        pointsView.setText(String.valueOf(Integer.parseInt(pointsView.getText().toString()) + points));
                    }
                });
            }
        }
    }

    private void nextQuestion(){
        List<Integer> guessMonths = new ArrayList<>(3);
        List<Integer> guessDays = new ArrayList<>(3);

        Question nextQuestion = quizHolder.getNextQuestion();

        if (nextQuestion == null){
            scoreViewModel.insert(new ScoreEntity(Integer.parseInt(((TextView) findViewById(R.id.nav_points)).getText().toString())));

            Intent intent = new Intent(this, Leaderboard.class);
            startActivity(intent);
            finish();
            return;

        }

        guessMonths.add(nextQuestion.getMonth());
        while (guessMonths.size() != 3){
            int temp = (int) Math.round(Math.random() * 11);
            if (!guessMonths.contains(temp)){
                guessMonths.add(temp);
            }
        }

        guessDays.add(nextQuestion.getDay());
        while (guessDays.size() != 3){
            int temp = (int) Math.round(Math.random()*30+1);
            if (!guessDays.contains(temp)){
                guessDays.add(temp);
            }
        }

        Collections.shuffle(guessMonths);
        Collections.shuffle(guessDays);

        ImageView imageView = findViewById(R.id.main_image);
        imageView.setImageResource(getResources().getIdentifier(nextQuestion.getPath(), "drawable", getPackageName()));

        TextView textView = findViewById(R.id.main_question);
        textView.setText(getResources().getIdentifier(nextQuestion.getPath(), "string", getPackageName()));

        m_choice1.setChecked(false);
        setToggleButtonText(m_choice1, getString(getResources().getIdentifier("month_" + guessMonths.get(0), "string", getPackageName())));

        m_choice2.setChecked(false);
        setToggleButtonText(m_choice2, getString(getResources().getIdentifier("month_" + guessMonths.get(1), "string", getPackageName())));

        m_choice3.setChecked(false);
        setToggleButtonText(m_choice3, getString(getResources().getIdentifier("month_" + guessMonths.get(2), "string", getPackageName())));

        d_choice1.setChecked(false);
        setToggleButtonText(d_choice1, String.valueOf(guessDays.get(0)));

        d_choice2.setChecked(false);
        setToggleButtonText(d_choice2, String.valueOf(guessDays.get(1)));

        d_choice3.setChecked(false);
        setToggleButtonText(d_choice3, String.valueOf(guessDays.get(2)));

        yearInput.setText("");
    }

    private void setToggleButtonText(ToggleButton button, String name){
        button.setTextOff(name);
        button.setTextOn(name);
        button.setText(name);
    }

    private int calculatePoints(int year, String month, int day){
        int monthInt;
        switch (month){
            case "January":
                monthInt = 0;
                break;
            case "February":
                monthInt = 1;
                break;
            case "March":
                monthInt = 2;
                break;
            case "April":
                monthInt = 3;
                break;
            case "May":
                monthInt = 4;
                break;
            case "June":
                monthInt = 5;
                break;
            case "July":
                monthInt = 6;
                break;
            case "August":
                monthInt = 7;
                break;
            case "September":
                monthInt = 8;
                break;
            case "October":
                monthInt = 9;
                break;
            case "November":
                monthInt = 10;
                break;
            case "December":
                monthInt = 11;
                break;
            default:
                throw new RuntimeException("Invalid month!!");
        }
        Question actual = quizHolder.getLast();
        double date_dif = 365*Math.abs(actual.getYear()-year)+30.417*Math.abs(monthInt-actual.getMonth())+Math.abs(day-actual.getDay());
        int points = (int) (-0.000300244*(date_dif*date_dif)+1000);
        return points >= 0 ? points : 0;
    }
}
