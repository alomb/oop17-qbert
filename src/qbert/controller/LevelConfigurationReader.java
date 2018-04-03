package qbert.controller;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import qbert.model.Level;
import qbert.model.Sprites;
import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;
import qbert.view.CharacterGraphicComponentImpl;
import qbert.model.Character;
import qbert.model.Dimensions;

import java.util.Random;

/**
 *
 */
public class LevelConfigurationReader {
    
    private final List<Character> levelCharacters;
    private final Map<String, EnemyInfo> mapInfo;

    public LevelConfigurationReader() {
        this.levelCharacters = new ArrayList<>();
        this.mapInfo = new HashMap<>();
    }
    
    public List<Character> readLevelConfiguration(final Level l) throws JDOMException {
        try {
            final SAXBuilder builder = new SAXBuilder();
            final Document document = builder.build("/levelconfig.xml");

            final Element root = document.getRootElement();
            final Element level = root.getChild("LEVEL" + l.getLevel());
            final Element round = level.getChild("ROUND" + l.getRound());
            final int colorsNumber = Integer.valueOf(round.getAttributeValue("colors"));
            final boolean reversable = Boolean.parseBoolean(round.getAttributeValue("reversable"));
            /* settare colorsNumber e reversable del livello */

            final List<Element> children = round.getChildren();
            final Iterator<Element> it = children.iterator();

            while (it.hasNext()) {
                final Element character = (Element) it.next();
                final String name = character.getName();
                final float speed = Float.valueOf(character.getAttributeValue("speed"));

                final int quantity = Integer.valueOf(character.getAttributeValue("quantity"));
                final int spawningTime = Integer.valueOf(character.getAttributeValue("spawningTime"));
                final int standingTime = Integer.valueOf(character.getAttributeValue("standingTime"));
                
                this.mapInfo.put(name, this.new EnemyInfo(quantity, spawningTime, standingTime));

                final Position2D randomPos = new Random().nextInt(2) == 0 ? Dimensions.spawingPointLeft : Dimensions.spawingPointRight;
                
                /* */
                switch(name) {
                case "RedBall":
                    try {
                        final Class<?> cl = Class.forName(name);
                        final Constructor<?> cns = cl.getConstructor(Position2D.class, Float.class, CharacterGraphicComponent.class, Integer.class);
                        this.levelCharacters.add((Character) cns.newInstance(randomPos, speed, new CharacterGraphicComponentImpl(Sprites.RedBallStanding, randomPos), standingTime));
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                            | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                    
                default:
                
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return Collections.unmodifiableList(levelCharacters);
    }
    
    
    public Map<String, EnemyInfo> getMapInfo() {
        return  Collections.unmodifiableMap(mapInfo);
    }
    
    
    /* */
    public final class EnemyInfo {
        
        private int currentQuantity;
        private final int totalQuantity;
        private final int spawningTime;
        private final int standingTime;
        
        /* package protected */
        EnemyInfo(final int quantity, final int spawningTime, final int standingTime) {
            this.currentQuantity = 0;
            this.totalQuantity = quantity;
            this.spawningTime = spawningTime;
            this.standingTime = standingTime;
        }
        
        public int getCurrentQuantity() {
            return this.currentQuantity;
        }
        
        public int getTotalQuantity() {
            return this.totalQuantity;
        }
        
        public int getSpawningTime() {
            return this.spawningTime;
        }
        
        public int getStandingTime() {
            return this.standingTime;
        }
        
        public void incCurrentQuantity() {
            this.currentQuantity++;
        }
        
        public void decCurrentQuantity() {
            this.currentQuantity--;
        }
    }

}
