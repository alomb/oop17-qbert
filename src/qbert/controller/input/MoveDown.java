package qbert.controller.input;

import qbert.model.models.Model;

/**
 * The command used to manage the down-arrow key.
 */
public class MoveDown implements Command {

    @Override
    public final void execute(final Model model) {
        model.moveDown();
    }
}
