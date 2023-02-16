/** Jack Vanlyssel
 *
 * This code defines a JavaFX user interface for the home screen.
 * The UI consists of a grid of buttons that correspond to different
 * tests, each with an icon and a label. The code sets up event handlers
 * for each button, which create and display a new instance of the corresponding
 * game when the button is clicked. The UI is created using a GridPane layout.
 * The UI is displayed in a new window and the primary stage of the UI is passed
 * in as a parameter to the constructor of this class.
 */

package MainPackage;

import Games.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#008AD8"), null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
        root.setAlignment(Pos.TOP_CENTER);

        Button reactionTimeButton = new Button();
        reactionTimeButton.setPrefSize(150, 100);
        root.add(reactionTimeButton, 0, 0);
        reactionTimeButton.setStyle("-fx-background-color: white;");
        Image image1 = new Image("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\reactionTime.png");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(75);
        imageView1.setFitWidth(100);
        Label label1 = new Label("Reaction Time");
        label1.setStyle("-fx-text-fill: black;");
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(imageView1, label1);
        vBox1.setAlignment(Pos.CENTER);
        reactionTimeButton.setGraphic(vBox1);
        reactionTimeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new ReactionTime(gameUtil , primaryStage);            }
        });



        Button sequenceMemoryButton = new Button();
        sequenceMemoryButton.setPrefSize(150, 100);
        root.add(sequenceMemoryButton, 1, 0);
        sequenceMemoryButton.setStyle("-fx-background-color: white;");
        Image image2 = new Image("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\sequenceMemory.png");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(75);
        imageView2.setFitWidth(100);
        Label label2 = new Label("Sequence Memory");
        label2.setStyle("-fx-text-fill: black;");
        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(imageView2, label2);
        vBox2.setAlignment(Pos.CENTER);
        sequenceMemoryButton.setGraphic(vBox2);
        sequenceMemoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new SequenceMemory(gameUtil , primaryStage);            }
        });


        Button aimTrainerButton = new Button();
        aimTrainerButton.setPrefSize(150, 100);
        root.add(aimTrainerButton, 2, 0);
        aimTrainerButton.setStyle("-fx-background-color: white;");
        Image image3 = new Image("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\aimTrainer.png");
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitHeight(75);
        imageView3.setFitWidth(100);
        Label label3 = new Label("Aim Trainer");
        label3.setStyle("-fx-text-fill: black;");
        VBox vBox3 = new VBox();
        vBox3.getChildren().addAll(imageView3, label3);
        vBox3.setAlignment(Pos.CENTER);
        aimTrainerButton.setGraphic(vBox3);
        aimTrainerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new AimTrainer(gameUtil , primaryStage);            }
        });


        Button chimpTestButton = new Button();
        chimpTestButton.setPrefSize(150, 100);
        root.add(chimpTestButton, 0, 1);
        chimpTestButton.setStyle("-fx-background-color: white;");
        Image image4 = new Image("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\sequenceMemory.png");
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitHeight(75);
        imageView4.setFitWidth(100);
        Label label4 = new Label("Chimp Test");
        label4.setStyle("-fx-text-fill: black;");
        VBox vBox4 = new VBox();
        vBox4.getChildren().addAll(imageView4, label4);
        vBox4.setAlignment(Pos.CENTER);
        chimpTestButton.setGraphic(vBox4);
        chimpTestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new ChimpTest(gameUtil , primaryStage);            }
        });

        Button visualMemoryButton = new Button();
        visualMemoryButton.setPrefSize(150, 100);
        root.add(visualMemoryButton, 1, 1);
        visualMemoryButton.setStyle("-fx-background-color: white;");
        Image image5 = new Image("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\sequenceMemory.png");
        ImageView imageView5 = new ImageView(image5);
        imageView5.setFitHeight(75);
        imageView5.setFitWidth(100);
        Label label5 = new Label("Visual Memory");
        label5.setStyle("-fx-text-fill: black;");
        VBox vBox5 = new VBox();
        vBox5.getChildren().addAll(imageView5, label5);
        vBox5.setAlignment(Pos.CENTER);
        visualMemoryButton.setGraphic(vBox5);
        visualMemoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new VisualMemory(gameUtil , primaryStage);            }
        });


        Button typingTestButton = new Button();
        typingTestButton.setPrefSize(150, 100);
        root.add(typingTestButton, 2, 1);
        typingTestButton.setStyle("-fx-background-color: white;");
        Image image6 = new Image("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\typingTest.png");
        ImageView imageView6 = new ImageView(image6);
        imageView6.setFitHeight(75);
        imageView6.setFitWidth(100);
        Label label6 = new Label("Typing");
        label6.setStyle("-fx-text-fill: black;");
        VBox vBox6 = new VBox();
        vBox6.getChildren().addAll(imageView6, label6);
        vBox6.setAlignment(Pos.CENTER);
        typingTestButton.setGraphic(vBox6);
        typingTestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new TypingTest(gameUtil , primaryStage);            }
        });


        Button numberMemoryButton = new Button();
        numberMemoryButton.setPrefSize(150, 100);
        root.add(numberMemoryButton, 0, 2);
        numberMemoryButton.setStyle("-fx-background-color: white;");
        Image image7 = new Image("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\numberMemory.png");
        ImageView imageView7 = new ImageView(image7);
        imageView7.setFitHeight(75);
        imageView7.setFitWidth(100);
        Label label7 = new Label("Number Memory");
        label7.setStyle("-fx-text-fill: black;");
        VBox vBox7 = new VBox();
        vBox7.getChildren().addAll(imageView7, label7);
        vBox7.setAlignment(Pos.CENTER);
        numberMemoryButton.setGraphic(vBox7);
        numberMemoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new NumberMemory(gameUtil , primaryStage);            }
        });

        Button verbalMemoryButton = new Button();
        verbalMemoryButton.setPrefSize(150, 100);
        root.add(verbalMemoryButton, 1, 2);
        verbalMemoryButton.setStyle("-fx-background-color: white;");
        Image image8 = new Image("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\verbalMemory.png");
        ImageView imageView8 = new ImageView(image8);
        imageView8.setFitHeight(75);
        imageView8.setFitWidth(100);
        Label label8 = new Label("Verbal Memory");
        label8.setStyle("-fx-text-fill: black;");
        VBox vBox8 = new VBox();
        vBox8.getChildren().addAll(imageView8, label8);
        vBox8.setAlignment(Pos.CENTER);
        verbalMemoryButton.setGraphic(vBox8);
        verbalMemoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new VerbalMemory(gameUtil , primaryStage);            }
        });

        Button myGameButton = new Button();
        myGameButton.setPrefSize(150, 100);
        root.add(myGameButton, 2, 2);
        myGameButton.setStyle("-fx-background-color: white;");
        Image image9 = new Image("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\numberMemory.png");
        ImageView imageView9 = new ImageView(image9);
        imageView9.setFitHeight(75);
        imageView9.setFitWidth(100);
        Label label9 = new Label("My Game");
        label9.setStyle("-fx-text-fill: black;");
        VBox vBox9 = new VBox();
        vBox9.getChildren().addAll(imageView9, label9);
        vBox9.setAlignment(Pos.CENTER);
        myGameButton.setGraphic(vBox9);
        myGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new MyGame(gameUtil , primaryStage);            }
        });


        primaryStage.setTitle("Human Benchmark");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}