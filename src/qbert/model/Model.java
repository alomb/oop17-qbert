package qbert.model;

import java.util.List;

import qbert.view.Renderable;

/**
 * The interface for a generic MVC model class, enabled to store data and manage some logic and
 * process input from {@link Controller}.
 */
public interface Model {

    /**
     * The method to start the model work.
     */
    void initialize();

    /**
     * @param elapsed the time elapsed since the last loop cycle.
     */
    void update(float elapsed);

    /**
     * The code associated to the move down command/event.
     */
    void moveDown();

    /**
     * The code associated to the move left command/event.
     */
    void moveLeft();

    /**
     * The code associated to the move right command/event.
     */
    void moveRight();

    /**
     * The code associated to the move up command/event.
     */
    void moveUp();

    /**
     * The code associated to the confirm command/event. 
     */
    void confirm();

    /**
     * @return a list of GUI to be rendered
     */
    List<GUILogic> getGUI();

    /**
     * @return a list of images to be rendered
     */
    List<Renderable> getRenderables();

    /**
     * @return true if the model wants to communicate that is job is done
     */
    boolean hasFinished();
}
