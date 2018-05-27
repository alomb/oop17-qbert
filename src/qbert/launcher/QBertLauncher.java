package qbert.launcher;

import qbert.controller.GameEngine;

/**
 * The class used to launch the game.
 */
public final class QBertLauncher {

    private QBertLauncher() {
        final GameEngine test = new GameEngine();
        test.setup();
        test.mainLoop();
    }

}
