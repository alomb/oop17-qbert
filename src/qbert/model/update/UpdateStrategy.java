package qbert.model.update;

/**
 * Interface for various implementations for updating game entities.
 */
public interface UpdateStrategy {

    /**
     * @param elapsed the time passed since the last game cycle
     */
    void update(float elapsed);

}
