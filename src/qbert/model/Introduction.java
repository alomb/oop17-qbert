package qbert.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import qbert.model.characters.Player;
import qbert.model.characters.Qbert;
import qbert.model.states.LandState;
import qbert.model.states.MoveState;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.Renderable;
import qbert.view.characters.PlayerGC;
import qbert.view.characters.PlayerGCImpl;

/**
 * The implementation of {@link Model} for application introductive scene logic.
 */
public class Introduction implements Model {
    private final List<String> instructions;

    private int instructionsIndex;
    private int step;
    private static final int MAXSTEP = 4;

    private final Player qbert;
    private static final float SPEED = 0.35f;
    private static final Position2D QBERTPOSITION = 
            new Position2D(Math.round(Dimensions.getWindowWidth() / 3f), Math.round(Dimensions.getWindowHeight() / 2.75f));

    /**
     * 
     */
    public Introduction() {

        this.instructions = new ArrayList<>();
        this.instructions.add("JUMP ON SQUARES TO");
        this.instructions.add("CHANGE THEM TO");
        this.instructions.add("THE TARGET COLOR");

        this.instructions.add("STAY ON PLAYFIELD!");
        this.instructions.add("JUMPING OFF RESULTS");
        this.instructions.add("IN A FATAL PLUMMET");

        this.instructions.add("AVOID ALL OBJECTS");
        this.instructions.add("AND CREATURES THAT");
        this.instructions.add("ARE NOT GREEN");

        this.instructions.add("JUMP ON SPINNING DISKS");
        this.instructions.add("TO LURE SNAKE");
        this.instructions.add("TO HIS DEATH");

        this.instructionsIndex = 3;
        this.step = 1;

        final PlayerGC graphics = new PlayerGCImpl(Sprites.qbertFrontStanding, Sprites.qbertFrontMoving, Sprites.qbertBackStanding, Sprites.qbertBackMoving, 
                Sprites.qbertDead, Sprites.qbertOnDisk, new Position2D(QBERTPOSITION));

        this.qbert = new Qbert(Dimensions.getSpawningLogQBert(), SPEED, graphics);
    }

    @Override
    public final void initialize() {
        this.qbert.setCurrentState(this.qbert.getStandingState());
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
    public final void confirm() {
        if (!this.qbert.isMoving()) {
            if (this.step < Introduction.MAXSTEP) {
                this.instructionsIndex += 3;
            }
            this.step++;

            this.qbert.setCurrentState(new MoveState.DownRight(this.qbert));
        }
    }

    @Override
    public final void update(final float elapsed) {
        this.qbert.update(elapsed);

        if (this.qbert.getCurrentState() instanceof LandState) {
            this.qbert.setCurrentState(this.qbert.getStandingState());
        }
    }

    /**
     * @return the list of object renderables.
     */
    public List<Renderable> getRenderables() {
        return Stream.of(this.qbert).collect(Collectors.toList());
    }

    /**
     * @return the current step.
     */
    public int getStep() {
        return this.step;
    }

    /**
     * @return the current index of instructions seen.
     */
    public int getInstructionsIndex() {
        return this.instructionsIndex;
    }

    /**
     * @return the list of instructions.
     */
    public List<String> getInstructions() {
        return this.instructions;
    }

    /**
     * @return true there's no more steps
     */
    public boolean hasReachedTheEnd() {
        return this.step > Introduction.MAXSTEP;
    }
}
