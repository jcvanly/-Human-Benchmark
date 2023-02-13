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

    @Override
    public String toString() {
        return "Name: " + name + "\nReaction Time: " + reactionTime + "\nSequence Memory: " + sequenceMemory
                + "\nAim Trainer: " + aimTrainer + "\nChimp Test: " + chimpTest + "\nVisual Memory: " + visualMemory
                + "\nTyping Test: " + typingTest + "\nNumber Memory: " + numberMemory + "\nVerbal Memory: " + verbalMemory
                + "\nMy Game: " + myGame;
    }

}
