package qbert.model.characters.states;

import qbert.model.characters.Character;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * The {@link CharacterState} used for {@link Character}s which move right on the X axis and semi-randomly on the Y axis.
 */
public class RightwardCharStandingState extends WaitTimerState {

    /**
     * @param character the {@link Character} linked to this state
     * @param triggerTime the timer duration
     */
    public RightwardCharStandingState(final Character character, final int triggerTime) {
        super(character, triggerTime);
        this.getCharacter().setCurrentPosition(new Position2D(this.getCharacter().getNextPosition()));
        this.getCharacter().getGraphicComponent().setStandingAnimation();
    }

    @Override
    public final void conclude() {
        CharacterState nextState;
        final Position2D newPos = new Position2D(getCharacter().getCurrentPosition());
        if (this.canAdvance()) {
            newPos.setX(newPos.getX() + 1);

            if (newPos.getY() == Dimensions.MAP_BOTTOM_EDGE || Math.random() > 0.5) {
                //newPos.setY(newPos.getY() + 1);
                //nextState = new MoveState.DownLeft(this.getCharacter());
                newPos.setY(newPos.getY() - 1);
                nextState = new MoveState.DownRight(this.getCharacter());
            } else {
                newPos.setY(newPos.getY() - 1);
                nextState = new MoveState.DownRight(this.getCharacter());
            }
            this.getCharacter().setNextPosition(newPos);
            this.getCharacter().setCurrentState(nextState);
        }
    }

    /**
     * @return true if the {@link Character} wants to keep going down or return false otherwise, probably changing the state
     */
    public boolean canAdvance() {
        return true;
    }
}
