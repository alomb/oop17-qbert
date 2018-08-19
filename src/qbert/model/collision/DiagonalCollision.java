package qbert.model.collision;

import qbert.model.characters.Player;

import java.util.function.BiPredicate;
import qbert.model.characters.Character;

/**
 * Implementation of collision check algorithm for {@link Wrongway} and {@link Ugg} collisions.
 */
public class DiagonalCollision implements BiPredicate<Player, Character> {

    @Override
    public final boolean test(final Player a, final Character b) {
        return (a.getCurrentPosition().getX() - 1 == b.getCurrentPosition().getX() || a.getCurrentPosition().getX() + 1 == b.getCurrentPosition().getX()) 
                && a.getCurrentPosition().getY() + 1 == b.getCurrentPosition().getY() 
                && !b.isMoving() && !a.isMoving();
    }

}
