package qbert.view.animations;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import qbert.model.utilities.Position2D;

/**
 * The basic animation for a jump, made by multiple basic animations.
 */
public abstract class Jump extends GenericAnimation {

    private final Queue<Animation> animations;

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     */
    public Jump(final Position2D startPos, final Position2D targetPos) {
        super(startPos);
        this.setTargetPosition(targetPos);
        this.animations = new ArrayBlockingQueue<>(2);
    }

    @Override
    public final boolean hasFinished() {
        return this.animations.isEmpty();
    }

    @Override
    public final void calculateNext() {
        if (!this.hasFinished()) {
            if (this.animations.peek().hasFinished()) {
                @SuppressWarnings("unused")
                final Animation polledAnimation = this.animations.poll();
            }
            final Animation currentAnimation = this.animations.peek();
            if (currentAnimation != null) {
                this.setCurrentPosition(currentAnimation.updateAnimation(this.getAnimationSpeed()));
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

            this.getAnimations().add(new MoveArcClockwise(startPos, intermediatePosition));
            this.getAnimations().add(new MoveDownAnimation(intermediatePosition, targetPos));
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

            this.getAnimations().add(new MoveArcCounterClockwise(startPos, intermediatePosition));
            this.getAnimations().add(new MoveDownAnimation(intermediatePosition, targetPos));
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

            this.getAnimations().add(new MoveUpAnimation(startPos, intermediatePosition));
            this.getAnimations().add(new MoveArcClockwise(intermediatePosition, targetPos));

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

            this.getAnimations().add(new MoveUpAnimation(startPos, intermediatePosition));
            this.getAnimations().add(new MoveArcCounterClockwise(intermediatePosition, targetPos));
        }
    }
}
