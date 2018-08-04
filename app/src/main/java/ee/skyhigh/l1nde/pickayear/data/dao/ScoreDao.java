package ee.skyhigh.l1nde.pickayear.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ee.skyhigh.l1nde.pickayear.data.entites.ScoreEntity;

@Dao
public interface ScoreDao {

    @Insert
    void insert(ScoreEntity scoreEntity);

    @Query("select  * from leaderboard order by score DESC limit 10")
    LiveData<List<ScoreEntity>> getTop10();

    @Query("delete from leaderboard")
    void deleteAll();
}
