package qbert.model.components.graphics;

/**
 * A specialization of {@link DownUpwardCharacterGC} used for {@link Snake}.
 */
public interface CoilyGC extends DownUpwardCharacterGC {

    /**
     * The method called when {@link Snake} becomes adult.
     */
    void transform();

}
