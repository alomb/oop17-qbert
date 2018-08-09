package qbert.model.characters;

import qbert.model.characters.states.CharacterState;
import qbert.model.characters.states.LeftwardCharStandingState;
import qbert.model.characters.states.SpawnState;
import qbert.model.utilities.Position2D;
import qbert.view.characters.CharacterGC;

/**
 * An extension of {@link CharacterImpl} to manage those characters that spawn
 * falling to the map, move leftward and fall out of the map. Collision and landing
 * behavior must be defined.
 */
public abstract class LeftwardCharacter extends CharacterImpl {

    private final int standingTime;

    /**
     * @param startPos the first {@link Position2D} of the {@link Character} in the map
     * @param speed the {@link Character} movement speed
     * @param graphics the {@link Character}'s {@link CharacterGC}
     * @param standingTime the time passed on standing state
     */
    public LeftwardCharacter(final Position2D startPos, final Float speed, final CharacterGC graphics, final Integer standingTime) {
        super(startPos, speed, graphics);
        this.setCurrentState(new SpawnState(this));
        this.standingTime = standingTime;
    }

    @Override
    public final CharacterState getStandingState() {
        return new LeftwardCharStandingState(this, this.standingTime);
    }
}
