package qbert.controller;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

public class LevelConfigurationReader {
    
    private LevelConfigurationReader() {
        
    }
    
    public static List<Character> readLevelConfiguration(final Level l) throws JDOMException {
        List<Character> levelCharacters = new ArrayList<>();
        //Map<String, >

        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build("/levelconfig.xml");

            Element root = document.getRootElement();
            Element level = root.getChild("LEVEL" + l.getLevel());
            Element round = level.getChild("ROUND" + l.getRound());
            int colors = Integer.valueOf(round.getAttributeValue("colors"));
            int reversable = Integer.valueOf(round.getAttributeValue("reversable"));
            
            List<Element> children = round.getChildren();
            Iterator<Element> it = children.iterator();
            
            while (it.hasNext()) {
                Element character = (Element) it.next();
                String name = character.getName();
                float speed = Float.valueOf(character.getAttributeValue("speed"));
                String sprite = character.getAttributeValue("graphics") + "Moving"; 
                
                int quantity = Integer.valueOf(character.getAttributeValue("quantity"));
                int spawningTime = Integer.valueOf(character.getAttributeValue("spawningTime"));
                int standingTime = Integer.valueOf(character.getAttributeValue("standingTime"));
                
                if (name.equals("RedBall")) {
                    Position2D randomPos = new Random().nextInt(2) == 0 ? Dimensions.spawingPointLeft : Dimensions.spawingPointRight;
                    Class<?> cl;
                    try {
                        cl = Class.forName(name);
                        final Constructor<?> cns = cl.getConstructor(Position2D.class, CharacterGraphicComponent.class, Integer.class);
                        levelCharacters.add((Character) cns.newInstance(randomPos, new CharacterGraphicComponentImpl(Sprites.RedBallStanding, randomPos), standingTime));
                    
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
               }
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return Collections.unmodifiableList(levelCharacters);
    }

}
