package qbert.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of {@link Model} for application introductive scene logic.
 */
public class Introduction implements Model {
    private final List<String> instructions;

    private int instructionsIndex;
    private int step;
    private static final int MAXSTEP = 4;

    /**
     * 
     */
    public Introduction() {

        this.instructions = new ArrayList<>();
        this.instructions.add("JUMP ON SQUARES TO");
        this.instructions.add("CHANGE THEM TO");
        this.instructions.add("THE TARGET COLOR");

        this.instructions.add("STAY ON PLAYFIELD!");
        this.instructions.add("JUMPING OFF RESULTS IN");
        this.instructions.add("IN A FATAL PLUMMET");

        this.instructions.add("AVOID ALL OBJECTS");
        this.instructions.add("AND CREATURES THAT");
        this.instructions.add("ARE NOT GREEN");

        this.instructions.add("USE SPINNING DISKS");
        this.instructions.add("TO LURE SNAKE");
        this.instructions.add("TO HIS DEATH");

        this.instructionsIndex = 3;
        this.step = 1;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveUp() {

    }

    @Override
    public void confirm() {

    }

    @Override
    public void update(final float elapsed) {

    }

    /**
     * @return the current step.
     */
    public int getStep() {
        return this.step;
    }

    /**
     * @return the max value of the step
     */
    public int getMaxStep() {
        return Introduction.MAXSTEP;
    }

    /**
     * @param value the value to increment
     */
    public void incrementStep(final int value) {
        this.step += value;
    }

    /**
     * @return the current index of instructions seen.
     */
    public int getInstructionsIndex() {
        return this.instructionsIndex;
    }

    /**
     * @param value the value to increment
     */
    public void incrementInstructionsIndex(final int value) {
        this.instructionsIndex += value;
    }

    /**
     * @return the list of instructions.
     */
    public List<String> getInstructions() {
        return this.instructions;
    }
}
