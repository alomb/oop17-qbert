package qbert.model.spawner;

import java.util.List;
import java.util.Optional;

import qbert.model.characters.Character;
import qbert.model.characters.Player;
import qbert.model.characters.Snake;

/**
 * The interface for the characters spawning management.
 */
public interface Spawner {

    /**
     * @return the {@link Player} representing Qbert.
     */
    Player spawnQbert();

    /**
     * 
     */
    void respawnQbert();

    /**
     * This method manages the characters spawning progress during the game.
     * @param dt the time passed since the last game cycle
     */
    void update(float dt);

    /**
     * This method manages the death of a character.
     * @param character the dead {@link Character}
     */
    void death(Character character);

    /**
     * @return the list of the {@link Character} of the current level/round
     */
    List<Character> getGameCharacters();

    Optional<Snake> getCoily();

    void killCoily();

    /**
     * @param gc the list of {@link Character} to be updated
     */
    void updateGameCharacters(List<Character> gc);

}
