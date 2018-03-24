package qbert.model.states;

import qbert.model.Character;

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
        character.getGraphicComponent().setStandingAnimation();
    }

    @Override
    public void update(final float dt) {

    }

    @Override
    public void conclude() {

    }

}
