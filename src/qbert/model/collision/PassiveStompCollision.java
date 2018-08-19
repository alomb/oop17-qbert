package qbert.model.collision;

import qbert.model.characters.states.LandState;

import java.util.function.BiPredicate;

import qbert.model.characters.Character;
import qbert.model.characters.Player;

/**
 * Implementation of collision check algorithm that triggers when a {@link Character} stomps on {@link Qbert}.
 */
public class PassiveStompCollision implements BiPredicate<Player, Character> {

    @Override
    public final boolean test(final Player a, final Character b) {
        return b.getNextPosition().equals(a.getCurrentPosition()) 
                && (a.getCurrentState() instanceof LandState || !a.isMoving());
    }

}
