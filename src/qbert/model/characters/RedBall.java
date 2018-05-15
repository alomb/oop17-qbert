package qbert.model.characters;

import qbert.model.states.CharacterState;
import qbert.model.states.MoveState;
import qbert.model.states.DownwardCharStandingState;
import qbert.model.utilities.Position2D;
import qbert.view.characters.CharacterGC;

/**
 * This class models an enemy who jumps on the map moving randomly downwards.
 */
public class RedBall extends CharacterImpl {

    private final int standingTime;

    /**
     * @param startPos the first {@link Position2D} of the {@link Character} in the map
     * @param speed the {@link Character} movement speed
     * @param graphics the {@link Character}'s {@link CharacterGC}
     * @param standingTime the time passed on standing state
     */
    public RedBall(final Position2D startPos, final Float speed, final CharacterGC graphics, final Integer standingTime) {
        super(startPos, speed, graphics);
        this.setCurrentState(new MoveState.Spawn(this));
        this.standingTime = standingTime;
    }

    @Override
    public final CharacterState getStandingState() {
        return new DownwardCharStandingState(this, this.standingTime);
    }
}
