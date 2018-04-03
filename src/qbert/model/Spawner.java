package qbert.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.JDOMException;

import qbert.controller.LevelConfigurationReader;
import qbert.view.QBertGraphicComponent;

public class Spawner {
    
    private final Level level;
    private List<Character> charactersToSpawn;
    private Map<String, LevelConfigurationReader.EnemyInfo> mapInfo = new HashMap<>();
    
    private int elapsedTime; /* */
    
    public Spawner(final Level level) {
        this.level = level;
        final LevelConfigurationReader lcr = new LevelConfigurationReader();
        try {
            this.charactersToSpawn = lcr.readLevelConfiguration(level);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        this.mapInfo = lcr.getMapInfo();
        this.spawnQbert();
    }
    
    public void spawnQbert() {
        level.spawn(new Qbert(Dimensions.spawingQBert, 0.35f, new QBertGraphicComponent(Sprites.qbertFrontStanding, Dimensions.spawingQBert)));
    }
    
    public void update(final float dt) {
        for (final Character character : this.charactersToSpawn) {
            Class<?> cl = null;
            try {
                cl = Class.forName(character.getClass().getName());
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (this.mapInfo.get(cl.getName()).getSpawningTime() <= this.elapsedTime) {
                this.elapsedTime = 0;
                if (this.mapInfo.get(cl.getName()).getCurrentQuantity() < this.mapInfo.get(cl.getName()).getTotalQuantity()) {
                    level.spawn(character);
                }
                this.mapInfo.get(cl.getName()).incCurrentQuantity();
            } else {
                this.elapsedTime += dt;
            }
        }
    }
}
