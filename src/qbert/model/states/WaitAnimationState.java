package qbert.model.states;

import qbert.model.characters.Character;

/**
 * An implementation of the {@link CharacterState} interface used to manage an animation.
 */
public abstract class WaitAnimationState extends CharacterStateImpl {

    /**
     * @param character {@link Character} associated with this {@link CharacterState} 
     */
    public WaitAnimationState(final Character character) {
        super(character);
    }

    @Override
    public final void update(final float dt) {
        if (!this.getCharacter().getGraphicComponent().getCurrentAnimation().hasFinished()) {
            this.getCharacter().getGraphicComponent().updateGraphics(dt * this.getCharacter().getSpeed());
        } else {
            this.conclude();
        }
    }

    @Override
    public abstract void conclude();
}
