package qbert.input;

import qbert.model.Model;

/**
 * The command used to manage the down-arrow key.
 */
public class MoveDown implements Command {

    @Override
    public final void execute(final Model model) {
        model.moveDown();
    }
}
