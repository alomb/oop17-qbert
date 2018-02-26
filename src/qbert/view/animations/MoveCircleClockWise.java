package qbert.view.animations;

import qbert.model.utilities.Position2D;

public class MoveCircleClockWise implements Animation{

    private Position2D currentPos;
    private final int radius;
    private final Position2D centerPos;

    private int currentAngle;
    private final int targetAngle;

    /**
     * @param centerPos the circumference center {@link Position2D}
     * @param startAngle the first angle
     * @param targetAngle the last angle
     */
    public MoveCircleClockWise(final Position2D centerPos, final int startAngle, final int targetAngle) {
        this.centerPos = centerPos;
        this.radius = this.getCurrentPosition().distance(this.centerPos);

        this.currentAngle = startAngle;
        this.targetAngle = targetAngle;
    }

    @Override
    public boolean hasFinished() {
        return this.currentAngle == this.targetAngle;
    }

    private void calculatePosition() {
        this.getCurrentPosition().setX(Math.round((float) (this.centerPos.getX() + Math.cos(Math.toRadians(this.currentAngle)) * this.radius)));
        this.getCurrentPosition().setY(Math.round((float) (this.centerPos.getY() + Math.sin(Math.toRadians(this.currentAngle)) * this.radius)));
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
    public Position2D updateAnimation(final float animationSpeed) {
        if (!this.hasFinished()) {
            this.currentAngle += animationSpeed;
            if (this.currentAngle > this.targetAngle) {
                this.currentAngle = this.targetAngle;
            }

            this.calculatePosition();
        }
        return this.currentPos;
    }
}
