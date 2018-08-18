package qbert.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import qbert.model.LevelSettings;
import qbert.model.LevelSettingsImpl;
import qbert.model.characters.CharactersList;
import qbert.model.spawner.EnemyInfoImpl;
import qbert.model.sprites.ColorComposition;

/**
 * The implementation of {@link LevelConfigurationReader}.
 */
public final class LevelConfigurationReaderImpl implements LevelConfigurationReader {

    private final Map<CharactersList, EnemyInfoImpl> mapInfo;
    private int colorsNumber;
    private boolean reversible;
    private int disksNumber;
    private float qbertSpeed;
    private ColorComposition colorComposition;

    private final Sprites sprites;

    /**
     * @throws IOException 
     * 
     */
    public LevelConfigurationReaderImpl() throws IOException {
        this.mapInfo = new HashMap<>();

        this.sprites = Sprites.getInstance();
        this.setColorComposition();
    }

    @Override
    public void readLevelConfiguration(final int levelNumber, final int roundNumber) throws JDOMException {
        try {
            final SAXBuilder builder = new SAXBuilder();
            final Document document = 
                    builder.build(getClass().getResource("/levelconfig.xml").toString());

            final Element root = document.getRootElement();
            final Element level = root.getChild("LEVEL" + levelNumber);
            final Element round = level.getChild("ROUND" + roundNumber);
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

                this.mapInfo.put(CharactersList.getEnumConstantByValue(name), new EnemyInfoImpl(speed, quantity, spawningTime, standingTime));
            }
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public LevelSettings getLevelSettings() {
        final Map<CharactersList, EnemyInfoImpl> mapInfo = this.getMapInfo();
        final Map<Integer, BufferedImage> colorMap = this.colorComposition.getColorComposition(colorsNumber);
        final BufferedImage background = this.colorComposition.getBackgroundImage();

        return new LevelSettingsImpl(colorsNumber, reversible, background, colorMap, disksNumber, mapInfo, qbertSpeed);
    }

    private void setColorComposition() {
        final int rand = new Random().nextInt(4) + 1;

        switch (rand) {
        case 1:
            colorComposition = this.sprites.getBlueColorComposition();
            break;
        case 2:
            colorComposition = this.sprites.getGreenColorComposition();
            break;
        case 3:
            colorComposition = this.sprites.getGreyColorComposition();
            break;
        default:
            colorComposition = this.sprites.getBrownColorComposition();
        }
    }

    private Map<CharactersList, EnemyInfoImpl> getMapInfo() {
        return Collections.unmodifiableMap(mapInfo);
    }
}
