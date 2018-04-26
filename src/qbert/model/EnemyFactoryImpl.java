package qbert.model;

import qbert.model.characters.Coily;
import qbert.model.characters.RedBall;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.CharacterGraphicComponent;
import qbert.view.DownwardCGC;

import java.util.Random;

import qbert.model.characters.Character;

/**
 * The implementation of {@link EnemyFactory} interface.
 */
public class EnemyFactoryImpl implements EnemyFactory {

    @Override
    public final Character createCoily(final float speed, final int standingTime, final Character qbert) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final CharacterGraphicComponent graphics = new DownwardCGC(Sprites.purpleBallStanding, Sprites.purpleBallMoving, randomPos);
        return new Coily(logicalPos, speed, graphics, standingTime, qbert);
    }

    @Override
    public final Character createRedBall(final float speed, final int standingTime) {
        final Position2D randomPos = this.getRandomPos();
        final Position2D logicalPos = this.getLogicalPos(randomPos);
        final CharacterGraphicComponent graphics = new DownwardCGC(Sprites.redBallStanding, Sprites.redBallMoving, randomPos);
        return new RedBall(logicalPos, speed, graphics, standingTime);
    }

    private Position2D getRandomPos() {
        return new Random().nextInt(2) == 0 ? Dimensions.spawningPointLeft : Dimensions.spawningPointRight;
    }

    private Position2D getLogicalPos(final Position2D randPos) {
        return randPos == Dimensions.spawningPointLeft ? new Position2D(5, 5) : new Position2D(7, 5);
    }
}
