package ee.skyhigh.l1nde.pickayear.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import ee.skyhigh.l1nde.pickayear.data.entites.ScoreEntity;
import ee.skyhigh.l1nde.pickayear.data.repositories.ScoreRepository;


public class ScoreViewModel extends AndroidViewModel {

    private ScoreRepository scoreRepository;
    private LiveData<List<ScoreEntity>> scores;

    public ScoreViewModel(Application application) {
        super(application);
        scoreRepository = new ScoreRepository(application);
        scores = scoreRepository.getScores();
    }

    public LiveData<List<ScoreEntity>> getScores() {
        return scores;
    }

    public void insert(ScoreEntity scoreEntity){
        scoreRepository.insert(scoreEntity);
    }
}
