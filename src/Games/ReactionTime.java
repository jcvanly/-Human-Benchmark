/** Jack Vanlyssel
 *
 */

package Games;

import MainPackage.GameUtility;
import MainPackage.HomeScreenUI;
import javafx.animation.KeyFrame;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;

public class ReactionTime {
    private final GameUtility gameUtility;
    private final Stage primaryStage;
    private int currentState = 1;

    public ReactionTime(GameUtility gameUtility, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameUtility = gameUtility;
        showReactionTimerGameScreen();
    }

    private void showReactionTimerGameScreen() {
        primaryStage.setTitle("Reaction Time");
        BorderPane root = new BorderPane();

        HBox hBox = new HBox();
        hBox.setPrefWidth(this.primaryStage.getWidth());
        SimpleStringProperty text = new SimpleStringProperty();
        SimpleLongProperty start = new SimpleLongProperty();
        SimpleIntegerProperty counter = new SimpleIntegerProperty();
        SimpleLongProperty score = new SimpleLongProperty();

        text.set("Reaction Time");
        Label label = new Label();
        label.setPrefWidth(this.primaryStage.getWidth());
        label.setFont(Font.font(34));
        label.setAlignment(Pos.CENTER);
        label.textProperty().bind(text);
        hBox.getChildren().add(label);
        label.setTextFill(Color.WHITE);
        hBox.setBackground(new Background(new BackgroundFill(Color.web("#008AD8"), CornerRadii.EMPTY, Insets.EMPTY)));

        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new HomeScreenUI(primaryStage, gameUtility);
            }
        });

        HBox topBox= new HBox();
        topBox.setSpacing(20);
        topBox.getChildren().addAll(backButton);

        root.setTop(topBox);
        Insets insets = new Insets(10);
        root.setPadding(insets);
        root.setCenter(hBox);

        hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                final KeyFrame clickScene = new KeyFrame(Duration.millis(5000), e ->{ hBox.setBackground(new Background(new BackgroundFill(Color.web("#6AC46A"), CornerRadii.EMPTY, Insets.EMPTY)));
                    currentState = 3;
                    text.set("Click!");
                    start.set(System.currentTimeMillis());
                });

                final Timeline timeline = new Timeline(clickScene);

                if (currentState == 1) {
                    text.set("Wait to become green");
                    hBox.setBackground(new Background(new BackgroundFill (Color.web("#AB2328"), CornerRadii.EMPTY, Insets.EMPTY)));
                    currentState = 2;

                    timeline.play();
                }

                else  if (currentState == 2) {
                    text.set("Too soon, click to try again!");
                    hBox.setBackground(new Background(new BackgroundFill (Color.web("#008AD8"), CornerRadii.EMPTY, Insets.EMPTY)));
                    timeline.stop();


                }

                else if(currentState == 3) {
                    counter.set(counter.get() + 1);
                    long end = System.currentTimeMillis();
                    score.set(end - start.get());
                    System.out.println("Score:" + counter.get());


                        text.set("You replied in " + score.get() +
                                " ms, click to try again");
                        hBox.setBackground(new Background(new BackgroundFill (Color.web("#008AD8"), CornerRadii.EMPTY, Insets.EMPTY)));
                        currentState = 1;
                }
            }
        });

        primaryStage.setScene(new Scene(root, 600, 400));
    }
}


