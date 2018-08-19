package qbert.model.collision;

import qbert.model.characters.states.LandState;

import java.util.function.BiPredicate;

import qbert.model.characters.Character;
import qbert.model.characters.Player;

public class StompCollision implements BiPredicate<Player, Character> {

    @Override
    public boolean test(final Player a, final Character b) {
        return a.getNextPosition().equals(b.getCurrentPosition()) 
                && (b.getCurrentState() instanceof LandState || !b.isMoving());
    }

}
