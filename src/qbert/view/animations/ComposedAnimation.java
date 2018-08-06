package qbert.view.animations;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import qbert.model.utilities.Position2D;

/**
 * An animation made by multiple basic animations stored in a queue.
 */
public class ComposedAnimation extends MovementAnimation {

    private final Queue<Animation> animations;
    private static final int QUEUESIZE = 2;

    private static final int ANGLE0 = 0;
    private static final int ANGLE90 = 90;
    private static final int ANGLE180 = 180;
    private static final int ANGLE270 = 270;
    private static final int ANGLE360 = 360;

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     */
    public ComposedAnimation(final Position2D startPos, final Position2D targetPos) {
        super(startPos, targetPos);
        this.animations = new ArrayBlockingQueue<>(QUEUESIZE);
    }

    @Override
    public final void calculateNext() {
        if (!this.animations.isEmpty()) {
            if (this.animations.peek().hasFinished()) {
                this.animations.remove();
            }
            /*In all cases updates the animation if it's not finished, or the next one, if it isn't the last.*/
            if (!this.animations.isEmpty()) {
                this.setCurrentPosition(this.animations.peek().updateAnimation(this.getAnimationSpeed()));
            }
        }
    }

    /**
     * @return the queue of animations to perform
     */
    public final Queue<Animation> getAnimations() {
        return animations;
    }

    /**
     * Animation for a down-right jump.
     */
    public static class JumpDownRight extends ComposedAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public JumpDownRight(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(targetPos.getX(), startPos.getY());

            this.getAnimations().add(new BasicAnimation.ArcClockwise(startPos, intermediatePosition, ANGLE180, ANGLE360));
            this.getAnimations().add(new BasicAnimation.Down(intermediatePosition, targetPos));
        }
    }

    /**
     * Animation for a down-right jump -90 degrees rotated. Up becomes Left, Down becomes Right.
     */
    public static class JumpDownRightRightward extends ComposedAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public JumpDownRightRightward(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(startPos.getX(), targetPos.getY());

            this.getAnimations().add(new BasicAnimation.ArcCounterclockwise(startPos, intermediatePosition, ANGLE270, ANGLE90));
            this.getAnimations().add(new BasicAnimation.Right(intermediatePosition, targetPos));
        }
    }

    /**
     * Animation for a down-left jump.
     */
    public static class JumpDownLeft extends ComposedAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public JumpDownLeft(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(targetPos.getX(), startPos.getY());

            this.getAnimations().add(new BasicAnimation.ArcCounterclockwise(startPos, intermediatePosition, ANGLE0, -ANGLE180));
            this.getAnimations().add(new BasicAnimation.Down(intermediatePosition, targetPos));
        }
    }

    /**
     * Animation for a down-left jump -90 degrees rotated. Up becomes Left, Down becomes Right.
     */
    public static class JumpDownLeftRightward extends ComposedAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public JumpDownLeftRightward(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(startPos.getX(), targetPos.getY());

            this.getAnimations().add(new BasicAnimation.ArcClockwise(startPos, intermediatePosition, ANGLE90, ANGLE270));
            this.getAnimations().add(new BasicAnimation.Right(intermediatePosition, targetPos));
        }
    }

    /**
     * Animation for a up-right jump.
     */
    public static class JumpUpRight extends ComposedAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public JumpUpRight(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(startPos.getX(), targetPos.getY());

            this.getAnimations().add(new BasicAnimation.Up(startPos, intermediatePosition));
            this.getAnimations().add(new BasicAnimation.ArcClockwise(intermediatePosition, targetPos, ANGLE180, ANGLE360));

        }
    }

    /**
     * Animation for a up-left jump.
     */
    public static class JumpUpLeft extends ComposedAnimation {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public JumpUpLeft(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(startPos.getX(), targetPos.getY());

            this.getAnimations().add(new BasicAnimation.Up(startPos, intermediatePosition));
            this.getAnimations().add(new BasicAnimation.ArcCounterclockwise(intermediatePosition, targetPos, ANGLE0, -ANGLE180));
        }
    }
}
