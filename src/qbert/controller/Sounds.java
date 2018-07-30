package qbert.controller;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The class for the management of sound effects of the game.
 */
public final class Sounds {

    private Sounds() {

    }

    /**
     * @param url the name of the .wav file to be loaded to play the sound
     */
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    final Clip clip = AudioSystem.getClip();
                    final AudioInputStream inputStream = AudioSystem
                            .getAudioInputStream(new File("res/sounds/" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}
