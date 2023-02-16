/** Jack Vanlyssel
 *
 */

package MainPackage;

public class UserData {
    private String name;
    private long reactionTime, sequenceMemory, aimTrainer, chimpTest,
            visualMemory, typingTest, numberMemory, verbalMemory, myGame;

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

    public void setChimpTest(long chimpTest) {
        this.chimpTest = chimpTest;
    }
    public void setVisualMemory(long visualMemory) {
        this.visualMemory = visualMemory;
    }

    public void setTypingTest(long typingTest) {
        this.typingTest = typingTest;
    }


    public void setNumberMemory(long numberMemory) {
        this.numberMemory = numberMemory;
    }

    public void setVerbalMemory(long verbalMemory) {
        this.verbalMemory = verbalMemory;
    }

    public void setMyGame(long verbalMemory) {
        this.myGame = verbalMemory;
    }

    @Override
    public String toString() {
        return name + "," + reactionTime + " ms ," + sequenceMemory
                + " levels ," + aimTrainer + " ms ," + chimpTest + " levels ," + visualMemory
                + " levels ," + typingTest + " ms ," + numberMemory + " levels ," + verbalMemory
                + " ms ," + myGame;
    }

}
