/** Jack Vanlyssel
 *
 * This code implements the Aim Trainer game.
 * The class contains a constructor that initializes the game,
 * and a showAimTrainer() method that creates the UI and sets up the game.
 * The UI consists of a BorderPane with a GridPane in the center that contains
 * a button, a label, and a message that tells the user how to play the game.
 * The aim of the game is to click on 15 targets as quickly as possible. Once
 * the game starts, the button is placed randomly in the GridPane and the user
 * must click it. The timer starts as soon as the first click is made. The game
 * ends after 15 clicks, and the user's average time is displayed on the screen.
 * The UI also contains a "Try Again" button and a "Save Score" button that allows
 * the user to play the game again or save their score, respectively.
 */

package Games;

import MainPackage.GameUtility;
import MainPackage.HomeScreenUI;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class AimTrainer {
    private final GameUtility gameUtility;
    private final Stage primaryStage;

    public AimTrainer(GameUtility gameUtility, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameUtility = gameUtility;
        showAimTrainer();
    }

    void showAimTrainer() {
        primaryStage.setTitle("Aim Trainer");
        BorderPane root = new BorderPane();
        HBox bottomBtnBox = new HBox();
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ffb347;");
        SimpleLongProperty total = new SimpleLongProperty();
        backButton.setOnAction(event -> new HomeScreenUI(primaryStage, gameUtility));
        SimpleIntegerProperty targetCounter = new SimpleIntegerProperty(15);
        Label targetLabel = new Label("Remaining: " + targetCounter.get());
        HBox topBox = new HBox();
        topBox.setSpacing(60);
        topBox.getChildren().addAll(backButton, targetLabel);
        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.web("#008AD8"), CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        Random r = new Random();
        Button saveScoreButton = new Button("" + "Save Score");
        saveScoreButton.setStyle("-fx-background-color: #ffb347;");
        saveScoreButton.setOnAction(event -> {
            try {
                AimTrainer.this.gameUtility.updateAimTrainer(total.get() / 15);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            new AimTrainer(AimTrainer.this.gameUtility, primaryStage);
        });
        Button tryAgainButton = new Button("Try Again");
        tryAgainButton.setStyle("-fx-background-color: #ffb347;");
        tryAgainButton.setOnAction(event -> new AimTrainer(AimTrainer.this.gameUtility, primaryStage));

        SimpleBooleanProperty isStarted = new SimpleBooleanProperty(false);
        SimpleLongProperty start = new SimpleLongProperty();

        Button button = new Button();
        button.setPrefSize(25, 25);
        button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        button.setBackground(Background.EMPTY);
        Image image = new Image("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\AimTrainerCrosshair.png");
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        button.setGraphic(imageView);
        button.setOnAction(event -> {
            if (!isStarted.get()) {
                isStarted.set(true);
                start.set(System.currentTimeMillis());
            } else if (targetCounter.get() != 1) {
                long end = System.currentTimeMillis();
                total.set(total.get() + (end - start.get()));
                targetCounter.set(targetCounter.get() - 1);
                targetLabel.setText("Remaining: " + targetCounter.get());
                gridPane.getChildren().clear();
                int row = r.nextInt(10);
                int col = r.nextInt(10);
                gridPane.add(button, col, row);
                start.set(System.currentTimeMillis());
            }
            else {
                targetCounter.set(0);
                targetLabel.setText("Remaining: " + targetCounter.get());
                gridPane.getChildren().clear();
                Label label = new Label((total.get() / 15) + " ms is your average time");
                label.setStyle("-fx-text-fill: white;");
                label.setFont(Font.font(36));
                label.setAlignment(Pos.CENTER);
                GridPane.setHalignment(label, HPos.CENTER);
                GridPane.setValignment(label, VPos.CENTER);
                GridPane.setColumnSpan(label, 3);
                gridPane.add(label, 0, 0);
                bottomBtnBox.setVisible(true);
            }
        });
        Label label = new Label("Click 15 targets as quick as you can!\n " + "   Click target above to start");
        label.setFont(Font.font(24));
        label.setStyle("-fx-text-fill: white;");
        label.setAlignment(Pos.CENTER);
        gridPane.add(button, 2, 2);
        gridPane.add(label, 2, 4);

        bottomBtnBox.getChildren().addAll(saveScoreButton, tryAgainButton);
        bottomBtnBox.setVisible(false);
        root.setTop(topBox);
        Insets insets = new Insets(20);
        root.setPadding(insets);
        root.setCenter(gridPane);
        root.setBottom(bottomBtnBox);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(insets);
        gridPane.setPrefSize(600, 600);
        primaryStage.setScene(new Scene(root, 600, 400));
    }

}