package qbert.controller;

import org.jdom2.JDOMException;
import qbert.model.LevelSettings;

/**
 * The interface for the reading of the levels configuration from file.
 */
public interface LevelConfigurationReader {

    /**
     * This method reads the configuration of the specific level/round from file.
     * @param l the level that must be loaded
     * @param r the round that must be loaded
     * @throws JDOMException when some jdom library error occur
     */
    void readLevelConfiguration(int l, int r) throws JDOMException;

    /**
     * @return the current {@link LevelSettingsImpl}
     */
    LevelSettings getLevelSettings();
}
