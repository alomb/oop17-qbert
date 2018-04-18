package qbert.input;

import qbert.model.Character;
import qbert.model.Game;
import qbert.model.states.MoveState;

/**
 * The command used to manage the down-arrow key.
 */
public class MoveDown implements Command {

    @Override
    public void execute(final Game game) {
        Character qbert = game.getLevel().getQBert();
        if (!qbert.isMoving()) {
            qbert.setCurrentState(new MoveState.DownLeft(qbert));
        }
    }
}
