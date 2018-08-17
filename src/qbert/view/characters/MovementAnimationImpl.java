package qbert.view.characters;

import qbert.model.utilities.Position2D;

/**
 * A movement-oriented implementation of {@link MovementAnimationImpl} interface.
 */
public abstract class MovementAnimationImpl implements MovementAnimation {

    private Position2D currentPos;
    private final Position2D targetPos;
    private float animationSpeed;

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     */
    public MovementAnimationImpl(final Position2D startPos, final Position2D targetPos) {
        this.currentPos = startPos;
        this.targetPos = targetPos;
    }

    @Override
    public final boolean hasNext() {
        return this.targetPos.equals(currentPos);
    }

    @Override
    public final Position2D updateAnimation(final float animationSpeed) {
        this.animationSpeed = animationSpeed;
        if (!this.hasNext()) {
            this.currentPos = new Position2D(this.next());
        }
        this.animationSpeed = 0;
        return this.currentPos;
    }

    /**
     * This method must be implemented by concrete animation classes to personalize the animation.
     * It is called in updateAnimation(), when hasFinished() return false.
     */
    @Override
    public abstract Position2D next();

    /**
     * @return the animation speed
     */
    protected final float getAnimationSpeed() {
        return this.animationSpeed;
    }

    /**
     * @return the current {@link Position2D} of the animation
     */
    protected final Position2D getCurrentPosition() {
        return this.currentPos;
    }

    /**
     * @return the the last {@link Position2D} of the animation
     */
    protected Position2D getTargetPosition() {
        return this.targetPos;
    }

    /**
     * @param centerPos the circumference center {@link Position2D}
     * @param angle the rotation angle
     * @param radius the circumference radius
     * @return the {@link Position2D} after the rotation
     */
    protected Position2D calculateCircumferenceCoords(final Position2D centerPos, final int angle, final double radius) {
        return new Position2D(Math.round((float) (centerPos.getX() + Math.cos(Math.toRadians(angle)) * radius)),
                Math.round((float) (centerPos.getY() + Math.sin(Math.toRadians(angle)) * radius)));
    }
}
