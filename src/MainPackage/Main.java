/** Jack Vanlyssel
 *
 */

package MainPackage;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        new UserNameUI(primaryStage);
    }
}

