package ee.skyhigh.l1nde.pickayear;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class QuizHolder {
    private List<Question> questions = new ArrayList<>();
    private Stack<Question> used = new Stack<>();

    private int answered = -1;

    public QuizHolder() {
        addQuestion(0, 1999, 4, 1);
        addQuestion(1, 1989, 11, 17);
        Collections.shuffle(questions);
    }

    public Question getNextQuestion(){
        answered += 1;
        if (questions.size() == 0){
            questions.addAll(used);
            used = new Stack<>();
            return null;
        }
        Question question = questions.remove(0);
        used.add(question);
        return question;
    }

    public int getAnswered(){
        return answered;
    }

    public Question getLast(){
        return used.peek();
    }

    private void addQuestion(int path, int year, int month, int day){
        questions.add(new Question("question_" + path, year, month, day));
    }
}
