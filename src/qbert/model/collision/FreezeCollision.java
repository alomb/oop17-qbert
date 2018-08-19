package qbert.model.collision;

import qbert.model.characters.Player;

import java.util.function.BiPredicate;

import qbert.model.characters.Character;

public class FreezeCollision implements BiPredicate<Player, Character> {

    private final boolean pauseEntities;

    public FreezeCollision(final boolean pauseEntities) {
        this.pauseEntities = pauseEntities;
    }

    @Override
    public boolean test(final Player qbert, final Character entity) {
        return qbert.getCurrentPosition().equals(entity.getCurrentPosition()) 
                && qbert.getNextPosition().equals(entity.getNextPosition()) 
                && this.pauseEntities;
    }
}
