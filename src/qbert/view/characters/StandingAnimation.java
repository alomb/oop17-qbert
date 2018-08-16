package qbert.view.characters;

import qbert.model.utilities.Position2D;

/**
 * Animation that doesn't move the sprite and is finished.
 */
public class StandingAnimation extends MovementAnimation {

    /**
     * @param startPos the first {@link Position2D}
     */
    public StandingAnimation(final Position2D startPos) {
        super(startPos, startPos);
    }

    @Override
    protected void calculateNext() {

    }
}
