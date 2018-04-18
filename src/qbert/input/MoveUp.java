package qbert.input;

import qbert.model.Game;
import qbert.model.states.MoveState;

/**
 * The command used to manage the up-arrow key.
 */
public class MoveUp implements Command {

    @Override
    public void execute(final Game game) {
        game.getLevel().getQBert().setCurrentState(new MoveState.UpRight(game.getLevel().getQBert()));
    }
}
