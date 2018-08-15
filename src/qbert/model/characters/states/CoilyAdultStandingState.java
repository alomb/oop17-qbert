package qbert.model.characters.states;

import qbert.model.characters.Snake;
import qbert.model.characters.Player;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * The {@link CharacterState} used by {@link Snake} in its adult snake form when it 
 * starts chasing the {@link Player}.
 */
public class CoilyAdultStandingState extends WaitTimerState {

    private final Player qbert;
    private final Snake coily;

    /**
     * @param coily the {@link Snake} linked to this state
     * @param triggerTime the timer duration
     * @param qbert the {@link Player} reference
     */
    public CoilyAdultStandingState(final Snake coily, final int triggerTime, final Player qbert) {
        super(coily, triggerTime);
        this.qbert = qbert;
        this.coily = coily;
        this.getCharacter().setCurrentPosition(new Position2D(this.getCharacter().getNextPosition()));
        this.coily.getDownUpwardGraphicComponent().setStandingAnimation();
    }

    @Override
    protected final void conclude() {
        final Position2D targetPosition = new Position2D(this.qbert.getCurrentPosition());
        final Position2D myPosition = new Position2D(this.getCharacter().getCurrentPosition());

        if (targetPosition.equals(myPosition)) {
            return;
        }

        final int dx, dy;

        if (targetPosition.getX() > myPosition.getX()) {
            dx = this.getCharacter().getStep();
        } else if (targetPosition.getX() < myPosition.getX()) {
            dx = -this.getCharacter().getStep();
        } else {
            dx = ((Math.random() > 0.5) ? this.getCharacter().getStep() : -this.getCharacter().getStep());
        }

        if (targetPosition.getY() > myPosition.getY() || myPosition.getY() == Dimensions.MAP_BOTTOM_EDGE) {
            dy = this.getCharacter().getStep();
        } else if (targetPosition.getY() == myPosition.getY()) {
            dy = ((Math.random() > 0.5) ? this.getCharacter().getStep() : -this.getCharacter().getStep());
        } else {
            dy = -this.getCharacter().getStep();
        }

        if (dx > 0 && dy > 0) {
            this.getCharacter().setCurrentState(new MoveState.UpRight(this.coily));
        } else if (dx < 0 && dy > 0) {
            this.getCharacter().setCurrentState(new MoveState.UpLeft(this.coily));
        } else if (dx > 0 && dy < 0) {
            this.getCharacter().setCurrentState(new MoveState.DownRight(this.coily));
        } else {
            this.getCharacter().setCurrentState(new MoveState.DownLeft(this.coily));
        }

        this.getCharacter().getNextPosition().vectorSum(new Position2D(dx, dy));
    }
}
