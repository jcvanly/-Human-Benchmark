/** Jack Vanlyssel
 *
 */

package MainPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//will be used to help track scores and user data along with user data
public class GameUtility {
    private String userName;
    private final List<UserData> userDataList;
    private UserData currentUserData;

    public GameUtility(String userName) {
        this.userName = userName;
        userDataList = new ArrayList<>();
        loadCsv();
        for (UserData u:userDataList ) {
                currentUserData = u;
                break;

        }

        if(currentUserData == null) {
            currentUserData = new UserData();
            currentUserData.setName(userName);
        }
    }

    private void loadCsv() {
        File file = new File("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\scores.csv");
        Scanner sc = null;

    }
    public String getUserName() {
        return userName;
    }

    public void updateReactionTime(long score){
        this.currentUserData.setReactionTime(score);
        saveCSV();
    }

    private void saveCSV() throws IOException {
        File file = new File("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\scores.csv");
        BufferedWriter bw = null;

        bw = new BufferedWriter(new FileWriter(file));
        bw.write("name, reaction_time, sequence_memory, aim_trainer, " +
                "chimp_test, visual_memory,typing, number_memory, " +
                "verbal_memory\n");

    }
}