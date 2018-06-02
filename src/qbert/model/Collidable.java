package qbert.model;

import qbert.model.characters.Character;
import qbert.model.characters.Player;
import qbert.model.components.PointComponent;
import qbert.model.components.TimerComponent;

public interface Collidable {

    /**
     * @param l temporary reference to level for dealing collision between {@link Character} and QBert
     * @param points reference to {@link PointComponent} to eventually score points in entity collisions
     */
    void collide(Player qbert, PointComponent points, TimerComponent timer);
    
}
