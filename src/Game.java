// Imports
import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Game {
    // Properties
    private List<String> listOfFlags = new ArrayList<>(
            Arrays.asList(  "Afghanistan", "Albania", "Algeria", "Australia", "Belgium",
                            "Bolivia", "Brazil", "Bulgaria", "Canada", "Chile", "China",
                            "Colombia", "Croatia", "Denmark", "Estonia", "France", "Germany",
                            "Greece", "India", "Japan", "Mexico",
                            "Netherlands", "Russia", "Sweden", "Ukraine"
            ));

    private int correctAnswer;
    private GameView gameView;
    private int score;
    private Alert alert;

    // Constructor
    public Game(GameView theGameView) {
        this.gameView = theGameView;
        this.score = 0;
    }

    // Methods
    public void startGame() {
        Collections.shuffle(listOfFlags);
        correctAnswer = getRandomCorrectAnswer();
        gameView.present(listOfFlags, correctAnswer, this);
    }

    public void checkAnswer(int theAnswer) {
        if (theAnswer == correctAnswer) {
            score ++;
            showResult(true, theAnswer);
        } else {
            showResult(false, theAnswer);
        }
    }

    public void showResult(boolean answerWasCorrect, int theAnswer) {
        if(answerWasCorrect) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Correct!");
            alert.setHeaderText("Your score is now " + score);
            alert.setContentText("Press Ok to continue!");
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK) {
                nextQuestion();
            } else {
                System.exit(0);
            }
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Wrong!");
            alert.setHeaderText("That was the flag of " + listOfFlags.get(theAnswer) + "\nYour score is still " + score);
            alert.setContentText("Press Ok to continue!");
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK) {
                nextQuestion();
            } else {
                System.exit(0);
            }
        }
    }

    public void nextQuestion() {
        Collections.shuffle(listOfFlags);
        correctAnswer = getRandomCorrectAnswer();
        gameView.present(listOfFlags, correctAnswer, this);
    }

    public int getRandomCorrectAnswer() {
        return (int)(Math.random()*3);
    }

    public int getScore() {
        return score;
    }
}
