package qbert.model;

import java.util.List;

import qbert.model.characters.Player;
import qbert.model.components.graphics.Renderable;
import qbert.model.scenes.Game;

/**
 * Interface for managing core aspects of the game.
 */
public interface Level {

    /**
     * @param gameObserver the {@link Game} who controls the level execution
     */
    void addObserver(Game gameObserver);

    /**
     * Notify to the {@link Game} the end of the level.
     */
    void notifyEndLevel();

    /**
     * @return Instance of {@link Qbert}
     */
    Player getQBert();

    /**
     * @return Current score
     */
    int getPoints();

    /**
     * @return Current {@link Qbert} lives amount
     */
    int getLives();

    /**
     * @return List of objects to be rendered in the view
     */
    List<Renderable> getRenderables();

    /**
     * @param elapsed the time passed since the last game cycle
     */
    void update(float elapsed);
}
