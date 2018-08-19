package qbert.controller;

import org.jdom2.JDOMException;
import java.io.IOException;
import qbert.model.LevelSettings;

/**
 * The interface for the reading of the levels configuration from file.
 */
public interface LevelConfigurationReader {

    /**
     * This method reads the configuration of the specific level/round from file.
     * @param levelNumber the level that must be loaded
     * @param roundNumber the round that must be loaded
     * @throws JDOMException when some jdom library error occur
     * @throws IOException when some input/output error occur
     */
    void readLevelConfiguration(int levelNumber, int roundNumber) throws JDOMException, IOException;

    /**
     * @return the current {@link LevelSettingsImpl}
     */
    LevelSettings getLevelSettings();
}
