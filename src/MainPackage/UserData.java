/** Jack Vanlyssel
 *
 */

package MainPackage;

public class UserData {
    private String name;
    private long reactionTime, sequenceMemory, aimTrainer;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setReactionTime(long reactionTime) {
        this.reactionTime = reactionTime;
    }

    public void setSequenceMemory(long SequenceMemory) {
        this.sequenceMemory = SequenceMemory;
    }

    public void setAimTrainer(long aimTrainer) {
        this.aimTrainer = aimTrainer;
    }

    public void setChimpTest(long aimTrainer) {
        this.aimTrainer = aimTrainer;
    }

    @Override
    public String toString() {
        return name + "," + reactionTime + "," + sequenceMemory + "," + aimTrainer;
    }

}
