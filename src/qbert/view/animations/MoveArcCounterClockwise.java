package qbert.view.animations;

import qbert.model.utilities.Position2D;

public class MoveArcCounterClockwise extends GenericAnimation {

    private final double radius;
    private final Position2D centerPos;

    private int currentAngle;
    private final int targetAngle = -180;

    public MoveArcCounterClockwise(final Position2D startPos, final Position2D targetPos) {
        super(startPos);
        this.setTargetPosition(targetPos);
        this.radius = Math.abs(this.getCurrentPosition().getX() - targetPos.getX()) / 2;
        this.centerPos = new Position2D((int) (this.getCurrentPosition().getX() - this.radius), this.getCurrentPosition().getY());

        this.currentAngle = 0;
    }

    private void calculatePosition() {
        this.getCurrentPosition().setX(Math.round((float) (this.centerPos.getX() + Math.cos(Math.toRadians(this.currentAngle)) * this.radius)));
        this.getCurrentPosition().setY(Math.round((float) (this.centerPos.getY() + Math.sin(Math.toRadians(this.currentAngle)) * this.radius)));
    }

    @Override
    public void calculateNext() {
        this.currentAngle -= this.getAnimationSpeed();
        if (this.currentAngle < this.targetAngle) {
            this.currentAngle = this.targetAngle;
            this.setCurrentPosition(this.getTargetPosition());
        } else {
            this.calculatePosition();
        }
    }
}
