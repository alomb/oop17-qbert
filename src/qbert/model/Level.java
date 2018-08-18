package qbert.model;

import java.util.List;

import qbert.model.characters.Player;
import qbert.model.models.Game;
import qbert.model.components.graphics.Renderable;

/**
 * Interface for managing core aspects of the game.
 */
public interface Level {

    void addObserver(Game gameObserver);

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
