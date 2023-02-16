package Games;

/** Jack Vanlyssel
 *
 *  This code implements a Number Memory game, where the
 *   player needs to memorize a randomly generated number
 *   and then input it after a certain amount of time has
 *   elapsed. The game has multiple levels, with the length
 *   of the generated number increasing with each level. The
 *   code also includes options to save the player's score
 *   and restart the game.
 */

import MainPackage.GameUtility;
import MainPackage.HomeScreenUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class NumberMemory {
    private final GameUtility gameUtility;
    private final Stage primaryStage;

    public NumberMemory(GameUtility gameUtility, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameUtility = gameUtility;
        showNumberMemoryGameScreen();
    }

    private void showNumberMemoryGameScreen() {
        primaryStage.setTitle("Number Memory");
        BorderPane root = new BorderPane();
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ffb347;");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new HomeScreenUI(primaryStage,gameUtility);
            }
        });

        HBox topBox = new HBox();
        topBox.setSpacing(60);
        topBox.getChildren().addAll(backButton);
        SimpleIntegerProperty level = new SimpleIntegerProperty();
        level.set(1);

        HBox bottomBtnBox = new HBox();
        Button saveScoreButton = new Button("Save Score");
        saveScoreButton.setStyle("-fx-background-color: #ffb347;");
        saveScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    gameUtility.updateNumberMemory(level.get());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                new NumberMemory
                        (NumberMemory.this.gameUtility, primaryStage);
            }
        });
        Button tryAgainButton = new Button("Try Again");
        tryAgainButton.setStyle("-fx-background-color: #ffb347;");
        tryAgainButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new NumberMemory
                        (NumberMemory.this.gameUtility, primaryStage);
            }
        });
        bottomBtnBox.getChildren().addAll(saveScoreButton, tryAgainButton);
        bottomBtnBox.setVisible(false);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        addLabel(gridPane, level, bottomBtnBox);

        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(gridPane);

        root.setTop(topBox);
        Insets insets = new Insets(20);
        root.setPadding(insets);
        root.setCenter(centerBox);
        root.setBottom(bottomBtnBox);
        root.setStyle("-fx-background-color: #008AD8;");

        primaryStage.setScene(new Scene(root, 600, 400));
    }
    private void addLabel(GridPane gridPane, SimpleIntegerProperty level, HBox bottomBtnBox) {
        gridPane.getChildren().clear();

        SimpleLongProperty num = new SimpleLongProperty();

        final KeyFrame kf1 = new KeyFrame(Duration.millis(0), e -> {
            Random r = new Random();
            long l = level.get();
            if (l == 1) {
                num.set(r.nextInt(9) + 1);
            } else if (l == 2) {
                num.set(r.nextInt(99) + 10);
            } else if (l == 3) {
                num.set(r.nextInt(999) + 100);
            } else if (l == 4) {
                num.set(r.nextInt(9999) + 1000);
            } else if (l == 5) {
                num.set(r.nextInt(99999) + 10000);
            } else if (l == 6) {
                num.set(r.nextInt(999999) + 100000);
            } else if (l == 7) {
                num.set(r.nextInt(9999999) + 100000);
            }
            Label label = new Label(""+num.get());
            label.setFont(Font.font(42));
            label.setTextFill(Color.WHITE);
            label.setAlignment(Pos.CENTER);
            gridPane.add(label, 1, 0);
        });


        final KeyFrame kf2 = new KeyFrame(Duration.millis(2000), e -> {
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(20);
            Label label = new Label("What was the number?");
            label.setFont(Font.font(36));
            label.setTextFill(Color.WHITE);
            TextField field = new TextField();
            Button submitButton = new Button("Submit");
            submitButton.setStyle("-fx-background-color: #ffb347;");
            vbox.getChildren().addAll(label,field,submitButton);
            gridPane.getChildren().clear();
            gridPane.add(vbox, 1, 1);
            submitButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String text = field.getText();
                    if (null != text && !text.trim().isEmpty()) {
                        try {
                            long number = Long.parseLong(field.getText().trim());
                            if (number == num.get()) {
                                VBox vbox = new VBox();
                                vbox.setAlignment(Pos.CENTER);
                                vbox.setSpacing(20);
                                Label l1 = new Label("Number");
                                l1.setTextFill(Color.WHITE);
                                l1.setFont(Font.font(24));
                                Label l2 = new Label("" + num.get());
                                l2.setFont(Font.font(24));
                                Label l3 = new Label("Your Number");
                                l3.setFont(Font.font(24));
                                l2.setTextFill(Color.WHITE);
                                l3.setTextFill(Color.WHITE);
                                Label l4 = new Label("" + number);
                                l4.setFont(Font.font(24));
                                l4.setTextFill(Color.WHITE);
                                Label l5 = new Label("Level " + level.get());
                                l5.setTextFill(Color.WHITE);
                                l5.setFont(Font.font(24));
                                Button nextButton =new Button();
                                nextButton.setText("Next");
                                nextButton.setStyle("-fx-background-color: #ffb347;");
                                nextButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        level.set(level.get()+1);
                                        addLabel(gridPane, level, bottomBtnBox);
                                    }
                                });
                                gridPane.getChildren().clear();
                                vbox.getChildren().addAll(l1,l2,l3,l4,l5,nextButton);
                                gridPane.add(vbox, 1, 1);
                            } else {
                                bottomBtnBox.setVisible(true);
                            }
                        } catch (NumberFormatException nfe) {
                        }
                    }
                    vbox.setAlignment(Pos.CENTER);
                    gridPane.setAlignment(Pos.CENTER);
                }
            });
        });

        final Timeline timeline = new Timeline(kf1, kf2);
        Platform.runLater(timeline::play);

    }
}
