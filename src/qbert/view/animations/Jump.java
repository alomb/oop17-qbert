package qbert.view.animations;

import qbert.model.utilities.Position2D;

/*TODO: To refactor.*/

public abstract class Jump extends GenericAnimation{

    public Jump(Position2D startPos, Position2D targetPosition) {
        super(startPos);
        this.setTargetPosition(targetPosition);
    }

    @Override
    public abstract void calculateNext();

    public static class DownRight extends Jump {

        private final MoveDownAnimation moveDown;
        private final MoveArcClockwise moveArc;

        public DownRight(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(targetPos.getX(), startPos.getY());
            this.moveArc = new MoveArcClockwise(startPos, intermediatePosition);
            this.moveDown = new MoveDownAnimation(intermediatePosition, targetPos);
        }

        @Override
        public final void calculateNext() {
            if (!this.moveArc.hasFinished()) {
                this.setCurrentPosition(this.moveArc.updateAnimation(this.getAnimationSpeed()));
            } else if (!this.moveDown.hasFinished()){
                this.setCurrentPosition(this.moveDown.updateAnimation(this.getAnimationSpeed()));
            }
        }
    }

    public static class DownLeft extends Jump {

        private final MoveDownAnimation moveDown;
        private final MoveArcCounterClockwise moveArc;

        public DownLeft(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(targetPos.getX(), startPos.getY());
            this.moveArc = new MoveArcCounterClockwise(startPos, intermediatePosition);
            this.moveDown = new MoveDownAnimation(intermediatePosition, targetPos);
        }

        @Override
        public final void calculateNext() {
            if (!this.moveArc.hasFinished()) {
                this.setCurrentPosition(this.moveArc.updateAnimation(this.getAnimationSpeed()));
            } else if (!this.moveDown.hasFinished()) {
                this.setCurrentPosition(this.moveDown.updateAnimation(this.getAnimationSpeed()));
            }
        }
    }

    public static class UpRight extends Jump {

        private final MoveUpAnimation moveUp;
        private final MoveArcClockwise moveArc;

        public UpRight(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(startPos.getX(), targetPos.getY());
            this.moveArc = new MoveArcClockwise(startPos, intermediatePosition);
            this.moveUp = new MoveUpAnimation(intermediatePosition, targetPos);
        }

        @Override
        public final void calculateNext() {
            if (!this.moveUp.hasFinished()) {
                this.setCurrentPosition(this.moveUp.updateAnimation(this.getAnimationSpeed()));
            } else if (!this.moveArc.hasFinished()) {
                this.setCurrentPosition(this.moveArc.updateAnimation(this.getAnimationSpeed()));
            }
        }
    }

    public static class UpLeft extends Jump {

        private final MoveUpAnimation moveUp;
        private final MoveArcCounterClockwise moveArc;

        public UpLeft(final Position2D startPos, final Position2D targetPos) {
            super(startPos, targetPos);
            final Position2D intermediatePosition = new Position2D(startPos.getX(), targetPos.getY());
            this.moveArc = new MoveArcCounterClockwise(startPos, intermediatePosition);
            this.moveUp = new MoveUpAnimation(intermediatePosition, targetPos);
        }

        @Override
        public final void calculateNext() {
            if (!this.moveUp.hasFinished()) {
                this.setCurrentPosition(this.moveUp.updateAnimation(this.getAnimationSpeed()));
            } else if (!this.moveArc.hasFinished()) {
                this.setCurrentPosition(this.moveArc.updateAnimation(this.getAnimationSpeed()));
            }
        }
    }
}

