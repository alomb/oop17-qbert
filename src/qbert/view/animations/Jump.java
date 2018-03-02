package qbert.view.animations;

import java.util.ArrayDeque;
import java.util.Deque;

import qbert.model.utilities.Position2D;

public abstract class Jump extends GenericAnimation {

    private final Deque<Animation> animations;

    public Jump(final Position2D startPos, final Position2D targetPosition) {
        super(startPos);
        this.setTargetPosition(targetPosition);
        this.animations = new ArrayDeque<Animation>();
    }

    @Override
    public final boolean hasFinished() {
        return this.animations.isEmpty();
    }

    @Override
    public final void calculateNext() {
        if (!this.hasFinished()) {
            if (this.animations.getFirst().hasFinished()) {
                this.animations.poll();
            }
            final Animation currentAnimation = this.animations.peekFirst();
            if (currentAnimation != null) {
                this.setCurrentPosition(currentAnimation.updateAnimation(this.getAnimationSpeed()));
            }
        }
    }

    public final Deque<Animation> getAnimations() {
        return animations;
    }

    public static class DownRight extends Jump {

        public DownRight(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(targetPos.getX(), startPos.getY());
            this.getAnimations().push(new MoveArcClockwise(startPos, intermediatePosition));
            this.getAnimations().push(new MoveDownAnimation(intermediatePosition, targetPos));
        }
    }

    public static class DownLeft extends Jump {

        public DownLeft(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(targetPos.getX(), startPos.getY());
            this.getAnimations().push(new MoveArcCounterClockwise(startPos, intermediatePosition));
            this.getAnimations().push(new MoveDownAnimation(intermediatePosition, targetPos));
        }
    }

    public static class UpRight extends Jump {

        public UpRight(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(startPos.getX(), targetPos.getY());
            this.getAnimations().push(new MoveUpAnimation(intermediatePosition, targetPos));
            this.getAnimations().push(new MoveArcClockwise(startPos, intermediatePosition));
        }
    }

    public static class UpLeft extends Jump {

        public UpLeft(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(startPos.getX(), targetPos.getY());
            this.getAnimations().push(new MoveUpAnimation(intermediatePosition, targetPos));
            this.getAnimations().push(new MoveArcCounterClockwise(startPos, intermediatePosition));
        }
    }
}
