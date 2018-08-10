package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 * Animation to move the character in a counterclockwise arc, decreasing the angle. The Y-axis is
 * reversed so to increase y in the imaginary goniometric circle it must be decreased.
 */
public class ArcCounterclockwiseAnimation extends MovementAnimation {

    private final double radius;
    private final Position2D centerPos;

    private int currentAngle;
    private final int targetAngle;

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     * @param startAngle the first current angle value
     * @param targetAngle the last current angle value
     */
    public ArcCounterclockwiseAnimation(final Position2D startPos, final Position2D targetPos, final int startAngle, final int targetAngle) {
        super(startPos, targetPos);

        //If the given target position has equals X coordinate then radius and centered is calculated on Y axis
        if (this.getCurrentPosition().getX() == targetPos.getX()) {
            this.radius = Math.abs(this.getCurrentPosition().getY() - targetPos.getY()) / 2;

            //If the target has an Y coordinate that is more than the current the center of the circumference is above the current position, under otherwise
            if (this.getCurrentPosition().getY() < this.getTargetPosition().getY()) {
                this.centerPos = new Position2D(this.getCurrentPosition().getX(), (int) (this.getCurrentPosition().getY() + this.radius));
            } else {
                this.centerPos = new Position2D(this.getCurrentPosition().getX(), (int) (this.getCurrentPosition().getY() - this.radius)); 
            }
        } else {
            this.radius = Math.abs(this.getCurrentPosition().getX() - targetPos.getX()) / 2;
            this.centerPos = new Position2D((int) (this.getCurrentPosition().getX() - this.radius), this.getCurrentPosition().getY());
        }

        this.currentAngle = startAngle;
        this.targetAngle = targetAngle;
    }

    @Override
    public final void calculateNext() {
        this.currentAngle -= this.getAnimationSpeed();
        if (this.currentAngle < this.targetAngle) {
            this.currentAngle = this.targetAngle;
            this.setCurrentPosition(this.getTargetPosition());
        } else {
            this.setCurrentPosition(MovementAnimation.calculateCircumferenceCoords(this.centerPos, this.currentAngle, this.radius));
        }
    }
}
