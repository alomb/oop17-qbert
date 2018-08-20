package qbert.controller.input;

import qbert.model.scenes.Model;

/**
 * The command used to manage the right-arrow key.
 */
public class MoveRight implements Command {

    @Override
    public final void execute(final Model model) {
        model.moveRight();
    }
}
