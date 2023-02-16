/** Jack Vanlyssel
 * This code defines a user interface for prompting the user to enter
 * their name before starting the game. The user interface consists of
 * a simple dialog box with a label, text field, and a button. When
 * the user clicks the "Play!" button, the code checks if the user
 * has entered a name. If the user has not entered a name, an error
 * message is displayed using an alert. If the user has entered a name,
 * the dialog box is closed and the name is stored for future use in the game.
 */

package MainPackage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

import java.io.FileNotFoundException;


public class UserNameUI {

    private final Stage parentStage;
    private String userName;

    public UserNameUI(Stage primaryStage) throws FileNotFoundException {
        this.parentStage = primaryStage;
        getUserName();
        GameUtility gameUtility = new GameUtility(userName);
        new HomeScreenUI(primaryStage, gameUtility);
    }

    /**
     * This code shows a dialog box prompting the user to enter a user name, and handles the user input.
     * The submitButton is set up with an event handler that calls the checkUserName() method to validate the input.
     */
    private void getUserName() {
        Stage userNameDialogue = new Stage();
        userNameDialogue.setWidth(400);
        userNameDialogue.setHeight(100);
        userNameDialogue.setTitle("Login Page");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, null, null);
        Background background = new Background(backgroundFill);
        gridPane.setBackground(background);

        Label label = new Label("Name:");
        label.setFont(new Font("Segoe UI", 16));

        TextField userNameField = new TextField();
        userNameField.setPrefWidth(200);

        Button submitButton = new Button("Play!");
        submitButton.setStyle("-fx-background-color: white; -fx-border-color: #008AD8; -fx-border-width: 3px; -fx-border-style: solid;");

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.getChildren().addAll(label, userNameField, submitButton);

        gridPane.add(hBox, 0, 0);

        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.CENTER);
        GridPane.setHalignment(userNameField, HPos.CENTER);
        GridPane.setValignment(userNameField, VPos.CENTER);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setValignment(submitButton, VPos.CENTER);

        userNameDialogue.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkUserName(userNameField, userNameDialogue);
            }
        });

        userNameDialogue.setScene(new Scene(gridPane));
        userNameDialogue.showAndWait();
    }

    private void checkUserName(TextField userNameField, Stage dialog) {
        userName = userNameField.getText();

        if(userName != null && !userName.isEmpty()) {
            dialog.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a user name");
            alert.showAndWait();
        }
    }
}

