package qbert.model.characters;

import qbert.model.Tile;
import qbert.model.states.CharacterState;
import qbert.model.states.QbertStandingState;
import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;

/**
 * This class models the main character of the game controlled by the user.
 */
public class Qbert extends CharacterImpl {

    /**
     * @param startPos the first {@link Position2D} of the {@link Character} in the map
     * @param speed the {@link Character} movement speed
     * @param graphics the {@link Character}'s {@link CharacterGraphicComponent}
     */
    public Qbert(final Position2D startPos, final float speed, final CharacterGraphicComponent graphics) {
        super(startPos, speed, graphics);
        this.setCurrentState(this.getStandingState());
    }

    @Override
    public final CharacterState getStandingState() {
        return new QbertStandingState(this);
    }

    @Override
    public final void land(final Tile t) {
        t.incrementColor();
    }
}
