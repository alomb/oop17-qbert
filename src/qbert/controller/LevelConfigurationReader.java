package qbert.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import qbert.model.EnemyInfo;
import qbert.model.LevelSettings;
import qbert.view.ColorComposition;

/**
 * The class reads the levels configuration from file.
 */
public class LevelConfigurationReader {

    private final Map<String, EnemyInfo> mapInfo;
    private int colorsNumber;
    private boolean reversible;
    private ColorComposition colorComposition;

    /**
     * 
     */
    public LevelConfigurationReader() {
        this.mapInfo = new HashMap<>();

        this.setColorComposition();
    }

    /**
     * This method reads the configuration of the specific level/round from file.
     * @param l the level that must be loaded
     * @param r the round that must be loaded
     * @throws JDOMException when some jdom library error occur
     */
    public void readLevelConfiguration(final int l, final int r) throws JDOMException {
        try {
            final SAXBuilder builder = new SAXBuilder();
            final Document document = builder.build("res/levelconfig.xml");

            final Element root = document.getRootElement();
            final Element level = root.getChild("LEVEL" + l);
            final Element round = level.getChild("ROUND" + r);
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
        return Collections.unmodifiableMap(mapInfo);
    }

    /**
     * @return the current {@link LevelSettings}
     */
    public LevelSettings getLevelSettings() {
        final Map<Integer, BufferedImage> colorMap = this.colorComposition.getColorComposition(colorsNumber);
        final BufferedImage background = this.colorComposition.getBackgroundImage();

        return new LevelSettings(colorsNumber, reversible, background, colorMap);
    }

    private void setColorComposition() {
        final int rand = new Random().nextInt(4) + 1;

        try {
            final Class<?> cl = Class.forName("qbert.view.ColorCompositionImpl" + rand);
            final Constructor<?> cns = cl.getConstructor();
            final ColorComposition cc = (ColorComposition) cns.newInstance();

            this.colorComposition = cc;
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
