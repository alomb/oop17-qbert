package qbert.view.animations;

import qbert.model.utilities.Position2D;

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
                this.moveArc.calculateNext();
            } else if (!this.moveDown.hasFinished()){
                this.moveDown.calculateNext();
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
                this.moveArc.calculateNext();
            } else if (!this.moveDown.hasFinished()){
                this.moveDown.calculateNext();
            }
        }
    }
}

