package qbert.input;

import qbert.model.Game;
import qbert.model.characters.Player;
import qbert.model.states.MoveState;

/**
 * The command used to manage the left-arrow key.
 */
public class MoveLeft implements Command {

    @Override
    public final void execute(final Game game) {
        final Player qbert = game.getLevel().getQBert();
        if (!qbert.isMoving() && !qbert.isDead()) {
            qbert.setCurrentState(new MoveState.UpLeft(qbert));
        }
    }
}
