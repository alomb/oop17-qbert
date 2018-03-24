package qbert.model.states;

import qbert.model.Character;

/**
 *
 */
public class DeathState extends CharacterStateImpl {

    /**
     * @param character {@link Character} associated with this {@link CharacterState} 
     */
    public DeathState(final Character character) {
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