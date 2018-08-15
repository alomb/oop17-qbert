package qbert.model.characters.states;

import qbert.model.characters.Character;
import qbert.model.characters.DownUpwardCharacter;

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

    /**
     * @param character the relative {@link Snake}
     */
    public FallState(final DownUpwardCharacter character) {
        super(character);
        this.getCharacter().getGraphicComponent().setFallAnimation();
        character.getCharcaterSoundComponent().setFallSound();
    }

    @Override
    public final void conclude() {
        this.getCharacter().setCurrentState(new DeathState(this.getCharacter()));
    }
}
