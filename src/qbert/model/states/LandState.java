package qbert.model.states;

import qbert.model.characters.Character;
import qbert.model.utilities.Position2D;

/**
 * A dumb {@link CharacterState} used to advise that the relative {@link Character} is landed on a block
 * after a jump.
 */
public class LandState extends CharacterStateImpl {

    /**
     * @param character {@link Character} associated with this {@link CharacterState} 
     */
    public LandState(final Character character) {
        super(character);
        /*TODO: update the nextPos only when the character lands on the terrain*/
        this.getCharacter().setCurrentPosition(new Position2D(this.getCharacter().getNextPosition()));
        this.getCharacter().getGraphicComponent().setStandingAnimation();
    }

    @Override
    public void update(final float dt) {

    }

    @Override
    public void conclude() {

    }
}
