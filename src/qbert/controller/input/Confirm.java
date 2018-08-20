package qbert.controller.input;

import qbert.model.scenes.Model;

/**
 * The command used to manage the enter key.
 */
public class Confirm implements Command {

    @Override
    public final void execute(final Model model) {
        model.confirm();
    }
}
