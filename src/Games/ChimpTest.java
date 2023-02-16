/** Jack Vanlyssel
 *
 * This is a Java class that implements the Chimp Test game.
 * The class contains a constructor that initializes the game
 * with a given instance of GameUtility and Stage objects, and
 * a private method showChimpTest() that displays the game window.
 * The top section contains a GridPane with a Back button,
 * Level label, and Lives label. The center section contains
 * a GridPane with a variable number of buttons that the player
 * clicks on in order. The bottom section contains an HBox that
 * contains Save Score and Try Again buttons. The addButtons()
 * method adds the buttons to the center section of the game window.
 * The game ends when the player runs out of lives. The challenge of
 * This game is that the buttons will all turn white after the first
 * level and the user must remember their order. I could not figure
 * out how to get buttons to stay in one place after being deleted.
 */

package Games;

import MainPackage.KeyValue;
import MainPackage.GameUtility;
import MainPackage.HomeScreenUI;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChimpTest{
    private final GameUtility gameUtility;
    private final Stage primaryStage;

    public ChimpTest(GameUtility gameUtility, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameUtility = gameUtility;
        showChimpTest();
    }

    private void showChimpTest() {
        primaryStage.setTitle("Chimp Test");
        BorderPane root = new BorderPane();
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ffb347;");

        backButton.setOnAction(event -> {
            new HomeScreenUI(primaryStage, gameUtility);
        });

        SimpleIntegerProperty lives = new SimpleIntegerProperty(3);
        SimpleIntegerProperty level = new SimpleIntegerProperty(1);
        SimpleIntegerProperty numberOfButtons = new SimpleIntegerProperty(4);

        Label livesLabel = new Label("Lives: " + lives.get());
        livesLabel.setTextFill(Color.WHITE);

        Label levelLabel = new Label("Level: " + level.get());
        levelLabel.setTextFill(Color.WHITE);

        GridPane topBox = new GridPane();
        topBox.setBackground(new Background(new BackgroundFill(Color.web("#008AD8"), null, null)));
        topBox.setHgap(20);
        topBox.addRow(0, backButton, levelLabel, livesLabel);
        GridPane centerBox = new GridPane();
        centerBox.setPrefSize(400, 400);
        centerBox.setBackground(new Background(new BackgroundFill(Color.web("#008AD8"), CornerRadii.EMPTY, Insets.EMPTY)));

        HBox bottomBtnBox = new HBox();
        bottomBtnBox.setBackground(new Background(new BackgroundFill(Color.web("#008AD8"), null, null)));
        List<KeyValue> keyValues = new ArrayList<>();
        addButtons(centerBox, bottomBtnBox, root, lives, level, livesLabel,
                levelLabel, numberOfButtons, keyValues);

        Button saveScoreButton = new Button("Save Score");
        saveScoreButton.setStyle("-fx-background-color: #ffb347;");
        saveScoreButton.setOnAction(event -> {
            try {
                gameUtility.updateChimpTest(level.get());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            new ChimpTest(ChimpTest.this.gameUtility, primaryStage);
        });

        Button tryAgainButton = new Button("Try Again");
        tryAgainButton.setStyle("-fx-background-color: #ffb347;");
        tryAgainButton.setOnAction(event -> {
            new ChimpTest(ChimpTest.this.gameUtility, primaryStage);
        });

        bottomBtnBox.getChildren().addAll(saveScoreButton, tryAgainButton);
        bottomBtnBox.setVisible(false);

        root.setTop(topBox);
        root.setCenter(centerBox);
        root.setBottom(bottomBtnBox);
        primaryStage.setScene(new Scene(root, 600, 400));
    }


    private void addButtons(GridPane centerBox, HBox bottomBtnBox, BorderPane root,
                            SimpleIntegerProperty lives, SimpleIntegerProperty level, Label livesLabel, Label levelLabel,
                            SimpleIntegerProperty numberOfButtons, List<KeyValue> keyValues) {

        Random r = new Random();
        SimpleIntegerProperty counter = new SimpleIntegerProperty(0);
        int gridSize = 15;

        while (keyValues.size() < numberOfButtons.get()) {
            int row = r.nextInt(gridSize);
            int col = r.nextInt(gridSize);
            if (keyValues.stream().noneMatch(kv -> kv.row == row && kv.col == col)) {
                keyValues.add(new KeyValue());
                Button button = new Button(Integer.toString(keyValues.size()));
                button.setTextFill(Color.WHITE);
                button.setBackground(new Background(new BackgroundFill(Color.web("#008AD8"), CornerRadii.EMPTY, Insets.EMPTY)));
                button.setStyle("-fx-border-color: #91b6d4; -fx-border-width: 2px; -fx-border-style: solid;");
                button.setPrefSize(35, 35);
                button.setOnAction(event -> {
                    Platform.runLater(() -> {
                        int val = Integer.parseInt(button.getText());
                        if (val - 1 == counter.get()) {
                            numberOfButtons.set(numberOfButtons.get() - 1);
                            centerBox.getChildren().remove(button);
                            button.setVisible(false);
                            button.setManaged(false);
                            if (level.get() != 1) {
                                centerBox.getChildren().stream().filter(node -> node instanceof Button).forEach(node -> node.setStyle("-fx-text-fill: white"));
                                centerBox.getChildren().stream().filter(node -> node instanceof Button)
                                        .forEach(node -> node.setStyle("-fx-text-fill: white; -fx-background-color: white;"));
                            }
                            counter.set(counter.get() + 1);
                            numberOfButtons.set(keyValues.size());
                        } else {
                            lives.set(lives.get() - 1);
                            livesLabel.setText("Lives: " + lives.get());
                            level.set(level.get() + 1);
                            levelLabel.setText("Level: " + level.get());
                        }
                        if (lives.get() == 0) {
                            root.setCenter(livesLabel);
                            bottomBtnBox.setVisible(true);
                        } else if (centerBox.getChildren().isEmpty()) {
                            level.set(level.get() + 1);
                            levelLabel.setText("Level: " + level.get());
                            numberOfButtons.set(numberOfButtons.get() + 1);
                            keyValues.clear();
                            addButtons(centerBox, bottomBtnBox, root, lives, level, livesLabel, levelLabel, numberOfButtons, keyValues);
                        }
                    });
                });
                centerBox.add(button, col, row);
            }
        }
    }
}