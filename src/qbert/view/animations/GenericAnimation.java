package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 * A generic implementation of {@link Animation} interface.
 */
public abstract class GenericAnimation implements Animation {

    private Position2D currentPos;
    private Position2D targetPos;
    private float animationSpeed;

    /**
     * @param startPos the first {@link Position2D}
     */
    public GenericAnimation(final Position2D startPos) {
        this.currentPos = startPos;
    }

    /**
     * @return the animation speed
     */
    public float getAnimationSpeed() {
        return this.animationSpeed;
    }

    @Override
    public Position2D getCurrentPosition() {
        return this.currentPos;
    }

    @Override
    public void setCurrentPosition(final Position2D currentPosition) {
        this.currentPos = currentPosition;
    }

    /**
     * @return the final position of the {@link StraightAnimation}
     */
    public Position2D getTargetPosition() {
        return this.targetPos;
    }

    /**
     * @param targetPosition the final position of the {@link StraightAnimation}
     */
    public void setTargetPosition(final Position2D targetPosition) {
        this.targetPos = targetPosition;
    }

    @Override
    public boolean hasFinished() {
        return this.targetPos.equals(currentPos);
    }

    @Override
    public Position2D updateAnimation(final float animationSpeed) {
        this.animationSpeed = animationSpeed;
        this.calculateNext();
        return this.currentPos;
    }

    /**
     * function to personalize the animation behavior.
     */
    public abstract void calculateNext();
}
