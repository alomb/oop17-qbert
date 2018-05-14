package qbert.model.states;

import qbert.model.characters.Player;
import qbert.model.utilities.Position2D;

/**
 * The {@link CharacterState} used to manage {@link Qbert} when it's standing, it differs from
 * others because it is managed by user's input not by AI or timers, so can never ends.
 */
public final class QbertStandingState implements CharacterState {

    private final Player character;
    /**
     * @param character the {@link Player} linked to this state.
     * Sets the spawn position to be the current one and starts the standing animation.
     */
    public QbertStandingState(final Player character) {
        this.character = character;
        this.character.setCurrentPosition(new Position2D(this.character.getNextPosition()));
        this.character.getGraphicComponent().setSpawnPosition(
                new Position2D(this.character.getGraphicComponent().getPosition()));
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
