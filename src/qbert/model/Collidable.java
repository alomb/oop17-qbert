package qbert.model;

import java.util.function.BiPredicate;


import qbert.model.characters.Player;
import qbert.model.characters.Character;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;

/**
 * Interface used for entities which can collide with {@link Qbert} and generate some consequences.
 */
public interface Collidable {

    /**
     * Checks if {@link Player} and given {@link Character} collided.
     * @param qbert {@link Player} reference for dealing with deadly collisions
     * @param entity {@link Character} the entity than needs to be checked for the collision
     * @param points reference to {@link PointComponent} to eventually score points in collisions
     * @param timer reference to {@link TimerComponent} for dealing with time flow changing collisions
     * @param collision Set of condition for the collision check
     * @return true if the collision happened
     */
    boolean checkCollision(Player qbert, Character entity, PointComponent points, TimerComponent timer, BiPredicate<Player, Character> collision);

}
