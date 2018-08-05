package qbert.model.spawner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import qbert.model.characters.Character;
import qbert.model.characters.Player;
import qbert.model.states.SpawnState;
import qbert.model.utilities.Position2D;

/**
 * The implementation of {@link Spawner}.
 */
public final class SpawnerImpl implements Spawner {

    private final Player qbert;
    private List<Character> gameCharacters;
    private final EnemyFactory ef = new EnemyFactoryImpl();
    private final Map<String, EnemyInfoImpl> mapInfo;

    /**
     * @param mapInfo the map of the characters
     * @param qBertSpeed the player speed
     */
    public SpawnerImpl(final Map<String, EnemyInfoImpl> mapInfo, final float qBertSpeed) {
        this.gameCharacters = new ArrayList<>();
        this.qbert = ef.createQbert(qBertSpeed);
        this.mapInfo = mapInfo;
    }

    @Override
    public Player spawnQbert() {
        return this.qbert;
    }

    @Override
    public void respawnQbert() {
        this.qbert.setNextPosition(new Position2D(this.qbert.getCurrentPosition()));
        this.qbert.setCurrentState(new SpawnState(this.qbert));
    }

    @Override
    public void update(final float dt) {
        for (final Map.Entry<String, EnemyInfoImpl> entry : mapInfo.entrySet()) {
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

    @Override
    public void death(final Character character) {
        final String name = character.getClass().getSimpleName();

        if (this.mapInfo.get(name).getCurrentQuantity() > 0) {
            this.mapInfo.get(name).decCurrentQuantity();
        }
    }

    @Override
    public List<Character> getGameCharacters() {
        return Collections.unmodifiableList(this.gameCharacters);
    }

    @Override
    public void updateGameCharacters(final List<Character> gc) {
        this.gameCharacters = gc;
    }
}
