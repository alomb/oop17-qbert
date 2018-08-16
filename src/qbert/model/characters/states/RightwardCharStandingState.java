package qbert.model.characters.states;

import qbert.model.characters.RightwardCharacter;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * The {@link CharacterState} used for {@link Character}s which move right on the X axis and semi-randomly on the Y axis.
 */
public class RightwardCharStandingState extends WaitTimerState {

    /**
     * @param character the {@link RightwardCharacter} linked to this state
     * @param triggerTime the timer duration
     */
    public RightwardCharStandingState(final RightwardCharacter character, final int triggerTime) {
        super(character, triggerTime);
        this.getCharacter().setCurrentPosition(new Position2D(this.getCharacter().getNextPosition()));
        this.getCharacter().getGraphicComponent().setStandingAnimation();
    }

    @Override
    protected final void conclude() {
        CharacterState nextState;
        final Position2D newPos = new Position2D(getCharacter().getCurrentPosition());
        newPos.setX(newPos.getX() + this.getCharacter().getStep());
        if (newPos.getY() <= Dimensions.MAP_BOTTOM_EDGE || Math.random() > 0.5) {
            newPos.setY(newPos.getY() + this.getCharacter().getStep());
            nextState = new MoveState.DownLeft(this.getCharacter());
        } else {
            newPos.setY(newPos.getY() - this.getCharacter().getStep());
            nextState = new MoveState.DownRight(this.getCharacter());
        }
        this.getCharacter().setNextPosition(newPos);
        this.getCharacter().setCurrentState(nextState);
    }
}
