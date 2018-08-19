package qbert.model.collision;

import qbert.model.characters.Player;

import java.util.function.BiPredicate;

import qbert.model.characters.Character;

public class CrossCollision implements BiPredicate<Character, Character> {

    public CrossCollision() {
    }

    @Override
    public final boolean test(final Character a, final Character b) {
        return a.getCurrentPosition().equals(b.getNextPosition()) && a.getNextPosition().equals(b.getCurrentPosition());
    }

}
