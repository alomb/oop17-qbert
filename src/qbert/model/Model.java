package qbert.model;

/**
 *
 */
public interface Model {

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
}
