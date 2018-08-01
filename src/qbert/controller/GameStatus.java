package qbert.controller;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represent labels used to identify in which status the application is.
 */
public enum GameStatus {

    INTRODUCTION,

    GAMEPLAY;

    public static Set<GameStatus> getAll() {
        return Arrays.asList(GameStatus.values()).stream().collect(Collectors.toSet());
    }

}
