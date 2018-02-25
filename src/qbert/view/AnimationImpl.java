package qbert.view;

import qbert.model.utilities.Position2D;

public class AnimationImpl implements Animation{

    private Position2D currentPos;
    private Position2D targetPos;
    private float animationSpeed;

    public AnimationImpl(final Position2D startPos) {
        this.currentPos = startPos;
        this.targetPos = new Position2D(startPos.getX(), startPos.getY() + 50);
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
    public void calculateNext() {
        this.currentPos.setY(this.currentPos.getY() + this.animationSpeed);
    }
}
