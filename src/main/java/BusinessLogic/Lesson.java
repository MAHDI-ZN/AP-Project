package BusinessLogic;

/**
 * A wrapper class for lessons a student may enroll
 */
public class Lesson {
    private String name;
    private DifficultyLevel difficultyLevel;

    /**
     * allows lessons to be created
     * @param name of the lesson
     * @param difficultyLevel of the lesson
     */
    public Lesson(String name, DifficultyLevel difficultyLevel) {
        this.name = name;
        this.difficultyLevel = difficultyLevel;
    }
}
