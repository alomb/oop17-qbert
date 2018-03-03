package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 * Animation for moving the sprite upward.
 */
public class MoveUpAnimation extends GenericAnimation {

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     */
    public MoveUpAnimation(final Position2D startPos, final Position2D targetPos) {
        super(startPos);
        this.setTargetPosition(targetPos);
    }

    @Override
    public void calculateNext() {
        if (!this.hasFinished()) {
            this.getCurrentPosition().setY(this.getCurrentPosition().getY() - this.getAnimationSpeed());

            if (this.getTargetPosition().getY() > this.getCurrentPosition().getY()) {
                this.getCurrentPosition().setY(this.getTargetPosition().getY());
            }
        }
    }

}
