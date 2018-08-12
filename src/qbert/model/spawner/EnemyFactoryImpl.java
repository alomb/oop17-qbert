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
import qbert.model.components.graphics.DownwardCharacterGCImpl;
import qbert.model.components.graphics.LeftwardCharacterGC;
import qbert.model.components.graphics.PlayerGC;
import qbert.model.components.graphics.PlayerGCImpl;
import qbert.model.components.graphics.RightwardCharacterGC;

import java.util.Random;
/**
 * The implementation of {@link EnemyFactory} interface.
 */
public class EnemyFactoryImpl implements EnemyFactory {

    @Override
    public final Player createQbert(final float speed, final Controller controller, final int qbertLives) {
        final Sprites sprites = Sprites.getInstance();
        final PlayerGC graphics = new PlayerGCImpl(sprites.getQbertFrontSprites(), sprites.getQbertBackSprites(), 
                sprites.getQbertSpecialSprites(), new Position2D(Dimensions.getSpawningQBert()));
        final PlayerSC sounds = new QbertSC(controller);

        return new Qbert(Dimensions.getSpawningLogQBert(), speed, graphics, sounds, qbertLives);
    }

    @Override
    public final Snake createCoily(final float speed, final int standingTime, final Player qbert, final Controller controller) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final Sprites sprites = Sprites.getInstance();

        final CoilyGC graphics = new CoilyGCImpl(sprites.getPurpleBallSprites(), sprites.getCoilyFrontSprites(), sprites.getCoilyBackSprites(), randomPos);
        final CharacterSC sounds = new DownUpwardCharacterSC(controller);

        return new Coily(logicalPos, speed, graphics, sounds, standingTime, qbert);
    }

    @Override
    public final Character createRedBall(final float speed, final int standingTime, final Controller controller) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final CharacterGC graphics = new DownwardCharacterGCImpl(Sprites.getInstance().getRedBallSprites(), randomPos);

        return new RedBall(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createGreenBall(final float speed, final int standingTime, final Controller controller) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final CharacterGC graphics = new DownwardCharacterGCImpl(Sprites.getInstance().getGreenBallSprites(), randomPos);

        return new GreenBall(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createSamAndSlick(final float speed, final int standingTime, final Controller controller) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final OneSideCharacterSprites slickS = Sprites.getInstance().getSlickSprites();
        final OneSideCharacterSprites samS = Sprites.getInstance().getSamSprites();

        final CharacterGC graphics = randomPos == Dimensions.getSpawningPointLeft()
                ? new DownwardCharacterGCImpl(slickS, randomPos)
                : new DownwardCharacterGCImpl(samS, randomPos);

        return new SamAndSlick(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createWrongway(final float speed, final int standingTime, final Controller controller) {
        final Position2D logicalPos = new Position2D(0, 0);
        final RightwardCharacterGC graphics = new RightwardCharacterGC(Sprites.getInstance().getWrongwaySprites(), new Position2D(-Dimensions.getSideSpawningHeight(), Dimensions.getBackgroundY() + Dimensions.getBackgroundHeight() - Dimensions.getCubeHeight()));

        return new Wrongway(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createUgg(final float speed, final int standingTime, final Controller controller) {
        final Position2D logicalPos = new Position2D(26, 0);
        final LeftwardCharacterGC graphics = new LeftwardCharacterGC(Sprites.getInstance().getUggSprites(), new Position2D(Dimensions.getWindowWidth() + Dimensions.getSideSpawningHeight(), Dimensions.getBackgroundY() + Dimensions.getBackgroundHeight() - Dimensions.getCubeHeight()));

        return new Ugg(logicalPos, speed, graphics, standingTime);
    }

    private Position2D getRandomPos() {
        return new Random().nextInt(2) == 0 ? Dimensions.getSpawningPointLeft() : Dimensions.getSpawningPointRight();
    }

    private Position2D getLogicalPos(final Position2D randPos) {
        return randPos == Dimensions.getSpawningPointLeft() ? Dimensions.getSpawningLogPointLeft() : Dimensions.getSpawningLogPointRight();
    }

}
