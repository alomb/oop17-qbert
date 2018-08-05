package qbert.model.characters.states;

import qbert.model.characters.Character;
import qbert.model.characters.DownUpwardCharacter;
import qbert.model.utilities.Position2D;

/**
 * The {@link CharacterState} used for all directions movement states.
 */
public abstract class MoveState extends WaitAnimationState {

    /**
     * @param character the relative {@link Character}
     */
    public MoveState(final Character character) {
        super(character);
    }

    @Override
    public final void conclude() {
        this.getCharacter().setCurrentState(new LandState(this.getCharacter()));
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
    }

    /**
     * The {@link CharacterState} for the up-right movement.
     */
    public static class UpRight extends MoveState {

        /**
         * @param character the relative {@link DownUpwardCharacter}
         */
        public UpRight(final DownUpwardCharacter character) {
            super(character);
            character.getDownUpwardGraphicComponent().setMoveUpRightAnimation();
            this.getCharacter().setNextPosition(
                    new Position2D(getCharacter().getCurrentPosition().getX() + 1, getCharacter().getCurrentPosition().getY() + 1));
        }
    }

    /**
     * The {@link CharacterState} for the up-left movement.
     */
    public static class UpLeft extends MoveState {

        /**
         * @param character the relative {@link DownUpwardCharacter}
         */
        public UpLeft(final DownUpwardCharacter character) {
            super(character);
            character.getDownUpwardGraphicComponent().setMoveUpLeftAnimation();
            this.getCharacter().setNextPosition(
                    new Position2D(getCharacter().getCurrentPosition().getX() - 1, getCharacter().getCurrentPosition().getY() + 1));
        }
    }
}
