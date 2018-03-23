package qbert.view.animations;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import qbert.model.utilities.Position2D;

/**
 * The basic animation for a jump, made by multiple basic animations.
 */
public abstract class Jump extends GenericAnimation {

    private final Queue<Animation> animations;
    private static final int QUEUESIZE = 2;

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     */
    public Jump(final Position2D startPos, final Position2D targetPos) {
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
    public static class DownRight extends Jump {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public DownRight(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(targetPos.getX(), startPos.getY());

            this.getAnimations().add(new MoveAnimation.ArcClockwise(startPos, intermediatePosition));
            this.getAnimations().add(new MoveAnimation.Down(intermediatePosition, targetPos));
        }
    }

    /**
     * Animation for a down-left jump.
     */
    public static class DownLeft extends Jump {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public DownLeft(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(targetPos.getX(), startPos.getY());

            this.getAnimations().add(new MoveAnimation.ArcCounterclockwise(startPos, intermediatePosition));
            this.getAnimations().add(new MoveAnimation.Down(intermediatePosition, targetPos));
        }
    }

    /**
     * Animation for a up-right jump.
     */
    public static class UpRight extends Jump {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public UpRight(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(startPos.getX(), targetPos.getY());

            this.getAnimations().add(new MoveAnimation.Up(startPos, intermediatePosition));
            this.getAnimations().add(new MoveAnimation.ArcClockwise(intermediatePosition, targetPos));

        }
    }

    /**
     * Animation for a up-left jump.
     */
    public static class UpLeft extends Jump {

        /**
         * @param startPos the first {@link Position2D}
         * @param targetPos the last {@link Position2D}
         */
        public UpLeft(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(startPos.getX(), targetPos.getY());

            this.getAnimations().add(new MoveAnimation.Up(startPos, intermediatePosition));
            this.getAnimations().add(new MoveAnimation.ArcCounterclockwise(intermediatePosition, targetPos));
        }
    }
}
