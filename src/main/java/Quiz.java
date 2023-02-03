import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is a wrapper class to ask and answer quizzes from students to validate their studies
 */
public class Quiz {
    private ArrayList<String> questions = new ArrayList<>();
    private int duration;
    private boolean isValid;
    private ArrayList<String> answers = new ArrayList<>();
    private DifficultyLevel difficultyLevel;

    /**
     * makes a quiz with specified duration
     * @param duration of the quiz
     */
    public Quiz(int duration) {
        this.duration = duration;
    }

    /**
     * returns answers of students
     * @return Answers
     */
    public ArrayList<String> getAnswers() {
        return answers;
    }

    /**
     * returns questions of this quiz
     * @return Questions
     */
    public ArrayList<String> getQuestions() {
        return questions;
    }

    /**
     * returns duration of this quiz in seconds
     * @return Duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * declares the quiz invalid because it is not done in the specified deadline
     */
    public void setValid(){
        isValid = true;
    }

    /**
     * declares the quiz valid
     */
    public void setInvalid(){
        isValid = false;
    }
}
