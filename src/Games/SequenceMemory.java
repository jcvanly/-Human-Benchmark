/** Jack Vanlyssel
 *
 * This code implements Sequence Memory. The game consists of a grid of buttons,
 * and the player must remember the sequence of buttons that light up in a specific
 * order, and then repeat the sequence in the same order. The game has multiple levels,
 * and the difficulty increases as the levels progress. Most of the logic for this game
 * is housed in the button event handlers.
 */
package Games;

import MainPackage.KeyValue;
import MainPackage.GameUtility;
import MainPackage.HomeScreenUI;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SequenceMemory {
    private final GameUtility gameUtility;
    private final Stage primaryStage;
    private List<KeyValue> sequences = new ArrayList<>();
    private SimpleLongProperty score;
    private List<KeyValue> userSequence = new ArrayList<>();

    public SequenceMemory(GameUtility gameUtility, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameUtility = gameUtility;
        showSequenceMemory();
    }

    void showSequenceMemory() {
        primaryStage.setTitle("Sequence Memory");
        BorderPane root = new BorderPane();
        SimpleStringProperty level = new SimpleStringProperty();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        Button[][] buttons = new Button[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SimpleIntegerProperty num = new SimpleIntegerProperty();
                num.set(i + j);
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(70, 70);
                buttons[i][j].setBackground(new Background(new BackgroundFill(Color.web("#2573c1"), CornerRadii.EMPTY, Insets.EMPTY)));
                gridPane.add(buttons[i][j], j, i);
            }
        }

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ffb347;");

        backButton.setOnAction(event -> new HomeScreenUI(primaryStage, gameUtility));
        Label levelLabel = new Label(level.get());
        HBox topBox = new HBox();
        topBox.setSpacing(60);
        topBox.getChildren().addAll(backButton, levelLabel);
        SimpleIntegerProperty levelScore = new SimpleIntegerProperty(1);
        score = new SimpleLongProperty();
        Button saveButton = new Button("Save Score");
        saveButton.setStyle("-fx-background-color: #ffb347;");
        saveButton.setOnAction(event -> {
            try {
                SequenceMemory.this.gameUtility.updateSequenceMemory(levelScore.get() * 100 / 14);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            new SequenceMemory(SequenceMemory.this.gameUtility, primaryStage);
        });

        Button tryAgainButton = new Button("Try Again");
        tryAgainButton.setStyle("-fx-background-color: #ffb347;");
        tryAgainButton.setOnAction(event -> new SequenceMemory(SequenceMemory.this.gameUtility, primaryStage));
        HBox bottomBtnBox = new HBox();
        bottomBtnBox.getChildren().addAll(saveButton, tryAgainButton);
        bottomBtnBox.setVisible(false);
        root.setTop(topBox);
        Insets insets = new Insets(20);
        root.setPadding(insets);
        root.setStyle("-fx-background-color: #008AD8;");
        root.setCenter(gridPane);
        root.setBottom(bottomBtnBox);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(insets);
        primaryStage.setScene(new Scene(root, 600, 400));

        SimpleIntegerProperty num = new SimpleIntegerProperty();
        num.set(1);
        levelLabel.setText("Level:" + levelScore.get());
        levelLabel.setStyle("-fx-text-fill: white;");

        initial(num, levelLabel, buttons, sequences, levelScore);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                KeyValue correctAns = new KeyValue();
                correctAns.row = i;
                correctAns.col = j;
                buttons[i][j].setOnAction(new ActionEventEventHandler(correctAns, root, bottomBtnBox, num, levelScore, levelLabel, buttons));
            }
        }
    }


    private boolean isValidSequence(List<KeyValue> userSequence, List<KeyValue> sequences) {
        if (userSequence.isEmpty() || sequences.isEmpty())
            return false;

        for (int i = 0; i < userSequence.size(); i++) {
            if (!userSequence.get(i).equals(sequences.get(i)))
                return false;
        }
        return true;
    }

    private void playLevel(Label level, Button[][] buttons, List<KeyValue> sequences, SimpleIntegerProperty levelScore) {
        final KeyFrame kf1 = new KeyFrame(Duration.millis(0), e -> {
            level.setText("Level:" + levelScore.get());
        });
        final Timeline timeline = new Timeline(kf1);
        int i = levelScore.get() - 1;

        Random r = new Random();
        SimpleIntegerProperty row = new SimpleIntegerProperty
                (r.nextInt(3));
        SimpleIntegerProperty col = new SimpleIntegerProperty
                (r.nextInt(3));
        KeyValue keyValue = new KeyValue();
        keyValue.col = col.get();
        keyValue.row = row.get();
        sequences.add(keyValue);
        int delay = 500;
        int j = 1;
        for (KeyValue keyValue1 : sequences) {
            final KeyFrame kf = new KeyFrame(Duration.millis(delay), e -> {
                buttons[keyValue1.row][keyValue1.col].setBackground
                        (new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            });
            timeline.getKeyFrames().add(kf);
            delay += 500;
            final KeyFrame kf2 = new KeyFrame(Duration.millis(delay), e -> {
                buttons[keyValue1.row][keyValue1.col].setBackground(new Background(new BackgroundFill(Color.web("#2573c1"), CornerRadii.EMPTY, Insets.EMPTY)));
            });

            timeline.getKeyFrames().add(kf2);
            delay += 500;
            j++;
        }

        Platform.runLater(timeline::play);
    }

    private void initial(SimpleIntegerProperty num, Label level, Button[][] buttons, List<KeyValue> sequences, SimpleIntegerProperty levelScore) {
        final KeyFrame kf1 = new KeyFrame(Duration.millis(0), e -> {
            level.setText("Level:" + levelScore.get());
        });

        final Timeline timeline = new Timeline(kf1);
        Random r = new Random();
        SimpleIntegerProperty row = new SimpleIntegerProperty(r.nextInt(3));
        SimpleIntegerProperty col = new SimpleIntegerProperty(r.nextInt(3));
        KeyValue keyValue = new KeyValue();
        keyValue.col = col.get();
        keyValue.row = row.get();
        sequences.add(keyValue);
        int delay = 500;

        final KeyFrame kf = new KeyFrame(Duration.millis(delay), e -> {
            buttons[keyValue.row][keyValue.col].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        timeline.getKeyFrames().add(kf);

        final KeyFrame kf2 = new KeyFrame(Duration.millis(delay + 500), e -> {
            buttons[keyValue.row][keyValue.col].setBackground(new Background(new BackgroundFill(Color.web("#2573c1"), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        timeline.getKeyFrames().add(kf2);
        Platform.runLater(() -> timeline.play());
    }

    private class ActionEventEventHandler implements EventHandler<ActionEvent> {
        private final KeyValue correctAns;
        private final BorderPane root;
        private final HBox bottomBtnBox;
        private final SimpleIntegerProperty num;
        private final SimpleIntegerProperty levelScore;
        private final Label levelLabel;
        private final Button[][] buttons;

        public ActionEventEventHandler(KeyValue correctAns, BorderPane root,
                                       HBox bottomBtnBox,
                                       SimpleIntegerProperty num,
                                       SimpleIntegerProperty levelScore,
                                       Label levelLabel, Button[][] buttons) {
            this.correctAns = correctAns;
            this.root = root;
            this.bottomBtnBox = bottomBtnBox;
            this.num = num;
            this.levelScore = levelScore;
            this.levelLabel = levelLabel;
            this.buttons = buttons;
        }

        @Override
        public void handle(ActionEvent event) {
            userSequence.add(correctAns);
            if (!isValidSequence(userSequence, sequences)) {
                HBox box = new HBox();
                box.setPrefWidth(root.getPrefWidth());
                Label label = new Label("Your score: " + score.get());
                label.setFont(Font.font(36));
                label.setStyle("-fx-text-fill: white;");
                box.setBackground(new Background(new BackgroundFill(Color.web("#008AD8"), CornerRadii.EMPTY, Insets.EMPTY)));
                box.setAlignment(Pos.CENTER);
                box.getChildren().add(label);
                root.setCenter(box);
                bottomBtnBox.setVisible(true);
            } else {
                // Flash button white
                Button button = buttons[correctAns.row][correctAns.col];
                button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                PauseTransition pause = new PauseTransition(Duration.millis(250));
                pause.setOnFinished(e -> button.setBackground(new Background(new BackgroundFill(Color.web("#2573c1"), CornerRadii.EMPTY, Insets.EMPTY))));
                pause.play();
            }

            if (sequences.size() == userSequence.size()) {
                num.set(num.get() + 1);
                levelScore.set(levelScore.get() + 1);
                score.set(score.get() + levelScore.get());
                userSequence.clear();
                playLevel(levelLabel, buttons, sequences, levelScore);
            }
        }
    }
}