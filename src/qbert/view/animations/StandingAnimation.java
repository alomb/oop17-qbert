package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 * Animation that doesn't move the sprite and is finished.
 */
public class StandingAnimation extends GenericAnimation {

    /**
     * @param startPos the first {@link Position2D}
     */
    public StandingAnimation(final Position2D startPos) {
        super(startPos, startPos);
    }

    @Override
    public void calculateNext() {

    }
}
