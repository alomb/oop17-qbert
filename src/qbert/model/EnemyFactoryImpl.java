package qbert.model;

import qbert.model.characters.Character;
import qbert.model.characters.Coily;
import qbert.model.characters.CoilyImpl;
import qbert.model.characters.Player;
import qbert.model.characters.Qbert;
import qbert.model.characters.RedBall;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.characters.CharacterGC;
import qbert.view.characters.CoilyGC;
import qbert.view.characters.CoilyGCImpl;
import qbert.view.characters.DownwardCharacterGCImpl;
import qbert.view.characters.PlayerGC;
import qbert.view.characters.PlayerGCImpl;

import java.util.Random;
/**
 * The implementation of {@link EnemyFactory} interface.
 */
public class EnemyFactoryImpl implements EnemyFactory {

    @Override
    public final Player createQbert() {

        final PlayerGC graphics = new PlayerGCImpl(Sprites.qbertFrontStanding, Sprites.qbertFrontMoving, Sprites.qbertBackStanding, Sprites.qbertBackMoving, 
                Sprites.qbertDead, Sprites.qbertOnDisk, new Position2D(Dimensions.spawningQBert));

        return new Qbert(new Position2D(6, 6), 0.35f, graphics);
    }

    @Override
    public final Coily createCoily(final float speed, final int standingTime, final Player qbert) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final CoilyGC graphics = new CoilyGCImpl(Sprites.purpleBallStanding, Sprites.coilyFrontStanding, 
                Sprites.coilyFrontMoving, Sprites.purpleBallMoving, Sprites.coilyBackStanding, Sprites.coilyBackMoving, randomPos);
        return new CoilyImpl(logicalPos, speed, graphics, standingTime, qbert);
    }

    @Override
    public final Character createRedBall(final float speed, final int standingTime) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final CharacterGC graphics = new DownwardCharacterGCImpl(Sprites.redBallStanding, Sprites.redBallMoving, randomPos);
        return new RedBall(logicalPos, speed, graphics, standingTime);
    }

    private Position2D getRandomPos() {
        return new Random().nextInt(2) == 0 ? Dimensions.spawningPointLeft : Dimensions.spawningPointRight;
    }

    private Position2D getLogicalPos(final Position2D randPos) {
        return randPos == Dimensions.spawningPointLeft ? new Position2D(5, 5) : new Position2D(7, 5);
    }

}
