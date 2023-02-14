package Games;

import MainPackage.GameUtility;
import MainPackage.HomeScreenUI;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        showAimTrainerGameScreen();
    }

    void showAimTrainerGameScreen() {
        BorderPane root = new BorderPane();
        HBox bottomBtnBox = new HBox();
        Button backBtn = new Button("Back");
        SimpleLongProperty total = new SimpleLongProperty();
        backBtn.setOnAction(event -> new HomeScreenUI(primaryStage, gameUtility));
        SimpleIntegerProperty targetCounter = new SimpleIntegerProperty(15);
        Label targetLabel = new Label("Remaining: " + targetCounter.get());
        HBox topBox = new HBox();
        topBox.setSpacing(60);
        topBox.getChildren().addAll(backBtn, targetLabel);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        Random r = new Random();
        Button saveScoreBtn = new Button("Save Score");
        saveScoreBtn.setOnAction(event -> {
            try {
                AimTrainer.this.gameUtility.updateAimTrainer(total.get() / 15);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            new AimTrainer(AimTrainer.this.gameUtility, primaryStage);
        });
        Button tryAgainBtn = new Button("Try Again");
        tryAgainBtn.setOnAction(event -> new AimTrainer(AimTrainer.this.gameUtility, primaryStage));

        SimpleBooleanProperty isStarted = new SimpleBooleanProperty(false);
        SimpleLongProperty start = new SimpleLongProperty();

        Button button = new Button();
        button.setPrefSize(25, 25);
        button.setBackground(new Background(new BackgroundFill(Color.web("#008AD8"), CornerRadii.EMPTY, Insets.EMPTY)));
        button.setOnAction(event -> {
            if (!isStarted.get()) {
                isStarted.set(true);
                start.set(System.currentTimeMillis());
            } else if (targetCounter.get() != 0) {
                long end = System.currentTimeMillis();
                total.set(total.get() + (end - start.get()));
                targetCounter.set(targetCounter.get() - 1);
                targetLabel.setText("Remaining:" + targetCounter.get());
                gridPane.getChildren().clear();
                int row = r.nextInt(10);
                int col = r.nextInt(10);
                gridPane.add(button, col, row);
                start.set(System.currentTimeMillis());
            } else {
                gridPane.getChildren().clear();
                Label label = new Label((total.get() / 15) + " ms is your average time");
                label.setFont(Font.font(24));
                label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                label.setAlignment(Pos.CENTER);
                gridPane.add(label, 2, 4);
                bottomBtnBox.setVisible(true);
            }
        });
        Label label = new Label("Click 15 targets as quick as you can!\n " + "   Click above target to start");
        label.setFont(Font.font(24));
        label.setAlignment(Pos.CENTER);
        gridPane.add(button, 2, 2);
        gridPane.add(label, 2, 4);

        bottomBtnBox.getChildren().addAll(saveScoreBtn, tryAgainBtn);
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