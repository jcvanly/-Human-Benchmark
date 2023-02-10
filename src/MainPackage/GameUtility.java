/** Jack Vanlyssel
 *
 */

package MainPackage;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//will be used to help track scores and user data along with user data
public class GameUtility {
    private String userName;
    private final List<UserData> userDataList;
    private UserData currentUserData;

    public GameUtility(String userName) throws FileNotFoundException {
        this.userName = userName;
        userDataList = new ArrayList<>();
        loadCsv();

//        for (UserData u : userDataList ) {
//                currentUserData = u;
//                break;
//
//        }

        if(currentUserData == null) {
            currentUserData = new UserData();
            currentUserData.setName(userName);
        }
    }

    private void loadCsv() throws FileNotFoundException {
        File file = new File("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\scores.csv");
        Scanner sc = new Scanner(file);
        sc.nextLine();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (null != line && !line.trim().isEmpty()) {
                line = line.trim();
                String[] parts = line.split(",");
                if (null != parts && parts.length == 2) {
                    try {
                        UserData currentUserData = new UserData();
                        currentUserData.setName(parts[0]);
                        currentUserData.setReactionTime(Long.valueOf(parts[1]));
                    }
                    catch (NumberFormatException nfe) {
                        System.out.println(nfe.getMessage());
                    }
                }
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public void updateReactionTime(long score) throws IOException {
        this.currentUserData.setReactionTime(score);
        saveCSV();
    }

    private void saveCSV() throws IOException {
        File file = new File("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\scores.csv");
        BufferedWriter bw = null;

        try {
            bw=new BufferedWriter(new FileWriter(file));
            bw.write("name, reaction_time\n");

            bw.write(currentUserData.toString());

//            for (UserData u : userDataList) {
//                bw.write(u.toString());
//                bw.newLine();
//            }

        }

        catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if(null!=bw){
                try {
                    bw.close();
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
