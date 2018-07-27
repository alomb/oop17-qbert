package qbert.launcher;

import qbert.controller.Controller;
import qbert.controller.ControllerImpl;

/**
 * The class used to launch the game.
 */
public final class QBertLauncher {

    public QBertLauncher() {
        final Controller controller = new ControllerImpl();
        controller.setupGameEngine();
    }

}
