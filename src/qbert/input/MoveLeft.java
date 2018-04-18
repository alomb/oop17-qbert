package qbert.input;

import qbert.model.Game;
import qbert.model.states.MoveState;

/**
 * The command used to manage the left-arrow key.
 */
public class MoveLeft implements Command {

    @Override
    public void execute(final Game game) {
        game.getLevel().getQBert().setCurrentState(new MoveState.UpLeft(game.getLevel().getQBert()));
    }
}
