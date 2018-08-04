package ee.skyhigh.l1nde.pickayear.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ee.skyhigh.l1nde.pickayear.data.dao.ScoreDao;
import ee.skyhigh.l1nde.pickayear.data.database.AppDatabase;
import ee.skyhigh.l1nde.pickayear.data.entites.ScoreEntity;

public class ScoreRepository {

    private ScoreDao scoreDao;
    private LiveData<List<ScoreEntity>> scores;

    public ScoreRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        scoreDao = db.scoreDao();
        scores = scoreDao.getTop10();
    }

    public LiveData<List<ScoreEntity>> getScores() {
        return scores;
    }

    public void insert(ScoreEntity scoreEntity){
        new insertAsyncTask(scoreDao).execute(scoreEntity);

    }
    private static class insertAsyncTask extends AsyncTask<ScoreEntity, Void, Void>{

        private ScoreDao scoreDao;

        insertAsyncTask(ScoreDao scoreDao){
            this.scoreDao = scoreDao;
        }

        @Override
        protected Void doInBackground(ScoreEntity... scoreEntities) {
            scoreDao.insert(scoreEntities[0]);
            return null;
        }
    }

}
