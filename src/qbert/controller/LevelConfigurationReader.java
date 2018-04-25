package qbert.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import qbert.model.EnemyInfo;
import qbert.model.Level;

/**
 * The class reads the levels configuration from file.
 */
public class LevelConfigurationReader {

    private final Map<String, EnemyInfo> mapInfo;
    private int colorsNumber;
    private boolean reversible;

    /**
     * 
     */
    public LevelConfigurationReader() {
        this.mapInfo = new HashMap<>();
    }

    /**
     * This function reads the configuration of the specific level/round from file.
     * @param l the {@link Level} of witch the configuration must be read
     * @throws JDOMException when some jdom library error occur.
     */
    public void readLevelConfiguration(final Level l) throws JDOMException {
        try {
            final SAXBuilder builder = new SAXBuilder();
            final Document document = builder.build("res/levelconfig.xml");

            final Element root = document.getRootElement();
            final Element level = root.getChild("LEVEL" + l.getLevel());
            final Element round = level.getChild("ROUND" + l.getRound());
            this.colorsNumber = Integer.valueOf(round.getAttributeValue("colors"));
            this.reversible = Boolean.parseBoolean(round.getAttributeValue("reversible"));

            final List<Element> children = round.getChildren();
            final Iterator<Element> it = children.iterator();

            while (it.hasNext()) {
                final Element character = (Element) it.next();
                final String name = character.getName();
                final float speed = Float.valueOf(character.getAttributeValue("speed"));
                final int quantity = Integer.valueOf(character.getAttributeValue("quantity"));
                final int spawningTime = Integer.valueOf(character.getAttributeValue("spawningTime"));
                final int standingTime = Integer.valueOf(character.getAttributeValue("standingTime"));

                this.mapInfo.put(name, new EnemyInfo(speed, quantity, spawningTime, standingTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the map containing enemies information
     */
    public Map<String, EnemyInfo> getMapInfo() {
        return  Collections.unmodifiableMap(mapInfo);
    }

    /**
     * @return the number of colors to be set for each tile for the current level/round
     */
    public int getColorsNumber() {
        return this.colorsNumber;
    }

    /**
     * @return true if the tile is reversible, false otherwise.
     */
    public boolean isReversible() {
        return this.reversible;
    }
}
