/**
 *
 */

package MainPackage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.VPos;



public class UserNameUI {

    private final Stage parentStage;
    private String userName;

    public UserNameUI(Stage primaryStage) {
        this.parentStage = primaryStage;
        getUserName();

    }

    private void getUserName() {
        Stage userNameDialogue = new Stage();
        userNameDialogue.setWidth(250);
        userNameDialogue.setHeight(150);
        userNameDialogue.setTitle("Login Page");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        Label label = new Label("Name: ");
        TextField userNameField=new TextField();
        Button submitButton = new Button("Play!");

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(submitButton, label, userNameField);

        gridPane.add(label,0,1);
        gridPane.add(userNameField,1,1);
        gridPane.add(submitButton,1,2);

        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setValignment(submitButton, VPos.CENTER);
        GridPane.setHalignment(userNameField, HPos.CENTER);
        GridPane.setValignment(userNameField, VPos.CENTER);

        userNameDialogue.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkUserName(userNameField,userNameDialogue);
            }
        });

        userNameDialogue.setScene(new Scene(gridPane, 400, 100));
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

