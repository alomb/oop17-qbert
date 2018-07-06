package qbert.model.states;

import qbert.model.characters.Character;

/**
 * The {@link CharacterState} to manage the fall off the map.
 */
public class FallState extends WaitAnimationState {

    /**
     * @param character the relative {@link Character}
     */
    public FallState(final Character character) {
        super(character);
        this.getCharacter().getGraphicComponent().setFallAnimation();
    }

    @Override
    public final void conclude() {
        this.getCharacter().setCurrentState(new DeathState(this.getCharacter()));
    }
}
