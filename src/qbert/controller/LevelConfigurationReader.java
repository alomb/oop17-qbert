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

import qbert.model.Level;

/**
 *
 */
public class LevelConfigurationReader {
    
    private final Map<String, EnemyInfo> mapInfo;
    private int colorsNumber;
    private boolean reversable;

    public LevelConfigurationReader() {
        this.mapInfo = new HashMap<>();
    }
    
    public void readLevelConfiguration(final Level l) throws JDOMException {
        try {
            final SAXBuilder builder = new SAXBuilder();
            final Document document = builder.build("res/levelconfig.xml");

            final Element root = document.getRootElement();
            final Element level = root.getChild("LEVEL" + l.getLevel());
            final Element round = level.getChild("ROUND" + l.getRound());
            this.colorsNumber = Integer.valueOf(round.getAttributeValue("colors"));
            this.reversable = Boolean.parseBoolean(round.getAttributeValue("reversable"));

            final List<Element> children = round.getChildren();
            final Iterator<Element> it = children.iterator();

            while (it.hasNext()) {
                final Element character = (Element) it.next();
                final String name = character.getName();
                final float speed = Float.valueOf(character.getAttributeValue("speed"));
                final int quantity = Integer.valueOf(character.getAttributeValue("quantity"));
                final int spawningTime = Integer.valueOf(character.getAttributeValue("spawningTime"));
                final int standingTime = Integer.valueOf(character.getAttributeValue("standingTime"));
                
                this.mapInfo.put(name, this.new EnemyInfo(speed, quantity, spawningTime, standingTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Map<String, EnemyInfo> getMapInfo() {
        return  Collections.unmodifiableMap(mapInfo);
    }
    
    public int getColorsNumber() {
        return this.colorsNumber;
    }
    
    public boolean isReversable() {
        return this.reversable;
    }
    
    /* */
    public final class EnemyInfo {
        
        final private float speed;
        private int currentQuantity;
        private final int totalQuantity;
        private final int spawningTime;
        private final int standingTime;
        
        private int elapsedTime;
        
        /* package protected */
        EnemyInfo(final float speed, final int quantity, final int spawningTime, final int standingTime) {
            this.speed = speed;
            this.currentQuantity = 0;
            this.totalQuantity = quantity;
            this.spawningTime = spawningTime;
            this.standingTime = standingTime;
            
            this.elapsedTime = 0;
        }
        
        public float getSpeed() {
            return this.speed;
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
        
        public int getElapsedTime() {
            return this.elapsedTime;
        }
        
        public void incCurrentQuantity() {
            this.currentQuantity++;
        }
        
        public void decCurrentQuantity() {
            this.currentQuantity--;
        }
        
        public void resetElapsedTime() {
            this.elapsedTime = 0;
        }
        
        public void incElapsedTime(final float dt) {
            this.elapsedTime += dt;
        }
    }

}
