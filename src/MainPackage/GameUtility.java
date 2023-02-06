/** Jack Vanlyssel
 *
 */

package MainPackage;

import java.util.ArrayList;
import java.util.List;
//will be used to help track scores and user data along with user data
public class GameUtility {
    private String userName;
    private final List<UserData> userDataList;
    private UserData currentUser;

    public GameUtility(String userName) {
        this.userName = userName;
        userDataList = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }
}