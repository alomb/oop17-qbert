package qbert.model.states;

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
        this.getCharacter().getGraphicComponent().setStandingAnimation();
    }

    @Override
    public final void conclude() {
        CharacterState nextState;
        final Position2D newPos = getCharacter().getCurrentPosition();
        if (this.canAdvance()) {
            newPos.setY(newPos.getY() - 1);
            if (Math.random() > 0.5) {
                newPos.setX(newPos.getX() + 1);
                nextState = new MoveState.DownRight(this.getCharacter());
            } else {
                newPos.setX(newPos.getX() - 1);
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
