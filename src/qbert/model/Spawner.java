package qbert.model;

import java.util.HashMap;
import java.util.Map;

import org.jdom2.JDOMException;

import qbert.controller.LevelConfigurationReader;
import qbert.model.characters.Character;
import qbert.model.characters.Qbert;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Sprites;
import qbert.view.DownwardUpwardCGC;

/**
 * The class for the characters spawning management.
 */
public final class Spawner {

    private final Level level;
    private final EnemyFactory ef = new EnemyFactoryImpl();
    private Map<String, EnemyInfo> mapInfo;
    private final LevelConfigurationReader lcr;

    /**
     * @param level the {@link Level} reference
     */
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

    /**
     * 
     */
    public void spawnQbert() {
        /*TODO: Change with correct sprites*/
        level.spawn(new Qbert(Dimensions.spawningQBert, 0.35f, new DownwardUpwardCGC(Sprites.qbertFrontStanding, Sprites.qbertFrontMoving, Sprites.qbertBackStanding, Sprites.qbertBackMoving, Dimensions.spawningQBert)));
    }

    /**
     * This function manages the characters spawning progress during the game.
     * @param dt the time passed since the last game cycle
     */
    public void update(final float dt) {
        for (final Map.Entry<String, EnemyInfo> entry : mapInfo.entrySet()) {
            if (entry.getValue().getSpawningTime() <= entry.getValue().getElapsedTime()) {
                entry.getValue().resetElapsedTime();
                if (entry.getValue().getCurrentQuantity() < entry.getValue().getTotalQuantity()) {
                    final Character character;
                    switch (entry.getKey()) {
                    case "Coily":
                        character = ef.createCoily(entry.getValue().getSpeed(), entry.getValue().getStandingTime(), level.getQBert());
                        level.spawn(character);
                        break;
                    case "RedBall":
                        character = ef.createRedBall(entry.getValue().getSpeed(), entry.getValue().getStandingTime());
                        level.spawn(character);
                        break;
                    default:
                    }
                    entry.getValue().incCurrentQuantity();
                }
            } else {
                entry.getValue().incElapsedTime(dt);
            }
        }
    }

    /**
     * This function manages the death of a character.
     * @param character the dead {@link Character}
     */
    public void death(final Character character) {
        final String name = character.getClass().getSimpleName();
        this.mapInfo.get(name).decCurrentQuantity();
    }

    /**
     * @return the number of colors to be set for each tile for the current level/round
     */
    public int getColorsNumber() {
        return this.lcr.getColorsNumber();
    }

    /**
     * @return true if the tile is reversible, false otherwise
     */
    public boolean isReversible() {
        return this.lcr.isReversible();
    }
}
