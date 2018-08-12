package qbert.model.characters.states;

import qbert.model.characters.Character;
import qbert.model.characters.DownUpwardCharacter;

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
        }
    }
}
