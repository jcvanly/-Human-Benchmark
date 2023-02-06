/**
 *
 */

package MainPackage;

import Games.ReactionTime;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
        root.setAlignment(Pos.TOP_CENTER);

        Button reactionTimeButton = new Button("Reaction Time");
        reactionTimeButton.setPrefSize(150, 100);
        root.add(reactionTimeButton, 0, 0);
        reactionTimeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new ReactionTime(gameUtil , primaryStage);            }
        });

        Button sequenceMemoryButton = new Button("Sequence Memory");
        sequenceMemoryButton.setPrefSize(150, 100);
        root.add(sequenceMemoryButton, 1, 0);

        Button aimTrainerButton = new Button("Aim Trainer");
        aimTrainerButton.setPrefSize(150, 100);
        root.add(aimTrainerButton, 2, 0);

        Button chimpTestButton = new Button("Chimp Test");
        chimpTestButton.setPrefSize(150, 100);
        root.add(chimpTestButton, 0, 1);

        Button visualMemoryButton = new Button("Visual Memory");
        visualMemoryButton.setPrefSize(150, 100);
        root.add(visualMemoryButton, 1, 1);

        Button typingButton = new Button("Typing");
        typingButton.setPrefSize(150, 100);
        root.add(typingButton, 2, 1);

        Button numberMemoryButton = new Button("Number Memory");
        numberMemoryButton.setPrefSize(150, 100);
        root.add(numberMemoryButton, 0, 2);

        Button verbalMemoryButton = new Button("Verbal Memory");
        verbalMemoryButton.setPrefSize(150, 100);
        root.add(verbalMemoryButton, 1, 2);

        Button myGameButton = new Button("My Game");
        myGameButton.setPrefSize(150, 100);
        root.add(myGameButton, 2, 2);

        primaryStage.setTitle("Human Benchmark");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}