package qbert.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
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

import qbert.model.LevelSettings;
import qbert.model.LevelSettingsImpl;
import qbert.model.spawner.EnemyInfoImpl;
import qbert.model.sprites.ColorComposition;
import qbert.model.utilities.Sprites;

/**
 * The implementation of {@link LevelConfigurationReader}.
 */
public final class LevelConfigurationReaderImpl implements LevelConfigurationReader {

    private final Map<String, EnemyInfoImpl> mapInfo;
    private int colorsNumber;
    private boolean reversible;
    private int disksNumber;
    private float qbertSpeed;
    private ColorComposition colorComposition;

    /**
     * 
     */
    public LevelConfigurationReaderImpl() {
        this.mapInfo = new HashMap<>();

        this.setColorComposition();
    }

    @Override
    public void readLevelConfiguration(final int l, final int r) throws JDOMException {
        try {
            final SAXBuilder builder = new SAXBuilder();
            final Document document = 
                    builder.build(getClass().getResource("/levelconfig.xml").toString());

            final Element root = document.getRootElement();
            final Element level = root.getChild("LEVEL" + l);
            final Element round = level.getChild("ROUND" + r);
            this.colorsNumber = Integer.parseInt(round.getAttributeValue("colors"));
            this.reversible = Boolean.parseBoolean(round.getAttributeValue("reversible"));
            this.disksNumber = Integer.parseInt(round.getAttributeValue("disks"));
            this.qbertSpeed = Float.parseFloat(round.getAttributeValue("qbertSpeed"));

            final List<Element> children = round.getChildren();
            final Iterator<Element> it = children.iterator();

            while (it.hasNext()) {
                final Element character = (Element) it.next();
                final String name = character.getName();
                final float speed = Float.parseFloat(character.getAttributeValue("speed"));
                final int quantity = Integer.parseInt(character.getAttributeValue("quantity"));
                final int spawningTime = Integer.parseInt(character.getAttributeValue("spawningTime"));
                final int standingTime = Integer.parseInt(character.getAttributeValue("standingTime"));

                this.mapInfo.put(name, new EnemyInfoImpl(speed, quantity, spawningTime, standingTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LevelSettings getLevelSettings() {
        final Map<String, EnemyInfoImpl> mapInfo = this.getMapInfo();
        final Map<Integer, BufferedImage> colorMap = this.colorComposition.getColorComposition(colorsNumber);
        final BufferedImage background = this.colorComposition.getBackgroundImage();

        return new LevelSettingsImpl(colorsNumber, reversible, background, colorMap, disksNumber, mapInfo, qbertSpeed);
    }

    private void setColorComposition() {
        final int rand = new Random().nextInt(4) + 1;

        switch (rand) {
        case 1:
            colorComposition = Sprites.getInstance().getBlueColorComposition();
            break;
        case 2:
            colorComposition = Sprites.getInstance().getGreenColorComposition();
            break;
        case 3:
            colorComposition = Sprites.getInstance().getGreyColorComposition();
            break;
        default:
            colorComposition = Sprites.getInstance().getBrownColorComposition();
        }
    }

    private Map<String, EnemyInfoImpl> getMapInfo() {
        return Collections.unmodifiableMap(mapInfo);
    }
}
