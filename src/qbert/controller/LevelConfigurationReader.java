package qbert.controller;

import java.util.Map;

import org.jdom2.JDOMException;

import qbert.model.EnemyInfoImpl;
import qbert.model.LevelSettings;
import qbert.model.LevelSettingsImpl;

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
     * @return the map containing enemies information
     */
    Map<String, EnemyInfoImpl> getMapInfo();

    /**
     * @return the current {@link LevelSettingsImpl}
     */
    LevelSettings getLevelSettings();

}
