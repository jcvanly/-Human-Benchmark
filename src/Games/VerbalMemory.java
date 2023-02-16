package Games;


import MainPackage.GameUtility;
import MainPackage.HomeScreenUI;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class VerbalMemory {
    private final GameUtility gameUtility;
    private final Stage primaryStage;
    private final List<String> words = new ArrayList<>();

    public VerbalMemory(GameUtility gameUtility, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameUtility = gameUtility;
        loadDictionary();
        showVerbalMemory();
    }

    private void loadDictionary() {
        try {
            Scanner sc = new Scanner(new File("resources/dictionary.txt"));
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (null != line && !line.trim().isEmpty()) {
                    words.add(line.trim());
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showVerbalMemory() {
        primaryStage.setTitle("Verbal Memory");
        BorderPane root = new BorderPane();
        SimpleLongProperty level = new SimpleLongProperty();
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ffb347;");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new HomeScreenUI(primaryStage, gameUtility);
            }
        });
        SimpleIntegerProperty lives = new SimpleIntegerProperty(3);
        SimpleIntegerProperty score = new SimpleIntegerProperty(0);
        SimpleIntegerProperty counter = new SimpleIntegerProperty(0);
        Label livesLabel = new Label("Lives " + lives.get());
        livesLabel.setStyle("-fx-text-fill: white;");

        Label scoreLabel = new Label("Score " + score.get());
        scoreLabel.setStyle("-fx-text-fill: white;");

        GridPane topBox = new GridPane();
        topBox.setHgap(20);
        topBox.add(backButton, 0, 0);
        topBox.add(livesLabel, 1, 0);
        topBox.add(scoreLabel, 2, 0);

        VBox centerBox = new VBox(40);
        List<String> previousWords = new ArrayList<>();

        SimpleStringProperty word = new SimpleStringProperty();
        Random r = new Random();
        int len = r.nextInt(words.size());
        word.set(words.get(len));

        Button seenBtn = new Button("Seen");
        Button newBtn = new Button("New");
        newBtn.setStyle("-fx-background-color: #ffb347;");
        seenBtn.setStyle("-fx-background-color: #ffb347;");
        Label wordLabel = new Label(word.get());
        wordLabel.setFont(Font.font(36));
        wordLabel.setStyle("-fx-text-fill: white;");
        root.setStyle("-fx-background-color: #008AD8;");
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(seenBtn, newBtn);
        centerBox.getChildren().addAll(wordLabel, buttonBox);
        centerBox.setAlignment(Pos.CENTER);


        HBox bottomBtnBox = new HBox();
        Button saveScoreBtn = new Button("Save Score");
        saveScoreBtn.setStyle("-fx-background-color: #ffb347;");
        saveScoreBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    gameUtility.updateVerbalMemory(score.get());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                new VerbalMemory(VerbalMemory.this.gameUtility,
                        primaryStage);
            }
        });
        Button tryAgainBtn = new Button("Try Again");
        tryAgainBtn.setStyle("-fx-background-color: #ffb347;");
        tryAgainBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new VerbalMemory(VerbalMemory.this.gameUtility,
                        primaryStage);
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

        seenBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (lives.get() == 0) {
                    return;
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (previousWords.contains(word.get())) {
                            score.set(score.get() + 1);
                            int len = r.nextInt(words.size());
                            word.set(words.get(len));
                        }
                        else {
                            lives.set(lives.get() - 1);
                            if (lives.get() == 0) {
                                bottomBtnBox.setVisible(true);
                            }
                            else {
                                int len = r.nextInt(words.size());
                                word.set(words.get(len));
                            }
                        }
                        livesLabel.setText("Lives:"+lives.get());
                        livesLabel.setStyle("-fx-text-fill: white;");
                        scoreLabel.setText("Score:"+score.get());
                        scoreLabel.setStyle("-fx-text-fill: white;");
                        wordLabel.setText(word.get());
                    }
                });
            }
        });

        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (lives.get() == 0) {
                    return;
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (!previousWords.contains(word.get())) {
                            score.set(score.get() + 1);
                            scoreLabel.setText("Score: "+score.get());
                            previousWords.add(word.get());
                            int len = r.nextInt(words.size());

                            if(counter.get()%5 == 0 && counter.get() > 0)
                            {
                                int index = r.nextInt(previousWords.size()-1);
                                word.set(previousWords.get(index));
                                wordLabel.setText(previousWords.get(index));

                            }

                            else
                            {
                                word.set(words.get(len));
                                wordLabel.setText(word.get());
                                System.out.println(word.get());
                            }

                        }

                        else {
                            lives.set(lives.get() - 1);
                            if (lives.get() == 0) {
                                bottomBtnBox.setVisible(true);
                            }
                            else {
                                int len = r.nextInt(words.size());
                                if(counter.get()%5 == 0)
                                {

                                }
                                word.set(words.get(len));
                                wordLabel.setText(word.get());
                            }
                        }

                        livesLabel.setText("Lives:"+lives.get());
                        livesLabel.setStyle("-fx-text-fill: white;");
                        scoreLabel.setText("Score:"+score.get());
                        livesLabel.setStyle("-fx-text-fill: white;");
                        counter.set(counter.get() + 1);
                        System.out.println(counter.get());

//                        if(counter.get()%5 == 0)
//                        {
//                            int index = r.nextInt(previousWords.size()-1);
//                            wordLabel.setText(previousWords.get(index));
//                            System.out.println(word.get());
//
//                        }
//                        else
//                        {
//                            System.out.println(word.get());
//                            wordLabel.setText(word.get());
//
//                        }
                    }
                });

            }
        });

    }
}