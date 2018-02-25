package qbert.view.animations;

import qbert.model.utilities.Position2D;

public abstract class AnimationImpl implements Animation{

    private Position2D currentPos;
    private Position2D targetPos;
    private float animationSpeed;

    public AnimationImpl(final Position2D startPos) {
        this.currentPos = startPos;
    }

    @Override
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

    @Override
    public Position2D getTargetPosition() {
        return this.targetPos;
    }

    @Override
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

    @Override
    public abstract void calculateNext();
}
