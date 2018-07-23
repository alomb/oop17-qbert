package qbert.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.JDOMException;

import qbert.controller.LevelConfigurationReader;
import qbert.model.characters.Character;
import qbert.model.characters.Player;
import qbert.model.states.SpawnState;
import qbert.model.utilities.Position2D;

/**
 * The class for the characters spawning management.
 */
public final class Spawner {

    private final Player qbert;
    private List<Character> gameCharacters;
    private final EnemyFactory ef = new EnemyFactoryImpl();
    private Map<String, EnemyInfo> mapInfo;
    private final LevelConfigurationReader lcr;

    /**
     * @param level the level that must be loaded
     * @param round the round that must be loaded
     */
    public Spawner(final int level, final int round) {
        this.qbert = ef.createQbert();
        this.gameCharacters = new ArrayList<>();
        this.mapInfo = new HashMap<>();
        this.lcr = new LevelConfigurationReader();
        try {
            lcr.readLevelConfiguration(level, round);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        this.mapInfo = lcr.getMapInfo();
    }

    /**
     * @return the {@link Player} representing Qbert.
     */
    public Player spawnQbert() {
        return this.qbert;
    }

    /**
     * 
     */
    public void respawnQbert() {
        this.qbert.setNextPosition(new Position2D(this.qbert.getCurrentPosition()));
        this.qbert.setCurrentState(new SpawnState(this.qbert));
    }

    /**
     * This method manages the characters spawning progress during the game.
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
                        character = ef.createCoily(entry.getValue().getSpeed(), entry.getValue().getStandingTime(), this.qbert);
                        character.setCurrentPosition(new Position2D(-1, -1)); ////////////
                        this.gameCharacters.add(character);
                        break;
                    case "RedBall":
                        character = ef.createRedBall(entry.getValue().getSpeed(), entry.getValue().getStandingTime());
                        character.setCurrentPosition(new Position2D(-1, -1)); ////////////
                        this.gameCharacters.add(character);
                        break;
                    case "GreenBall":
                        character = ef.createGreenBall(entry.getValue().getSpeed(), entry.getValue().getStandingTime());
                        character.setCurrentPosition(new Position2D(-1, -1)); ////////////
                        this.gameCharacters.add(character);
                        break;
                    case "SamAndSlick":
                        character = ef.createSamAndSlick(entry.getValue().getSpeed(), entry.getValue().getStandingTime());
                        character.setCurrentPosition(new Position2D(-1, -1)); ////////////
                        this.gameCharacters.add(character);
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
     * This method manages the death of a character.
     * @param character the dead {@link Character}
     */
    public void death(final Character character) {
        final String name = character.getClass().getSimpleName();
        /* DEBUG */
        System.out.println("DEC");

        if (this.mapInfo.get(name).getCurrentQuantity() > 0) {
            this.mapInfo.get(name).decCurrentQuantity();
        }
    }

    /**
     * @return the list of the {@link Character} of the current level/round
     */
    public List<Character> getGameCharacters() {
        return Collections.unmodifiableList(this.gameCharacters);
    }

    /**
     * @param gc the list of {@link Character} to be updated
     */
    public void updateGameCharacters(final List<Character> gc) {
        this.gameCharacters = gc;
    }

    /**
     * @return the {@link LevelSettings} of the current level/round
     */
    public LevelSettings getLevelSettings() {
        return this.lcr.getLevelSettings();
    }
}
