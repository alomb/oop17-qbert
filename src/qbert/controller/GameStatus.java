package qbert.controller;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represent labels used to identify in which status the application is.
 */
public enum GameStatus {

    /**
     * Show the user a brief animated introduction to the game rules.
     */
    INTRODUCTION,

    /**
     * Let the user to decide which application functionality use.
     */
    MENU,

    /**
     * The user is playing a match.
     */
    GAMEPLAY,

    /**
     * Show the user a list of matches, including players name and score.
     */
    RANKING,

    /**
     * Let the user conclude a match.
     */
    GAMEOVER,
    /**
     * Before start game choice mode and difficult.
     */
    MODE;

    /**
     * @return all the {@link GameStatus}
     */
    public static Set<GameStatus> getAll() {
        return Arrays.asList(GameStatus.values()).stream().collect(Collectors.toSet());
    }

}
