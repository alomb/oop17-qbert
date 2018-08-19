package qbert.model.spawner;

import qbert.controller.Controller;
import qbert.controller.Sprites;
import qbert.model.characters.Character;
import qbert.model.characters.Snake;
import qbert.model.characters.Ugg;
import qbert.model.characters.Wrongway;
import qbert.model.components.sounds.CharacterSC;
import qbert.model.components.sounds.DownUpwardCharacterSC;
import qbert.model.components.sounds.PlayerSC;
import qbert.model.components.sounds.QbertSC;
import qbert.model.sprites.OneSideCharacterSprites;
import qbert.model.characters.Coily;
import qbert.model.characters.GreenBall;
import qbert.model.characters.Player;
import qbert.model.characters.Qbert;
import qbert.model.characters.RedBall;
import qbert.model.characters.SamAndSlick;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.components.graphics.CharacterGC;
import qbert.model.components.graphics.CoilyGC;
import qbert.model.components.graphics.CoilyGCImpl;
import qbert.model.components.graphics.DownwardCharacterGC;
import qbert.model.components.graphics.LeftwardCharacterGC;
import qbert.model.components.graphics.PlayerGC;
import qbert.model.components.graphics.PlayerGCImpl;
import qbert.model.components.graphics.RightwardCharacterGC;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The implementation of {@link EnemyFactory} interface.
 */
public class EnemyFactoryImpl implements EnemyFactory {

    private static final String USER_MESSAGE = "Application aborted. Please look at log file for more information.";

    private final int spawningPointLeftX;
    private final int spawningPointRightX;

    private final Position2D spawningLogPointLeft;
    private final Position2D spawningLogPointRight;

    private final Position2D spawningQBert;
    private final Position2D spawningLogQBert;

    private final Controller controller;

    private Sprites sprites;

    /**
     * Initialize some variables.
     * @param controller the game {@link Controller}
     */
    public EnemyFactoryImpl(final Controller controller) {

        try {
            this.sprites = Sprites.getInstance();
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            controller.forceQuit(USER_MESSAGE);
        }

        this.spawningPointLeftX = Math.round(new Float(Dimensions.getWindowWidth() / 2f) - Dimensions.getCubeWidth());
        this.spawningPointRightX = Math.round(new Float(Dimensions.getWindowWidth() / 2f));

        this.spawningLogPointLeft = new Position2D(Dimensions.MAP_SPAWNING_POINT_LEFT_X, Dimensions.MAP_SPAWNING_POINT_LEFT_Y);
        this.spawningLogPointRight = new Position2D(Dimensions.MAP_SPAWNING_POINT_RIGHT_X, Dimensions.MAP_SPAWNING_POINT_RIGHT_Y);

        final int qbertFrontSpriteHeight = this.sprites.getQbertFrontSprites().getStandSprite().getHeight();
        final int qbertFrontSpriteWidth = this.sprites.getQbertFrontSprites().getStandSprite().getWidth();

        this.spawningQBert = new Position2D(Math.round(new Float(Dimensions.getWindowWidth()) / 2f) - Math.round(new Float(qbertFrontSpriteWidth) / 2f), 
                Dimensions.getBackgroundPos().getY() - qbertFrontSpriteHeight);
        this.spawningLogQBert = new Position2D(Dimensions.MAP_SPAWNING_QBERT_X, Dimensions.MAP_SPAWNING_QBERT_Y);

        this.controller = controller;
    }

    @Override
    public final Player createQbert(final float speed, final int qbertLives) {
        final PlayerGC graphics = new PlayerGCImpl(this.sprites.getQbertFrontSprites(), this.sprites.getQbertBackSprites(), 
                sprites.getQbertSpecialSprites(), new Position2D(spawningQBert));
        final PlayerSC sounds = new QbertSC(this.controller);

        return new Qbert(this.spawningLogQBert, speed, graphics, sounds, qbertLives);
    }

    @Override
    public final Snake createCoily(final float speed, final int standingTime, final Player qbert) {
        final Position2D randomPos = this.getRandomPos(this.sprites.getPurpleBallSprites().getMoveSprite().getHeight());
        final Position2D logicalPos = this.getLogicalPos(randomPos);

        final CoilyGC graphics = new CoilyGCImpl(this.sprites.getPurpleBallSprites(), this.sprites.getCoilyFrontSprites(), 
                this.sprites.getCoilyBackSprites(), randomPos);
        final CharacterSC sounds = new DownUpwardCharacterSC(this.controller);

        return new Coily(logicalPos, speed, graphics, sounds, standingTime, qbert);
    }

    @Override
    public final Character createRedBall(final float speed, final int standingTime) {
        final OneSideCharacterSprites sprites = this.sprites.getRedBallSprites();
        final Position2D randomPos = this.getRandomPos(sprites.getMoveSprite().getHeight());
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final CharacterGC graphics = new DownwardCharacterGC(sprites, randomPos);

        return new RedBall(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createGreenBall(final float speed, final int standingTime) {
        final OneSideCharacterSprites sprites = this.sprites.getGreenBallSprites();
        final Position2D randomPos = this.getRandomPos(sprites.getMoveSprite().getHeight());
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final CharacterGC graphics = new DownwardCharacterGC(this.sprites.getGreenBallSprites(), randomPos);

        return new GreenBall(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createSamAndSlick(final float speed, final int standingTime) {
        final OneSideCharacterSprites slickS = this.sprites.getSlickSprites();
        final OneSideCharacterSprites samS = this.sprites.getSamSprites();
        final Position2D randomPos = this.getRandomPos(slickS.getMoveSprite().getHeight() > samS.getMoveSprite().getHeight()
                ? slickS.getMoveSprite().getHeight() : samS.getMoveSprite().getHeight());
        final Position2D logicalPos = this.getLogicalPos(randomPos);

        final CharacterGC graphics = randomPos == this.spawningLogPointLeft
                ? new DownwardCharacterGC(slickS, randomPos)
                : new DownwardCharacterGC(samS, randomPos);

        return new SamAndSlick(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createWrongway(final float speed, final int standingTime) {
        final Position2D logicalPos = new Position2D(Dimensions.MAP_BOTTOM_EDGE - 1, Dimensions.MAP_BOTTOM_EDGE - 1);
        final RightwardCharacterGC graphics = new RightwardCharacterGC(this.sprites.getWrongwaySprites(), 
                new Position2D(-this.sprites.getWrongwaySprites().getMoveSprite().getWidth(), Dimensions.getBackgroundPos().getY() + Dimensions.getBackgroundHeight() - Dimensions.getCubeHeight()));

        return new Wrongway(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createUgg(final float speed, final int standingTime) {
        final Position2D logicalPos = new Position2D(Dimensions.MAP_COLUMNS - 1, Dimensions.MAP_BOTTOM_EDGE - 1);
        final LeftwardCharacterGC graphics = new LeftwardCharacterGC(this.sprites.getUggSprites(), 
                new Position2D(Dimensions.getWindowWidth() + this.sprites.getWrongwaySprites().getMoveSprite().getWidth(), Dimensions.getBackgroundPos().getY() + Dimensions.getBackgroundHeight() - Dimensions.getCubeHeight()));

        return new Ugg(logicalPos, speed, graphics, standingTime);
    }

    private Position2D getRandomPos(final int spriteHeight) {
        return new Random().nextInt(2) == 0 ? new Position2D(this.spawningPointLeftX, -spriteHeight)
            : new Position2D(this.spawningPointRightX, -spriteHeight);
    }

    private Position2D getLogicalPos(final Position2D randPos) {
        return randPos.getX() == this.spawningPointLeftX ? this.spawningLogPointLeft : this.spawningLogPointRight;
    }

}
