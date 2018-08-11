package qbert.model.characters;

import qbert.model.characters.states.CharacterState;
import qbert.model.characters.states.QbertStandingState;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.sounds.PlayerSC;
import qbert.model.utilities.Position2D;
import qbert.model.components.graphics.DownUpwardCharacterGC;
import qbert.model.components.graphics.PlayerGC;

/**
 * This class models the main character of the game controlled by the user.
 */
public class Qbert extends CharacterImpl implements Player {

    /**
     * Number of lives which Qbert has on the start of the game.
     */
    private static final int DEFAULT_LIVES_NUMBER = 3;

    private final PlayerGC graphics;
    private final PlayerSC sounds;
    private int lives;

    /**
     * @param startPos the first {@link Position2D} of the {@link Player} in the map
     * @param speed the {@link Player} movement speed
     * @param graphics the {@link Player}'s {@link PlayerGC}
     * @param sounds the {@link Player}'s {@link PlayerSC}
     */
    public Qbert(final Position2D startPos, final float speed, final PlayerGC graphics, final PlayerSC sounds) {
        super(startPos, speed, graphics);
        this.graphics = graphics;
        this.sounds = sounds;
        this.setCurrentState(this.getStandingState());
        this.lives = Qbert.DEFAULT_LIVES_NUMBER;
    }

    @Override
    public final CharacterState getStandingState() {
        return new QbertStandingState(this);
    }

    @Override
    public final void land(final MapComponent map, final PointComponent points) {
        points.score(map.incrementColor(this.getNextPosition()), this);
        this.sounds.setHopSound();
    }

    @Override
    public final PlayerGC getPlayerGraphicComponent() {
        return this.graphics;
    }

    @Override
    public final DownUpwardCharacterGC getDownUpwardGraphicComponent() {
        return this.graphics;
    }

    @Override
    public final void gainLife() {
        this.lives++;
    }

    @Override
    public final void looseLife() {
        this.lives--;
    }

    @Override
    public final int getLivesNumber() {
        return this.lives;
    }

    @Override
    public final PlayerSC getPlayerSoundComponent() {
        return this.sounds;
    }
}
