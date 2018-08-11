package qbert.model.components.sounds;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

import javax.sound.sampled.Clip;

import qbert.controller.Controller;
import qbert.controller.SoundEffectFile;

/**
 * The implementation of {@link PlayerSC}.
 */
public class QbertSC implements PlayerSC {

    private final Controller controller;

    private final Queue<Clip> clipToPlay = new LinkedTransferQueue<>();

    /**
     * @param controller the game {@link Controller}
     */
    public QbertSC(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public void setCoinSound() {

    }

    @Override
    public void setGameStartSound() {

    }

    @Override
    public final void setHopSound() {
        this.clipToPlay.add(this.controller.uploadClip(SoundEffectFile.QBERT_HOPS));
        this.notifyPlaySound();
    }

    @Override
    public final void setFallSound() {
        this.clipToPlay.add(this.controller.uploadClip(SoundEffectFile.QBERT_FALLS));
        this.notifyPlaySound();
    }

    @Override
    public void setKillSound() {

    }

    @Override
    public final void setOnDiskSound() {
        this.clipToPlay.add(this.controller.uploadClip(SoundEffectFile.QBERT_RIDES_A_DISK));
        this.notifyPlaySound();
    }

    @Override
    public final void setDeathSound() {
        this.clipToPlay.add(this.controller.uploadClip(SoundEffectFile.QBERT_DIES));
        this.notifyPlaySound();
    }

    @Override
    public final void notifyPlaySound() {
        this.controller.emptyClipQueue(this.clipToPlay);
    }

}
