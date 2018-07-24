package ee.skyhigh.l1nde.pickayear.data.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "leaderboard")
public class ScoreEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "score")
    private int score;

    @ColumnInfo(name = "date")
    private String date;

    public ScoreEntity(int id, int score, String date) {
        this.id = id;
        this.score = score;
        this.date = date;
    }

    @Ignore
    public ScoreEntity(int score) {
        this.score = score;
        this.date = new Date().toString();
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


}
