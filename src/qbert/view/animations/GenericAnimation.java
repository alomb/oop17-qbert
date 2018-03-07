package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 * A generic implementation of {@link Animation} interface.
 */
public abstract class GenericAnimation implements Animation {

    private Position2D currentPos;
    private final Position2D targetPos;
    private float animationSpeed;

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     */
    public GenericAnimation(final Position2D startPos, final Position2D targetPos) {
        this.currentPos = startPos;
        this.targetPos = targetPos;
    }

    /**
     * @return the animation speed
     */
    public float getAnimationSpeed() {
        return this.animationSpeed;
    }

    /**
     * @return the current {@link Position2D} of the animation
     */
    protected final Position2D getCurrentPosition() {
        return this.currentPos;
    }

    /**
     * @param currentPosition set the current {@link Position2D} of the animation
     */
    protected final void setCurrentPosition(final Position2D currentPosition) {
        this.currentPos = currentPosition;
    }

    /**
     * @return the the last {@link Position2D} of the animation
     */
    protected Position2D getTargetPosition() {
        return this.targetPos;
    }

    @Override
    public final boolean hasFinished() {
        return this.targetPos.equals(currentPos);
    }

    @Override
    public final Position2D updateAnimation(final float animationSpeed) {
        this.animationSpeed = animationSpeed;
        if (!this.hasFinished()) {
            this.calculateNext();
        }
        return this.currentPos;
    }

    /**
     * This method must be implemented by concrete animation classes to personalize the animation.
     * It is called in updateAnimation(), when hasFinished() return false.
     */
    protected abstract void calculateNext();
}
