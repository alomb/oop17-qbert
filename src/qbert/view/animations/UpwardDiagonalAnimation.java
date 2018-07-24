package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 * An animation that provide a diagonal upward movement to a target point. 
 */
public class UpwardDiagonalAnimation extends MovementAnimation {

    private final double angle;
    private static final double SPEEDFACTOR = 0.65;

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     */
    public UpwardDiagonalAnimation(final Position2D startPos, final Position2D targetPos) {
        super(startPos, targetPos);
        final Position2D direction = new Position2D(targetPos);
        direction.vectorSum(new Position2D(-this.getCurrentPosition().getX(), 
                -this.getCurrentPosition().getY()));
        this.angle = Math.atan2(direction.getY(), direction.getX());
    }

    @Override
    protected final void calculateNext() {

        this.getCurrentPosition().vectorSum(
                new Position2D((int) (this.getAnimationSpeed() * UpwardDiagonalAnimation.SPEEDFACTOR * Math.cos(angle)), 
                        (int) (this.getAnimationSpeed() * UpwardDiagonalAnimation.SPEEDFACTOR * Math.sin(angle))));

        if (this.getCurrentPosition().getY() < this.getTargetPosition().getY()) {
            this.setCurrentPosition(new Position2D(this.getTargetPosition()));
        }
    }
}
