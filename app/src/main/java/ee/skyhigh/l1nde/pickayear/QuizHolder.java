package ee.skyhigh.l1nde.pickayear;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Queue;


public class QuizHolder {
    private List<Question> questions = new ArrayList<>();
    private Queue<Question> used =  new ArrayDeque<>();

    public QuizHolder() {
        addQuestion(0, 1999, 4, 1);
        addQuestion(1, 1989, 11, 17);
        Collections.shuffle(questions);
    }

    public Question getNextQuestion(){
        if (questions.size() == 0){
            questions.addAll(used);
            used = new ArrayDeque<>();
            return null;
        }
        Question question = questions.remove(0);
        used.add(question);
        return question;
    }

    public Question getLast(){
        return used.peek();
    }

    private void addQuestion(int path, int year, int month, int day){
        questions.add(new Question("question_" + path, year, month, day));
    }
}
