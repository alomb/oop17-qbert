package qbert.model;

import java.util.function.BiPredicate;


import qbert.model.characters.Player;
import qbert.model.characters.Character;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;

/**
 * Interface used for entities which can collide with {@link Qbert} and generate some consequences.
 */
public abstract class CollidableImpl implements Collidable {

    @Override
    public final boolean checkCollision(final Player qbert, final Character entity, final PointComponent points, final TimerComponent timer, final BiPredicate<Player, Character> collision) {
        if (collision.test(qbert, entity)) {
            this.collide(qbert, points, timer);
            return true;
        }
        return false;
    }

    /**
     * Event happening on the collision between the entity and {@link Qbert}.
     * @param qbert {@link Player} reference for dealing with deadly collisions
     * @param points reference to {@link PointComponent} to eventually score points in collisions
     * @param timer reference to {@link TimerComponent} for dealing with time flow changing collisions
     */
    protected abstract void collide(Player qbert, PointComponent points, TimerComponent timer);
}
