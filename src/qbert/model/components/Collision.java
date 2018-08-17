package qbert.model.components;

import qbert.model.characters.Player;
import qbert.model.characters.Character;

@FunctionalInterface
public interface Collision {

    boolean check(final Player qbert, final Character entity);

}
