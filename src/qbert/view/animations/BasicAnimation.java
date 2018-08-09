package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 * The basic animation extendible for straight and curvilinear movements. 
 */
public abstract class BasicAnimation extends MovementAnimation {

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     */
    public BasicAnimation(final Position2D startPos, final Position2D targetPos) {
        super(startPos, targetPos);
    }

    private static Position2D calculateCircumferenceCoords(final Position2D centerPos, final int angle, final double radius) {
        return new Position2D(Math.round((float) (centerPos.getX() + Math.cos(Math.toRadians(angle)) * radius)),
                Math.round((float) (centerPos.getY() + Math.sin(Math.toRadians(angle)) * radius)));
    }

    /**
     * Animation for moving the sprite upward.
     */
    public static class Down extends BasicAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public Down(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
        }

        @Override
        public final void calculateNext() {
            this.getCurrentPosition().setY((int) (this.getCurrentPosition().getY() + this.getAnimationSpeed()));

            if (this.getTargetPosition().getY() < this.getCurrentPosition().getY()) {
                this.getCurrentPosition().setY(this.getTargetPosition().getY());
            }
        }
    }

    /**
     * Animation for moving the sprite upward.
     */
    public static class Up extends BasicAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public Up(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
        }

        @Override
        public final void calculateNext() {
            this.getCurrentPosition().setY((int) (this.getCurrentPosition().getY() - this.getAnimationSpeed()));

            if (this.getTargetPosition().getY() > this.getCurrentPosition().getY()) {
                this.getCurrentPosition().setY(this.getTargetPosition().getY());
            }
        }
    }

    /**
     * Animation for moving the sprite rightward.
     */
    public static class Right extends BasicAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public Right(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
        }

        @Override
        public final void calculateNext() {
            this.getCurrentPosition().setX((int) (this.getCurrentPosition().getX() + this.getAnimationSpeed()));

            if (this.getTargetPosition().getX() < this.getCurrentPosition().getX()) {
                this.getCurrentPosition().setX(this.getTargetPosition().getX());
            }
        }
    }

    /**
     * Animation for moving the sprite leftward.
     */
    public static class Left extends BasicAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public Left(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
        }

        @Override
        public final void calculateNext() {
            this.getCurrentPosition().setX((int) (this.getCurrentPosition().getX() - this.getAnimationSpeed()));

            if (this.getTargetPosition().getX() > this.getCurrentPosition().getX()) {
                this.getCurrentPosition().setX(this.getTargetPosition().getX());
            }
        }
    }

    /**
     * Animation to move the character in a clockwise arc, increasing the angle. The Y-axis is
     * reversed so to increase y in the imaginary goniometric circle it must be decreased.
     */
    public static class ArcClockwise extends BasicAnimation {

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
        public ArcClockwise(final Position2D startPos, final Position2D targetPos, final int startAngle, final int targetAngle) {
            super(startPos, targetPos);

            //If the given target position has equals X coordinate then radius and centered is calculated on Y axis
            if (this.getCurrentPosition().getX() == targetPos.getX()) {
                this.radius = Math.abs(this.getCurrentPosition().getY() - targetPos.getY()) / 2;

                //If the target has an Y coordinate that is less than the current the center of the circumference is under the current position, above otherwise
                if (this.getCurrentPosition().getY() > this.getCurrentPosition().getY()) {
                    this.centerPos = new Position2D(this.getCurrentPosition().getX(), (int) (this.getCurrentPosition().getY() - this.radius));
                } else {
                    this.centerPos = new Position2D(this.getCurrentPosition().getX(), (int) (this.getCurrentPosition().getY() + this.radius));
                }
            } else {
                this.radius = Math.abs(this.getCurrentPosition().getX() - targetPos.getX()) / 2;
                this.centerPos = new Position2D((int) (this.getCurrentPosition().getX() + this.radius), this.getCurrentPosition().getY());
            }

            this.currentAngle = startAngle;
            this.targetAngle = targetAngle;
        }

        @Override
        public final void calculateNext() {
            this.currentAngle += this.getAnimationSpeed();
            if (this.currentAngle > this.targetAngle) {
                this.currentAngle = this.targetAngle;
                this.setCurrentPosition(this.getTargetPosition());
            } else {
                this.setCurrentPosition(BasicAnimation.calculateCircumferenceCoords(this.centerPos, this.currentAngle, this.radius));
            }
        }
    }

    /**
     * Animation to move the character in a counterclockwise arc, decreasing the angle. The Y-axis is
     * reversed so to increase y in the imaginary goniometric circle it must be decreased.
     */
    public static class ArcCounterclockwise extends BasicAnimation {

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
        public ArcCounterclockwise(final Position2D startPos, final Position2D targetPos, final int startAngle, final int targetAngle) {
            super(startPos, targetPos);

            //If the given target position has equals X coordinate then radius and centered is calculated on Y axis
            if (this.getCurrentPosition().getX() == targetPos.getX()) {
                this.radius = Math.abs(this.getCurrentPosition().getY() - targetPos.getY()) / 2;

                //If the target has an Y coordinate that is more than the current the center of the circumference is above the current position, under otherwise
                if (this.getCurrentPosition().getY() < this.getCurrentPosition().getY()) {
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
                this.setCurrentPosition(BasicAnimation.calculateCircumferenceCoords(this.centerPos, this.currentAngle, this.radius));
            }
        }
    }
}
