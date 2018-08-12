package qbert.model.spawner;

import qbert.controller.Controller;
import qbert.model.characters.Character;
import qbert.model.characters.Snake;
import qbert.model.characters.Ugg;
import qbert.model.characters.Wrongway;
import qbert.model.components.sounds.CharacterSC;
import qbert.model.components.sounds.DownUpwardCharacterSC;
import qbert.model.components.sounds.PlayerSC;
import qbert.model.components.sounds.QbertSC;
import qbert.model.sprites.CompleteCharacterSprites;
import qbert.model.sprites.FrontBackCharacterSprites;
import qbert.model.sprites.FrontCharacterSprites;
import qbert.model.sprites.FrontCharacterSpritesImpl;
import qbert.model.characters.Coily;
import qbert.model.characters.GreenBall;
import qbert.model.characters.Player;
import qbert.model.characters.Qbert;
import qbert.model.characters.RedBall;
import qbert.model.characters.SamAndSlick;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
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
    public final Player createQbert(final float speed, final Controller controller) {
        final CompleteCharacterSprites s = Sprites.getInstance().getQbertSprites();
        final PlayerGC graphics = new PlayerGCImpl(s.getFrontStandSprite(), s.getFrontMoveSprite(), s.getBackStandSprite(), s.getBackMoveSprite(), 
                s.getDeathSprite(), s.getOnDiskSprite(), new Position2D(Dimensions.getSpawningQBert()));
        final PlayerSC sounds = new QbertSC(controller);

        return new Qbert(Dimensions.getSpawningLogQBert(), speed, graphics, sounds);
    }

    @Override
    public final Snake createCoily(final float speed, final int standingTime, final Player qbert, final Controller controller) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final FrontBackCharacterSprites adultS = Sprites.getInstance().getCoilySprites();
        final FrontCharacterSprites ballS = Sprites.getInstance().getPurpleBallSprites();

        final CoilyGC graphics = new CoilyGCImpl(ballS.getFrontStandSprite(), adultS.getFrontStandSprite(), 
                adultS.getFrontMoveSprite(), ballS.getFrontMoveSprite(), adultS.getBackStandSprite(), adultS.getBackMoveSprite(), randomPos);
        final CharacterSC sounds = new DownUpwardCharacterSC(controller);

        return new Coily(logicalPos, speed, graphics, sounds, standingTime, qbert);
    }

    @Override
    public final Character createRedBall(final float speed, final int standingTime, final Controller controller) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final CharacterGC graphics = new DownwardCharacterGCImpl(Sprites.getInstance().getRedBallSprites().getFrontStandSprite(), 
                Sprites.getInstance().getRedBallSprites().getFrontMoveSprite(), randomPos);

        return new RedBall(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createGreenBall(final float speed, final int standingTime, final Controller controller) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final CharacterGC graphics = new DownwardCharacterGCImpl(Sprites.getInstance().getGreenBallSprites().getFrontStandSprite(),
                Sprites.getInstance().getGreenBallSprites().getFrontMoveSprite(), randomPos);

        return new GreenBall(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createSamAndSlick(final float speed, final int standingTime, final Controller controller) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final FrontCharacterSpritesImpl slickS = Sprites.getInstance().getSlickSprites();
        final FrontCharacterSpritesImpl samS = Sprites.getInstance().getSamSprites();

        final CharacterGC graphics = randomPos == Dimensions.getSpawningPointLeft()
                ? new DownwardCharacterGCImpl(slickS.getFrontStandSprite(), slickS.getFrontMoveSprite(), randomPos)
                : new DownwardCharacterGCImpl(samS.getFrontStandSprite(), samS.getFrontMoveSprite(), randomPos);

        return new SamAndSlick(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createWrongway(final float speed, final int standingTime, final Controller controller) {
        final Position2D logicalPos = new Position2D(0, 0);
        final RightwardCharacterGC graphics = new RightwardCharacterGC(Sprites.getInstance().getWrongwaySprites().getFrontStandSprite(), 
                Sprites.getInstance().getWrongwaySprites().getFrontMoveSprite(), new Position2D(-Dimensions.getSideSpawningHeight(), Dimensions.getBackgroundY() + Dimensions.getBackgroundHeight() - Dimensions.getCubeHeight()));

        return new Wrongway(logicalPos, speed, graphics, standingTime);
    }

    @Override
    public final Character createUgg(final float speed, final int standingTime, final Controller controller) {
        final Position2D logicalPos = new Position2D(26, 0);
        final LeftwardCharacterGC graphics = new LeftwardCharacterGC(Sprites.getInstance().getUggSprites().getFrontStandSprite(), 
                Sprites.getInstance().getUggSprites().getFrontMoveSprite(), new Position2D(Dimensions.getWindowWidth() + Dimensions.getSideSpawningHeight(), Dimensions.getBackgroundY() + Dimensions.getBackgroundHeight() - Dimensions.getCubeHeight()));

        return new Ugg(logicalPos, speed, graphics, standingTime);
    }

    private Position2D getRandomPos() {
        return new Random().nextInt(2) == 0 ? Dimensions.getSpawningPointLeft() : Dimensions.getSpawningPointRight();
    }

    private Position2D getLogicalPos(final Position2D randPos) {
        return randPos == Dimensions.getSpawningPointLeft() ? Dimensions.getSpawningLogPointLeft() : Dimensions.getSpawningLogPointRight();
    }

}
