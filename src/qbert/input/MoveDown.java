package qbert.input;

import qbert.model.Game;
import qbert.model.states.MoveState;

/**
 * The command used to manage the down-arrow key.
 */
public class MoveDown implements Command {

    @Override
    public void execute(final Game game) {
        game.getLevel().getQBert().setCurrentState(new MoveState.DownLeft(game.getLevel().getQBert()));
    }
}
