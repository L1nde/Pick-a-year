package ee.skyhigh.l1nde.pickayear.data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import ee.skyhigh.l1nde.pickayear.data.dao.ScoreDao;
import ee.skyhigh.l1nde.pickayear.data.entites.ScoreEntity;

@Database(entities = {ScoreEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ScoreDao scoreDao();

    public static AppDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "leaderboard").fallbackToDestructiveMigration().addCallback(scoreDbCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback scoreDbCallback = new RoomDatabase.Callback(){

        @Override
        public  void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
//            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void>{

        private ScoreDao scoreDao;

        PopulateDBAsync(AppDatabase appDatabase){
            scoreDao = appDatabase.scoreDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            scoreDao.deleteAll();
            return null;
        }
    }

}
