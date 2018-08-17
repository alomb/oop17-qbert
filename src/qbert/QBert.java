package qbert;

import qbert.controller.Controller;
import qbert.controller.ControllerImpl;
import qbert.controller.GameStatus;
import qbert.controller.LoadResources;
import qbert.controller.LoggerManager;

/**
 * The class used to launch the game.
 */
public final class QBert {

    /**
     * @param args the argument passed by the console
     */
    public static void main(final String[] args) {
        new QBert();
    }

    private QBert() {
        new LoggerManager();
        final LoadResources lr = new LoadResources();
        if (lr.load()) {
            final Controller controller = new ControllerImpl(GameStatus.MENU);
            controller.setupGameEngine();
        }
    }

}
