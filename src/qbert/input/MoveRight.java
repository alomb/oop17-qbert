package qbert.input;

import qbert.model.Game;
import qbert.model.states.MoveState;

/**
 * The command used to manage the right-arrow key.
 */
public class MoveRight implements Command {

    @Override
    public void execute(final Game game) {
        game.getLevel().getQBert().setCurrentState(new MoveState.DownRight(game.getLevel().getQBert()));
    }
}
