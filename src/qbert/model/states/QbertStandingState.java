package qbert.model.states;

import qbert.model.characters.Character;
import qbert.model.utilities.Position2D;

/**
 * The {@link CharacterState} used to manage {@link Qbert} when it's standing, it differs from
 * others because it is managed by user's input not by AI or timers, so can never ends.
 */
public final class QbertStandingState implements CharacterState {

    private final Character character;
    /**
     * @param character the {@link Character} linked to this state.
     * Sets the spawn position to be the current one and starts the standing animation.
     */
    public QbertStandingState(final Character character) {
        this.character = character;
        this.character.setCurrentPosition(new Position2D(this.character.getNextPosition()));
        this.character.getGraphicComponent().setSpawnPosToCurrentPos();
        this.character.getGraphicComponent().setStandingAnimation();
    }

    @Override
    public void update(final float dt) {
        this.character.getGraphicComponent().updateGraphics(dt);
    }

    @Override
    public void conclude() {

    }
}
