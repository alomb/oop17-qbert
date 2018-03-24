package qbert.model;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Timer;

import javax.imageio.ImageIO;

import org.jdom2.JDOMException;

import qbert.controller.LevelConfigurationReader;
import qbert.model.utilities.Position2D;
import qbert.view.QBertGraphicComponent;

import java.util.List;

public class Spawner {
    private final Level level;
    private List<Character> charactersToSpawn;
    
    public Spawner(final Level level) {
        this.level = level;
        try {
            this.charactersToSpawn = LevelConfigurationReader.readLevelConfiguration(level);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        this.spawning();
    }
    
    public void spawning() {
        final URL spriteUrl = this.getClass().getResource("/QbertFrontStanding.png");
        try {
            level.spawn(new Qbert(Dimensions.spawingQBert, 0.35f, new QBertGraphicComponent(ImageIO.read(spriteUrl), Dimensions.spawingQBert)));
            //level.spawn(new RedBall(Dimensions.spawingPointRight, 0.34f, new QBertGraphicComponent(ImageIO.read(spriteUrl), Dimensions.spawingPointRight), 1000));
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        for (Character character : charactersToSpawn) {
            level.spawn(character);
        }
    }

}
