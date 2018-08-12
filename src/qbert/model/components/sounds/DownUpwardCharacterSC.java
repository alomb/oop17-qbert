package qbert.model.components.sounds;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

import javax.sound.sampled.Clip;

import qbert.controller.Controller;
import qbert.controller.SoundEffectFile;

/**
 * An implementation of {@link CharacterSC}.
 */
public class DownUpwardCharacterSC implements CharacterSC {

    private final Controller controller;

    private final Queue<Clip> clipToPlay = new LinkedTransferQueue<>();

    /**
     * @param controller the game {@link Controller}
     */
    public DownUpwardCharacterSC(final Controller controller) {
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
        this.clipToPlay.add(this.controller.uploadClip(SoundEffectFile.COILY_HOPS));
        this.notifyPlaySound();
    }

    @Override
    public final void setFallSound() {
        this.clipToPlay.add(this.controller.uploadClip(SoundEffectFile.COILY_FALLS));
        this.notifyPlaySound();
    }

    @Override
    public final void setKillSound() {
        this.clipToPlay.add(this.controller.uploadClip(SoundEffectFile.COILY_KILLS));
        this.notifyPlaySound();
    }

    @Override
    public final void notifyPlaySound() {
        this.controller.emptyClipQueue(this.clipToPlay);
    }
}
