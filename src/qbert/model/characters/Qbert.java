package qbert.model.characters;

import qbert.model.characters.states.CharacterState;
import qbert.model.characters.states.QbertStandingState;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;
import qbert.model.components.sounds.CharacterSC;
import qbert.model.components.sounds.PlayerSC;
import qbert.model.utilities.Position2D;
import qbert.model.components.graphics.DownUpwardCharacterGC;
import qbert.model.components.graphics.PlayerGC;

/**
 * This class models the main character of the game controlled by the player.
 */
public class Qbert extends CharacterImpl implements Player {

    /**
     * Number of lives which Qbert has on the start of the game.
     */
    public static final int DEFAULT_LIVES_NUMBER = 3;

    private final PlayerGC graphics;
    private final PlayerSC sounds;
    private int lives;
    private final Position2D spawningPos;

    /**
     * @param startPos the first {@link Position2D} of the {@link Player} in the map
     * @param speed the {@link Player} movement speed
     * @param graphics the {@link Player}'s {@link PlayerGC}
     * @param sounds the {@link Player}'s {@link PlayerSC}
     */
    public Qbert(final Position2D startPos, final float speed, final PlayerGC graphics, final PlayerSC sounds) {
        this(startPos, speed, graphics, sounds, 0);
    }

    /**
     * @param startPos the first {@link Position2D} of the {@link Player} in the map
     * @param speed the {@link Player} movement speed
     * @param graphics the {@link Player}'s {@link PlayerGC}
     * @param sounds the {@link Player}'s {@link PlayerSC}
     * @param lives the number of lives that the {@link Player} owns
     */
    public Qbert(final Position2D startPos, final float speed, final PlayerGC graphics, final PlayerSC sounds, final int lives) {
        super(startPos, speed, graphics);
        this.graphics = graphics;
        this.sounds = sounds;
        this.setCurrentState(this.getStandingState());
        this.lives = lives;
        this.spawningPos = new Position2D(startPos);
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

    @Override
    public final CharacterSC getCharcaterSoundComponent() {
        return this.sounds;
    }

    @Override
    public final Position2D getSpawningPosition() {
        return this.spawningPos;
    }

    @Override
    protected final void collide(final Player qbert, final PointComponent points, final TimerComponent timer) {
        throw new UnsupportedOperationException();
    }
}
