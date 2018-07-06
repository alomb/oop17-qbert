package qbert.model.states;

import qbert.model.characters.Player;
import qbert.model.utilities.Position2D;

/**
 * The {@link CharacterState} used to manage {@link Player} when it's standing, it differs from
 * others because it is managed by user's input not by AI or timers, so can never ends.
 */
public class QbertStandingState implements CharacterState {

    private final Player qbert;
    /**
     * @param qbert the {@link Player} linked to this state.
     * Sets the spawn position to be the current one and starts the standing animation.
     */
    public QbertStandingState(final Player qbert) {
        this.qbert = qbert;
        this.qbert.setCurrentPosition(new Position2D(this.qbert.getNextPosition()));
        this.qbert.getGraphicComponent().setSpawnPosition(
                new Position2D(this.qbert.getGraphicComponent().getPosition()));
        this.qbert.getGraphicComponent().setStandingAnimation();
    }

    @Override
    public final void update(final float dt) {
        this.qbert.getGraphicComponent().updateGraphics(dt);
    }

    @Override
    public void conclude() {

    }
}
