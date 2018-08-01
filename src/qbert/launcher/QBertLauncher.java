package qbert.launcher;

import qbert.controller.Controller;
import qbert.controller.ControllerImpl;
import qbert.controller.GameStatus;
import qbert.controller.LoadResources;

/**
 * The class used to launch the game.
 */
public final class QBertLauncher {

    public static void main(String[] args) {
        new QBertLauncher();
    }

    public QBertLauncher() {
        LoadResources lr = new LoadResources();
        lr.load();
        final Controller controller = new ControllerImpl(GameStatus.INTRODUCTION);
        controller.setupGameEngine();
    }

}
