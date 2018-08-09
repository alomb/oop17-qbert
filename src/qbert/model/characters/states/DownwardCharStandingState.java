package qbert.model.characters.states;

import qbert.model.characters.Character;
import qbert.model.utilities.Position2D;

/**
 * The {@link CharacterState} used for {@link Character}s which move down on the Y axis and randomly on the X axis.
 */
public class DownwardCharStandingState extends WaitTimerState {

    /**
     * @param character the {@link Character} linked to this state
     * @param triggerTime the timer duration
     */
    public DownwardCharStandingState(final Character character, final int triggerTime) {
        super(character, triggerTime);
        this.getCharacter().setCurrentPosition(new Position2D(this.getCharacter().getNextPosition()));
        this.getCharacter().getGraphicComponent().setStandingAnimation();
    }

    @Override
    public final void conclude() {
        CharacterState nextState;
        final Position2D newPos = new Position2D(getCharacter().getCurrentPosition());
        if (this.canAdvance()) {
            newPos.setY(newPos.getY() - this.getCharacter().getStep());
            if (Math.random() > 0.5) {
                newPos.setX(newPos.getX() + this.getCharacter().getStep());
                nextState = new MoveState.DownRight(this.getCharacter());
            } else {
                newPos.setX(newPos.getX() - this.getCharacter().getStep());
                nextState = new MoveState.DownLeft(this.getCharacter());
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
