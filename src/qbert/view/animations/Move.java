package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 * The basic animation for straight and curvilinear movements. 
 */
public final class Move {

    private Move() {
        throw new UnsupportedOperationException();
    }

    private static Position2D calculateCircumferenceCoords(final Position2D centerPos, final int angle, final double radius) {
        return new Position2D(Math.round((float) (centerPos.getX() + Math.cos(Math.toRadians(angle)) * radius)),
                Math.round((float) (centerPos.getY() + Math.sin(Math.toRadians(angle)) * radius)));
    }

    /**
     * Animation for moving the sprite upward.
     */
    public static class Down extends GenericAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public Down(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
        }

        @Override
        public final void calculateNext() {
            this.getCurrentPosition().setY(this.getCurrentPosition().getY() + this.getAnimationSpeed());

            if (this.getTargetPosition().getY() < this.getCurrentPosition().getY()) {
                this.getCurrentPosition().setY(this.getTargetPosition().getY());
            }
        }
    }

    /**
     * Animation for moving the sprite upward.
     */
    public static class Up extends GenericAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public Up(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
        }

        @Override
        public final void calculateNext() {
            this.getCurrentPosition().setY(this.getCurrentPosition().getY() - this.getAnimationSpeed());

            if (this.getTargetPosition().getY() > this.getCurrentPosition().getY()) {
                this.getCurrentPosition().setY(this.getTargetPosition().getY());
            }
        }
    }

    /**
     * Animation to move the character in a clockwise arc.
     */
    public static class ArcClockwise extends GenericAnimation {

        private final double radius;
        private final Position2D centerPos;

        private int currentAngle;
        private static final int TARGETANGLE = 360;

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public ArcClockwise(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            this.radius = Math.abs(this.getCurrentPosition().getX() - targetPos.getX()) / 2;

            this.centerPos = new Position2D((int) (this.getCurrentPosition().getX() + this.radius), this.getCurrentPosition().getY());
            this.currentAngle = 180;
        }

        @Override
        public final void calculateNext() {
            this.currentAngle += this.getAnimationSpeed();
            if (this.currentAngle > ArcClockwise.TARGETANGLE) {
                this.currentAngle = ArcClockwise.TARGETANGLE;
                this.setCurrentPosition(this.getTargetPosition());
            } else {
                this.setCurrentPosition(Move.calculateCircumferenceCoords(this.centerPos, this.currentAngle, this.radius));
            }
        }
    }

    /**
     * Animation to move the character in a counterclockwise arc.
     */
    public static class ArcCounterclockwise extends GenericAnimation {

        private final double radius;
        private final Position2D centerPos;

        private int currentAngle;
        private static final int TARGETANGLE = -180;

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public ArcCounterclockwise(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            this.radius = Math.abs(this.getCurrentPosition().getX() - targetPos.getX()) / 2;

            this.centerPos = new Position2D((int) (this.getCurrentPosition().getX() - this.radius), this.getCurrentPosition().getY());
            this.currentAngle = 0;
        }

        @Override
        public final void calculateNext() {
            this.currentAngle -= this.getAnimationSpeed();
            if (this.currentAngle < ArcCounterclockwise.TARGETANGLE) {
                this.currentAngle = ArcCounterclockwise.TARGETANGLE;
                this.setCurrentPosition(this.getTargetPosition());
            } else {
                this.setCurrentPosition(Move.calculateCircumferenceCoords(this.centerPos, this.currentAngle, this.radius));
            }
        }
    }
}
