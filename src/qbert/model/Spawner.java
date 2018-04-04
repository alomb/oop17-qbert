package qbert.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.jdom2.JDOMException;

import qbert.controller.LevelConfigurationReader;
import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponent;
import qbert.view.CharacterGraphicComponentImpl;
import qbert.view.QBertGraphicComponent;

public class Spawner {
    
    private final Level level;
    private Map<String, LevelConfigurationReader.EnemyInfo> mapInfo;
    final LevelConfigurationReader lcr;
    
    public Spawner(final Level level) {
        this.level = level;
        this.mapInfo = new HashMap<>();
        this.lcr = new LevelConfigurationReader();
        try {
            lcr.readLevelConfiguration(level);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        this.mapInfo = lcr.getMapInfo();
    }
    
    public void spawnQbert() {
        level.spawn(new Qbert(Dimensions.spawingQBert, 0.35f, new QBertGraphicComponent(Sprites.qbertFrontStanding, Dimensions.spawingQBert)));
    }
    
    public void update(final float dt) {
        for (final Map.Entry<String, LevelConfigurationReader.EnemyInfo> entry : mapInfo.entrySet()) {
            if (entry.getValue().getSpawningTime() <= entry.getValue().getElapsedTime()) {
                entry.getValue().resetElapsedTime();
                if (entry.getValue().getCurrentQuantity() < entry.getValue().getTotalQuantity()) {
                    final Position2D randomPos = new Random().nextInt(2) == 0 ? Dimensions.spawingPointLeft : Dimensions.spawingPointRight;
                    final Position2D logicalPos = randomPos == Dimensions.spawingPointLeft ? new Position2D(5, 5) : new Position2D(7, 5);
                    try {
                        final Class<?> cl = Class.forName("qbert.model." + entry.getKey());
                        final Constructor<?> cns = cl.getConstructor(Position2D.class, Float.class, CharacterGraphicComponent.class, Integer.class);
                        final Character character = (Character) cns.newInstance(logicalPos, entry.getValue().getSpeed(), new CharacterGraphicComponentImpl(Sprites.RedBallStanding, randomPos), entry.getValue().getStandingTime());
                        level.spawn(character); 
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                            | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                entry.getValue().incCurrentQuantity();
            } else {
                entry.getValue().incElapsedTime(dt);
            }
        }
    }
    
    public int getColorsNumber() {
        return this.lcr.getColorsNumber();
    }
    
    public boolean isReverable() {
        return this.lcr.isReversable();
    }
}
