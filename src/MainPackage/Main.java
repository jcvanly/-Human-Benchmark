/** Jack Vanlyssel
 *
 */

package MainPackage;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private String userName;
    @Override
    public void start(Stage primaryStage) {
        new UserNameUI(primaryStage);
        GameUtility controller = new GameUtility(userName);

        new HomeScreenUI(primaryStage, controller);
    }

}

