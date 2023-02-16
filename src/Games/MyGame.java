/** Jack Vanlyssel
 *
 * This code implements the ninth test which is a math game.
 * The game asks the user to solve multiplication problems
 * and advances the level with each correct answer. It then
 * reports how quickly the user was able to solve all the
 * problems. The class uses JavaFX to create a GUI with a
 * text field and a submit button for the user to input
 * their answer, and displays the time taken at the end
 * of the game.
 */

package Games;

import MainPackage.GameUtility;
import MainPackage.HomeScreenUI;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;

public class MyGame {
    private final GameUtility gameUtility;
    private final Stage primaryStage;
    private int counter = 0;
    private long duration;

    public MyGame(GameUtility gameUtility, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameUtility = gameUtility;
        showMyGame();
    }

    private void showMyGame() {
        primaryStage.setTitle("My Game");
        BorderPane root = new BorderPane();
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ffb347;");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new HomeScreenUI(primaryStage, gameUtility);
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
                    gameUtility.updateMyGame(duration/5);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                new MyGame
                        (MyGame.this.gameUtility, primaryStage);
            }
        });
        Button tryAgainButton = new Button("Try Again");
        tryAgainButton.setStyle("-fx-background-color: #ffb347;");
        tryAgainButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new MyGame
                        (MyGame.this.gameUtility, primaryStage);
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

        Random r = new Random();
        int l = level.get();
        long startTime = System.currentTimeMillis();
        int num1 = r.nextInt(10) + 1;
        int num2 = r.nextInt(10) + 1;
        int answer = num1 * num2;

        Label label = new Label(num1 + " x " + num2 + " = ");
        label.setFont(Font.font(42));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);
        gridPane.add(label, 1, 0);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        TextField field = new TextField();
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #ffb347;");
        vbox.getChildren().addAll(field, submitButton);
        gridPane.add(vbox, 1, 1);

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (counter == 4) {
                    duration = System.currentTimeMillis() - startTime;
                    gridPane.getChildren().clear();
                    Label gameOverLabel = new Label("    Game Over!\n" +"Average time: "+ (duration/5));
                    gameOverLabel.setFont(Font.font(42));
                    gameOverLabel.setTextFill(Color.WHITE);
                    gameOverLabel.setAlignment(Pos.CENTER);
                    gridPane.add(gameOverLabel, 1, 1);
                    bottomBtnBox.setVisible(true);
                    submitButton.setDisable(true);
                    submitButton.setVisible(false);
                    bottomBtnBox.setVisible(true);
                    return;


                }
                String text = field.getText();
                if (null != text && !text.trim().isEmpty()) {
                    try {
                        int number = Integer.parseInt(field.getText().trim());
                        if (number == answer) {
                            counter++;
                            VBox vbox = new VBox();
                            vbox.setAlignment(Pos.CENTER);
                            vbox.setSpacing(20);
                            Label l1 = new Label("Correct!");
                            l1.setTextFill(Color.WHITE);
                            l1.setFont(Font.font(24));
                            Label l2 = new Label("Level " + level.get());
                            l2.setTextFill(Color.WHITE);
                            l2.setFont(Font.font(24));
                            Button nextButton = new Button();
                            nextButton.setText("Next");
                            nextButton.setStyle("-fx-background-color: #ffb347;");
                            nextButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    level.set(level.get() + 1);
                                    addLabel(gridPane, level, bottomBtnBox);
                                }
                            });
                            gridPane.getChildren().clear();
                            vbox.getChildren().addAll(l1, l2, nextButton);
                            gridPane.add(vbox, 1, 1);
                        }
                        else {
                            //bottomBtnBox.setVisible(true);
                        }
                    } catch (NumberFormatException nfe) {
                    }
                }
                vbox.setAlignment(Pos.CENTER);
                gridPane.setAlignment(Pos.CENTER);
            }
        });
    }
}

