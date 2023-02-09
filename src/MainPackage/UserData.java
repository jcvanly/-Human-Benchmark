/** Jack Vanlyssel
 *
 */

package MainPackage;

public class UserData {
    private String name;
    private long reactionTime, sequenceMemory, aimTrainer, chimpTest,
            visualMemory, typing, numberMemory, verbalMemory;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setReactionTime(long reactionTime) {

        this.reactionTime = reactionTime;
    }

    @Override
    public String toString() {
        return name + "," + reactionTime;
    }
}
