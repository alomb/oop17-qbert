package qbert.model.collision;

import qbert.model.characters.states.LandState;

import java.util.function.BiPredicate;

import qbert.model.characters.Character;
import qbert.model.characters.Player;

/**
 * Implementation of collision check algorithm that triggers when {@link Qbert} stomps on a {@link Character}.
 */
public class StompCollision implements BiPredicate<Player, Character> {

    @Override
    public final boolean test(final Player a, final Character b) {
        return a.getNextPosition().equals(b.getCurrentPosition()) 
                && (b.getCurrentState() instanceof LandState || !b.isMoving());
    }

}
