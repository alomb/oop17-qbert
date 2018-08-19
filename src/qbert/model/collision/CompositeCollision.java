package qbert.model.collision;

import qbert.model.characters.Player;

import java.util.function.BiPredicate;

import qbert.model.characters.Character;

/**
 * Implementation of various condition for collision check algorithm.
 */
public class CompositeCollision implements BiPredicate<Player, Character> {

    private final boolean entityFreezed;

    /**
     * @param entityFreezed True if the {@link Player} got the {@link GreenBall} and froze the entities
     */
    public CompositeCollision(final boolean entityFreezed) {
        this.entityFreezed = entityFreezed;
    }

    @Override
    public final boolean test(final Player qbert, final Character entity) {
        return qbert.getCurrentPosition().equals(entity.getNextPosition()) && qbert.getNextPosition().equals(entity.getCurrentPosition())
                || (qbert.getCurrentPosition().equals(entity.getCurrentPosition()) && qbert.getNextPosition().equals(entity.getNextPosition()) && this.entityFreezed)
                || ((qbert.getCurrentPosition().getX() - 1 == entity.getCurrentPosition().getX() ||  qbert.getCurrentPosition().getX() + 1 == entity.getCurrentPosition().getX()) && qbert.getCurrentPosition().getY() + 1 == entity.getCurrentPosition().getY() && !entity.isMoving() && !qbert.isMoving());
    }

}
