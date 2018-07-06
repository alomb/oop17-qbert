package qbert.model.states;

import qbert.model.characters.Character;

/**
 * The {@link CharacterState} to manage the spawning.
 */
public class SpawnState extends WaitAnimationState {

    /**
     * @param character the relative {@link Character}
     */
    public SpawnState(final Character character) {
        super(character);
        this.getCharacter().setDead(false);
        this.getCharacter().getGraphicComponent().setSpawnAnimation();
    }

    @Override
    public final void conclude() {
        this.getCharacter().setCurrentState(new LandState(this.getCharacter()));
    }
}
