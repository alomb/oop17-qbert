package qbert.controller;

import java.io.File;

/**
 * The enumeration that manages the different audio files necessary for sound effects.
 */
public enum SoundEffectFile {

    /**
     * 
     */
    INSERT_A_COIN("InsertACoin.wav"),

    /**
     * 
     */
    GAME_START_MUSIC("GameStartMusic.wav"),

    /**
     * 
     */
    WIN_A_ROUND_MUSIC("WinningARound.wav"),

    /**
     * 
     */
    QBERT_HOPS("QbertHops.wav"),

    /**
     * 
     */
    QBERT_FALLS("QbertGoesOverTheEdge.wav"),

    /**
     * 
     */
    QBERT_RIDES_A_DISK("RidingADisk.wav"),

    /**
     * 
     */
    QBERT_DIES("QbertDies.wav"),

    /**
     * 
     */
    COILY_HOPS("CoilyHops.wav"),

    /**
     * 
     */
    COILY_FALLS("CoilyGoesOverTheEdge.wav"),

    /**
     * 
     */
    COILY_KILLS("CoilyBites.wav");

    private final String fileName;

    /**
     * @param fileName the name of the audio file
     */
    SoundEffectFile(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the audio {@link File} corresponding to the specific sound effect 
     */
    public File getFile() {
        return new File(this.fileName);
    }

}
