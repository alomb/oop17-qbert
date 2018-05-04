package qbert.model.states;

import qbert.model.characters.Character;
import qbert.model.utilities.Position2D;

/**
 * The {@link CharacterState} used for movement states, e.g. falls, jumps..
 * MoveState conclude() method (which is final) changes the {@link Character}'s current state in the relative Standing State.
 */
public abstract class MoveState extends WaitAnimationState {

    /**
     * @param character the relative {@link Character}
     */
    public MoveState(final Character character) {
        super(character);
    }

    @Override
    public abstract void conclude();

    /**
     * The {@link CharacterState} for spawning.
     */
    public static class Spawn extends MoveState {

        /**
         * @param character the relative {@link Character}
         */
        public Spawn(final Character character) {
            super(character);
            this.getCharacter().setDead(false);
            this.getCharacter().getGraphicComponent().setSpawnAnimation();
        }

        @Override
        public final void conclude() {
            this.getCharacter().setCurrentState(new LandState(this.getCharacter()));
        }
    }

    /**
     * The {@link CharacterState} for falling.
     */
    public static class Fall extends MoveState {

        /**
         * @param character the relative {@link Character}
         */
        public Fall(final Character character) {
            super(character);
            this.getCharacter().getGraphicComponent().setFallAnimation();
        }

        @Override
        public final void conclude() {
            this.getCharacter().setCurrentState(new DeathState(this.getCharacter()));
        }
    }

    /**
     * The {@link CharacterState} for the down-left movement.
     */
    public static class DownLeft extends MoveState {

        /**
         * @param character the relative {@link Character}
         */
        public DownLeft(final Character character) {
            super(character);
            this.getCharacter().getGraphicComponent().setMoveDownLeftAnimation();
            this.getCharacter().setNextPosition(
                    new Position2D(getCharacter().getCurrentPosition().getX() - 1, getCharacter().getCurrentPosition().getY() - 1));
        }

        @Override
        public final void conclude() {
            this.getCharacter().setCurrentState(new LandState(this.getCharacter()));
        }
    }

    /**
     * The {@link CharacterState} for the down-right movement.
     */
    public static class DownRight extends MoveState {

        /**
         * @param character the relative {@link Character}
         */
        public DownRight(final Character character) {
            super(character);
            this.getCharacter().getGraphicComponent().setMoveDownRightAnimation();
            this.getCharacter().setNextPosition(
                    new Position2D(getCharacter().getCurrentPosition().getX() + 1, getCharacter().getCurrentPosition().getY() - 1));
        }

        @Override
        public final void conclude() {
            this.getCharacter().setCurrentState(new LandState(this.getCharacter()));
        }
    }

    /**
     * The {@link CharacterState} for the up-right movement.
     */
    public static class UpRight extends MoveState {

        /**
         * @param character the relative {@link Character}
         */
        public UpRight(final Character character) {
            super(character);
            this.getCharacter().getGraphicComponent().setMoveUpRightAnimation();
            this.getCharacter().setNextPosition(
                    new Position2D(getCharacter().getCurrentPosition().getX() + 1, getCharacter().getCurrentPosition().getY() + 1));
        }

        @Override
        public final void conclude() {
            this.getCharacter().setCurrentState(new LandState(this.getCharacter()));
        }
    }

    /**
     * The {@link CharacterState} for the up-left movement.
     */
    public static class UpLeft extends MoveState {

        /**
         * @param character the relative {@link Character}
         */
        public UpLeft(final Character character) {
            super(character);
            this.getCharacter().getGraphicComponent().setMoveUpLeftAnimation();
            this.getCharacter().setNextPosition(
                    new Position2D(getCharacter().getCurrentPosition().getX() - 1, getCharacter().getCurrentPosition().getY() + 1));
        }

        @Override
        public final void conclude() {
            this.getCharacter().setCurrentState(new LandState(this.getCharacter()));
        }
    }
}
