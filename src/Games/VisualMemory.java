package Games;

import MainPackage.KeyValue;
import MainPackage.GameUtility;
import MainPackage.HomeScreenUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VisualMemory {
    private final GameUtility gameUtility;
    private final Stage primaryStage;

    public VisualMemory(GameUtility gameUtility, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameUtility = gameUtility;
        showVisualMemory();
    }

    private void showVisualMemory() {
        primaryStage.setTitle("Visual Memory");
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #008AD8;");
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ffb347;");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new HomeScreenUI(primaryStage, gameUtility);
            }
        });
        SimpleIntegerProperty lives = new SimpleIntegerProperty(3);
        SimpleIntegerProperty level = new SimpleIntegerProperty(1);

        Label livesLabel = new Label("Lives " + lives.get());
        Label levelLabel = new Label("Level " + level.get());

        GridPane topBox = new GridPane();
        topBox.setHgap(20);
        topBox.add(backButton, 0, 0);
        topBox.add(levelLabel, 1, 0);
        topBox.add(livesLabel, 2, 0);

        GridPane centerBox = new GridPane();
        centerBox.setHgap(20);
        centerBox.setVgap(20);
        Button[][] buttons = new Button[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(100, 100);
                buttons[i][j].setBackground(new Background(new BackgroundFill
                        (Color.web("#2573c1"), CornerRadii.EMPTY, Insets.EMPTY)));
                centerBox.add(buttons[i][j], j, i);
            }
        }


        HBox bottomBtnBox = new HBox();
        Button saveScoreBtn = new Button("Save Score");
        saveScoreBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    gameUtility.updateVisualMemory(level.get());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                new VisualMemory(VisualMemory.this.gameUtility,
                        primaryStage);
            }
        });
        Button tryAgainBtn = new Button("Try Again");

        tryAgainBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new VisualMemory(VisualMemory.this.gameUtility, primaryStage);
            }
        });
        bottomBtnBox.getChildren().addAll(saveScoreBtn, tryAgainBtn);
        bottomBtnBox.setVisible(false);

        root.setTop(topBox);
        Insets insets = new Insets(20);
        root.setPadding(insets);
        centerBox.setPadding(insets);
        root.setCenter(centerBox);
        root.setBottom(bottomBtnBox);
        primaryStage.setScene(new Scene(root, 600, 400));

        List<KeyValue> selectedChoices = new ArrayList<>();
        SimpleIntegerProperty numberOfSequences = new SimpleIntegerProperty(3);
        playSequence(buttons, numberOfSequences, selectedChoices,levelLabel,level);
        SimpleIntegerProperty correctAns = new SimpleIntegerProperty();
        SimpleIntegerProperty wrongAns = new SimpleIntegerProperty();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                SimpleIntegerProperty row = new SimpleIntegerProperty(i);
                SimpleIntegerProperty col = new SimpleIntegerProperty(j);

                buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        if (!isExist(row.get(),col.get(),selectedChoices)) {
                            wrongAns.set(wrongAns.get() + 1);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    buttons[row.get()][col.get()].setBackground
                                            (new Background(new BackgroundFill
                                                    (Color.RED,
                                                            CornerRadii.EMPTY,
                                                            Insets.EMPTY)));

                                }
                            });

                        } else {
                            correctAns.set(correctAns.get() + 1);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    buttons[row.get()][col.get()].setBackground
                                            (new Background(new BackgroundFill
                                                    (Color.GREEN,
                                                            CornerRadii.EMPTY,
                                                            Insets.EMPTY)));

                                }
                            });
                        }
                        if (wrongAns.get() > numberOfSequences.get() / 2) {
                            lives.set(lives.get() - 1);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    livesLabel.setText("Lives:" + lives.get());
                                }
                            });

                        }

                        if (lives.get() == 0) {
                            root.setCenter(levelLabel);
                            bottomBtnBox.setVisible(true);
                        } else {
                            System.out.println(correctAns.get() +":"+
                                    numberOfSequences.get());
                            if (correctAns.get() == numberOfSequences.get()) {
                                level.set(level.get() + 1);
                                numberOfSequences.set
                                        (numberOfSequences.get() + 1);
                                selectedChoices.clear();

                                for (int i = 0; i < 5; i++) {
                                    SimpleIntegerProperty row =
                                            new SimpleIntegerProperty(i);
                                    for (int j = 0; j < 5; j++) {
                                        SimpleIntegerProperty col =
                                                new SimpleIntegerProperty(j);
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[row.get()][col.get()].
                                                        setBackground(new
                                                                Background
                                                                (new BackgroundFill
                                                                        (Color.BLUE,
                                                                                CornerRadii.EMPTY,
                                                                                Insets.EMPTY)));
                                            }
                                        });
                                    }
                                }
                                playSequence(buttons, numberOfSequences,
                                        selectedChoices,levelLabel,level);
                            }
                        }
                    }
                });
            }
        }
    }

    private boolean isExist(int row, int col, List<KeyValue> selectedChoices) {
        for (KeyValue keyValue:selectedChoices){
            if(row==keyValue.row && col==keyValue.col)
                return true;
        }
        return false;
    }

    private void playSequence(Button[][] buttons, SimpleIntegerProperty numberOfSequences,
                              List<KeyValue> selectedChoices,Label level,
                              SimpleIntegerProperty levelScore) {
        final Timeline timeline = new Timeline();
        final KeyFrame kf = new KeyFrame(Duration.millis(0), e -> {
            level.setText("Level:"+levelScore.get());
        });
        timeline.getKeyFrames().add(kf);

        for (int i = 0; i < numberOfSequences.get(); i++) {
            Random r = new Random();
            SimpleIntegerProperty row = new SimpleIntegerProperty();
            SimpleIntegerProperty col = new SimpleIntegerProperty(r.nextInt
                    (4));
            KeyValue keyValue = new KeyValue();
            keyValue.row = r.nextInt(4);
            keyValue.col = r.nextInt(4);
            selectedChoices.add(keyValue);
        }
        int delay = 1000;
        for (KeyValue keyValue : selectedChoices) {
            final KeyFrame kf1 = new KeyFrame(Duration.millis(delay), e -> {
                buttons[keyValue.row][keyValue.col].setBackground
                        (new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            });
            timeline.getKeyFrames().add(kf1);

            delay += 1000;
            final KeyFrame kf2 = new KeyFrame(Duration.millis(delay), e -> {
                buttons[keyValue.row][keyValue.col].setBackground
                        (new Background(new BackgroundFill(Color.web("#2573c1"), CornerRadii.EMPTY, Insets.EMPTY)));
            });
            timeline.getKeyFrames().add(kf2);
            delay += 1000;
        }
        Platform.runLater(timeline::play);
    }
}

