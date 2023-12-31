/** Jack Vanlyssel
 *
 * This code implements the typing test mini-game.
 * The showTypingTest() method sets up the user interface
 * for the game, which consists of a text area with some
 * predefined text and another text area where the user
 * can type. The program checks if the user types the
 * text correctly, and if so, it calculates the time
 * taken to complete the task and displays it to the
 * user. The user can save their score or try again.
 */

package Games;

import MainPackage.GameUtility;
import MainPackage.HomeScreenUI;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;


public class TypingTest {
    private final GameUtility gameUtility;
    private final Stage primaryStage;

    public TypingTest(GameUtility gameUtility, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameUtility = gameUtility;
        showTypingTest();
    }

    /**
     * Sets the screen for the Typing Game.
     */
    private void showTypingTest() {
        primaryStage.setTitle("Typing Test");
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #008AD8;");
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ffb347;");
        backButton.setOnAction(event -> {
            new HomeScreenUI(primaryStage, gameUtility);
        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        HBox topBox = new HBox();
        topBox.setSpacing(60);
        topBox.getChildren().addAll(backButton);

        SimpleLongProperty time = new SimpleLongProperty();

        HBox bottomBtnBox = new HBox();
        Button saveScoreButton = new Button("Save Score");
        saveScoreButton.setStyle("-fx-background-color: #ffb347;");
        saveScoreButton.setOnAction(event -> {
            try {
                gameUtility.updateTypingTest(time.get());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            new TypingTest(TypingTest.this.gameUtility, primaryStage);
        });

        Button tryAgainButton = new Button("Try Again");
        tryAgainButton.setStyle("-fx-background-color: #ffb347;");
        tryAgainButton.setOnAction(event -> {
            new TypingTest(TypingTest.this.gameUtility, primaryStage);
        });
        bottomBtnBox.getChildren().addAll(saveScoreButton, tryAgainButton);
        bottomBtnBox.setVisible(false);

        String originalText = "Ancient Greece is known for its significant contributions to the world, including philosophy, " +
                "democracy, and art. The Greeks developed advanced systems of government, education, and culture " +
                "that continue to influence modern society. The Parthenon in Athens, the Olympic Games, and the " +
                "mythological tales of gods and heroes are just a few examples of the lasting legacy of ancient Greece.";

        TextArea a1 = new TextArea(originalText);
        a1.setEditable(false);
        a1.setWrapText(true);
        a1.setStyle("-fx-highlight-fill: #6AC46A;");


        TextArea a2 = new TextArea();
        SimpleBooleanProperty isTyping = new SimpleBooleanProperty();
        isTyping.set(false);
        final int[] currentIndex = {0};
        a2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isTyping.get()) {
                isTyping.setValue(true);
                time.set(System.currentTimeMillis());
            }
            String typedText = a2.getText();
            while (currentIndex[0] < originalText.length() && currentIndex[0] < typedText.length()
                    && originalText.charAt(currentIndex[0]) == typedText.charAt(currentIndex[0])) {
                currentIndex[0]++;
            }
            a1.selectRange(0, currentIndex[0]);

            if (originalText.equals(typedText)) {
                long end = System.currentTimeMillis();
                time.set(end - time.get());
                gridPane.getChildren().clear();
                Label label = new Label("You have taken " + time.get() + " ms.");
                label.setStyle("-fx-text-fill: white;");
                label.setFont(Font.font(36));
                label.setAlignment(Pos.CENTER);
                gridPane.add(label, 0, 0);
                bottomBtnBox.setVisible(true);
            }
        });
        gridPane.add(a1, 0, 0);
        gridPane.add(a2, 0, 1);

        root.setTop(topBox);
        Insets insets = new Insets(20);
        root.setPadding(insets);
        root.setCenter(gridPane);
        root.setBottom(bottomBtnBox);
        primaryStage.setScene(new Scene(root, 600, 400));
    }
}