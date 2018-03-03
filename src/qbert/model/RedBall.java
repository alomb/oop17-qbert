package qbert.model;

import qbert.model.states.CharacterState;
import qbert.model.states.MoveState;
import qbert.model.states.SimpleStandingState;
import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;

/**
 * This class models an enemy who jumps on the map moving randomly downwards.
 */
public class RedBall extends CharacterImpl {

    private final int standingTime;

    /**
     * @param startPos the first {@link Position2D} of the {@link Character} in the map
     * @param speed the {@link Character} movement speed
     * @param graphics the {@link Character}'s {@link CharacterGraphicComponent}
     * @param standingTime the time passed on standing state
     */
    public RedBall(final Position2D startPos, final float speed, final CharacterGraphicComponent graphics, final int standingTime) {
        super(startPos, speed, graphics);
        this.setCurrentState(new MoveState.Fall(this));
        this.standingTime = standingTime;
        //this.setStandingState(new SimpleStandingState(this, standingTime));
    }

    @Override
    public CharacterState getStandingState() {
        return new SimpleStandingState(this, this.standingTime);
    }
}
