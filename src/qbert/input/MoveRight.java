package qbert.input;

import qbert.model.Character;
import qbert.model.Game;
import qbert.model.states.MoveState;

/**
 * The command used to manage the right-arrow key.
 */
public class MoveRight implements Command {

    @Override
    public void execute(final Game game) {
        final Character qbert = game.getLevel().getQBert();
        if (!qbert.isMoving()) {
            qbert.setCurrentState(new MoveState.DownRight(qbert));
        }
    }
}
