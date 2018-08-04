package ee.skyhigh.l1nde.pickayear.data.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "leaderboard")
public class ScoreEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "score")
    private int score;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "answered")
    private int answered;

    public ScoreEntity(int id, int score, String date, int answered) {
        this.id = id;
        this.score = score;
        this.date = date;
        this.answered = answered;
    }

    @Ignore
    public ScoreEntity(int score, int answered) {
        this.score = score;
        this.answered = answered;
        this.date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date());
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getAnswered() {
        return answered;
    }
}
