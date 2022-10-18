// Imports
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.List;

public class GameView {
    // Properties
    private final Stage primaryStage;


    // Constructor
    public GameView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Methods
    public void present(List<String> shuffledFlags, int correctAnswer, Game game) {
        // Outer VBOX to hold content
        VBox outerVBox = new VBox();
        outerVBox.setStyle("-fx-background-color: radial-gradient(center 50% 0% , radius 80% , #4040E0, #080840)");
        outerVBox.setAlignment(Pos.CENTER);
        outerVBox.setSpacing(20);

        // Text for title with dropShadow
        Text title = new Text("Guess the Flag");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.BLACK);
        title.setEffect(dropShadow);
        title.setStyle("-fx-font: bold 20pt \"Arial\"");
        title.setFill(Color.WHITE);

        // Text for instruction with dropShadow
        Text instruction = new Text("Tap the flag of " + shuffledFlags.get(correctAnswer));
        DropShadow dropShadowSmall = new DropShadow();
        dropShadowSmall.setOffsetY(1.0);
        dropShadowSmall.setColor(Color.BLACK);
        instruction.setEffect(dropShadowSmall);
        instruction.setStyle("-fx-font: bold 12pt \"Arial\"");
        instruction.setFill(Color.WHITE);

        // Inner VBox to hold the flags (buttons)
        VBox innerVBox = new VBox();
        innerVBox.setAlignment(Pos.CENTER);

        // Add buttonOne
        Button buttonOne = new Button("", new ImageView(shuffledFlags.get(0).concat(".jpg")));
        buttonOne.setStyle("-fx-background-color:transparent");
        buttonOne.setOnAction(event -> {
            game.checkAnswer(0);
        });

        // Add buttonTwo
        Button buttonTwo = new Button("", new ImageView(shuffledFlags.get(1)+".jpg"));
        buttonTwo.setStyle("-fx-background-color:transparent");
        buttonTwo.setOnAction(event -> {
            game.checkAnswer(1);
        });

        // Add buttonThree
        Button buttonThree = new Button("", new ImageView(shuffledFlags.get(2)+".jpg"));
        buttonThree.setStyle("-fx-background-color:transparent");
        buttonThree.setOnAction(event -> {
            game.checkAnswer(2);
        });

        // Add child nodes to innerVBox
        innerVBox.getChildren().addAll(buttonOne, buttonTwo, buttonThree);

        // Text for instruction with dropShadow
        Text scoreText = new Text("Score: " + game.getScore());
        scoreText.setEffect(dropShadowSmall);
        scoreText.setStyle("-fx-font: bold 12pt \"Arial\"");
        scoreText.setFill(Color.WHITE);

        // Add child nodes to outerVBox
        outerVBox.getChildren().addAll(title, instruction, innerVBox, scoreText);

        // Create a new scene and load outerVBox into it and show the stage
        Scene scene  = new Scene(outerVBox, 400, 740);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
