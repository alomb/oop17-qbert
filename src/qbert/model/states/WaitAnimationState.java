package qbert.model.states;

import qbert.model.Character;

/**
 * An implementation of the {@link CharacterState} interface used to manage an animation.
 */
public abstract class WaitAnimationState implements CharacterState {

    private final Character character;

    /**
     * @param character {@link Character} associated with this {@link CharacterState} 
     */
    public WaitAnimationState(final Character character) {
        this.character = character;
    }

    /**
     * @return the {@link Character} associated with this {@link CharacterState}
     */
    public Character getCharacter() {
        return this.character;
    }

    @Override
    public void update(final float dt) {
        if (!this.character.getGraphicComponent().getCurrentAnimation().hasFinished()) {
            this.character.getGraphicComponent().updateGraphics(dt * this.character.getSpeed());
        } else {
            this.conclude();
        }
    }

    @Override
    public abstract void conclude();
}
