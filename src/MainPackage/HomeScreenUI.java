/**
 *
 */

package MainPackage;

import Games.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeScreenUI {

    private final Stage primaryStage;
    private final GameUtility gameUtil;

    public HomeScreenUI(Stage primaryStage, GameUtility gameUtil) {
        this.primaryStage = primaryStage;
        this.gameUtil = gameUtil;
        createHomeScreen();

    }

    private void createHomeScreen() {

        GridPane root = new GridPane();
        root.setMinSize(400, 200);
        root.setPadding(new Insets(30, 10, 10, 10));
        root.setVgap(20);
        root.setHgap(20);
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#008AD8"), null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
        root.setAlignment(Pos.TOP_CENTER);


        Button reactionTimeButton = new Button("Reaction Time");
        reactionTimeButton.setPrefSize(150, 100);
        root.add(reactionTimeButton, 0, 0);
        reactionTimeButton.setStyle("-fx-background-color: white;");
        reactionTimeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new ReactionTime(gameUtil , primaryStage);            }
        });

        Button sequenceMemoryButton = new Button("Sequence Memory");
        sequenceMemoryButton.setPrefSize(150, 100);
        root.add(sequenceMemoryButton, 1, 0);
        sequenceMemoryButton.setStyle("-fx-background-color: white;");
        sequenceMemoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new SequenceMemory(gameUtil , primaryStage);            }
        });


        Button aimTrainerButton = new Button("Aim Trainer");
        aimTrainerButton.setPrefSize(150, 100);
        root.add(aimTrainerButton, 2, 0);
        aimTrainerButton.setStyle("-fx-background-color: white;");
        aimTrainerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new AimTrainer(gameUtil , primaryStage);            }
        });


        Button chimpTestButton = new Button("Chimp Test");
        chimpTestButton.setPrefSize(150, 100);
        root.add(chimpTestButton, 0, 1);
        chimpTestButton.setStyle("-fx-background-color: white;");
        chimpTestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new ChimpTest(gameUtil , primaryStage);            }
        });

        Button visualMemoryButton = new Button("Visual Memory");
        visualMemoryButton.setPrefSize(150, 100);
        root.add(visualMemoryButton, 1, 1);
        visualMemoryButton.setStyle("-fx-background-color: white;");
        visualMemoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new VisualMemory(gameUtil , primaryStage);            }
        });


        Button typingTestButton = new Button("Typing");
        typingTestButton.setPrefSize(150, 100);
        root.add(typingTestButton, 2, 1);
        typingTestButton.setStyle("-fx-background-color: white;");
        typingTestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new TypingTest(gameUtil , primaryStage);            }
        });


        Button numberMemoryButton = new Button("Number Memory");
        numberMemoryButton.setPrefSize(150, 100);
        root.add(numberMemoryButton, 0, 2);
        numberMemoryButton.setStyle("-fx-background-color: white;");


        Button verbalMemoryButton = new Button("Verbal Memory");
        verbalMemoryButton.setPrefSize(150, 100);
        root.add(verbalMemoryButton, 1, 2);
        verbalMemoryButton.setStyle("-fx-background-color: white;");


        Button myGameButton = new Button("My Game");
        myGameButton.setPrefSize(150, 100);
        root.add(myGameButton, 2, 2);
        myGameButton.setStyle("-fx-background-color: white;");


        primaryStage.setTitle("Human Benchmark");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}