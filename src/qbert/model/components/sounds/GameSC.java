package qbert.model.components.sounds;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

import javax.sound.sampled.Clip;

import qbert.controller.Controller;
import qbert.controller.SoundEffectFile;

/**
 * The implementation of {@link SoundComponent}.
 */
public class GameSC implements SoundComponent {

    private final Controller controller;

    private final Queue<Clip> clipToPlay = new LinkedTransferQueue<>();

    /**
     * @param controller the game {@link Controller}
     */
    public GameSC(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public final void setCoinSound() {
        this.clipToPlay.add(this.controller.uploadClip(SoundEffectFile.INSERT_A_COIN));
        this.notifyPlaySound();
    }

    @Override
    public final void setGameStartSound() {
        this.clipToPlay.add(this.controller.uploadClip(SoundEffectFile.GAME_START_MUSIC));
        this.notifyPlaySound();
    }

    @Override
    public final void notifyPlaySound() {
        this.controller.emptyClipQueue(this.clipToPlay);
    }
}
