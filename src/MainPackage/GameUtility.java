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

        if(currentUserData == null) {
            currentUserData = new UserData();
            currentUserData.setName(userName);
        }
    }

    private void loadCsv() throws FileNotFoundException {
        File file = new File("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\scores.csv");
        Scanner sc = new Scanner(file);
        //sc.nextLine();

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

    public void updateSequenceMemory(long score) throws IOException {
        this.currentUserData.setSequenceMemory(score);
        saveCSV();
    }

    public void updateAimTrainer(long score) throws IOException {
        this.currentUserData.setAimTrainer(score);
        saveCSV();
    }

    public void updateChimpTest(long score) throws IOException {
        this.currentUserData.setChimpTest(score);
        saveCSV();
    }

    public void updateVisualMemory(long score) throws IOException {
        this.currentUserData.setVisualMemory(score);
        saveCSV();
    }

    public void updateTypingTest(long score) throws IOException {
        this.currentUserData.setTypingTest(score);
        saveCSV();
    }

    private void saveCSV() throws IOException {
        File file = new File("C:\\Users\\jackv\\IdeaProjects\\Human-Benchmark\\resources\\scores.csv");
        BufferedWriter bw = null;

        try {
            bw=new BufferedWriter(new FileWriter(file));
            bw.write("name, reaction_time, sequence_memory, aim_trainer, " +
                    "chimp_test, visual_memory,typing, number_memory, " +
                    "verbal_memory\n");
            bw.write(currentUserData.toString());

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

