package qbert.model.components;

import java.util.stream.Collectors;

import qbert.model.Level;
import qbert.model.characters.Coily;
import qbert.model.characters.DownUpwardCharacter;
import qbert.model.characters.Player;
import qbert.model.characters.states.DeathState;
import qbert.model.characters.states.FallState;
import qbert.model.characters.states.LandState;
import qbert.model.spawner.Spawner;
import qbert.model.utilities.Position2D;

public class StandardUpdate extends UpdateManager {
    
    public StandardUpdate(Player qbert, Spawner spawner, PointComponent points, MapComponent map, TimerComponent timer,
            Level level) {
        super(qbert, spawner, points, map, timer, level);
    }

    /**
     * @param elapsed the time passed since the last game cycle
     */
    public void update(final float elapsed) {
        qbert.update(elapsed);
        spawner.getGameCharacters().stream().forEach(e -> {
            e.update(elapsed);
        });
        if (spawner.getCoily().isPresent()) {
            spawner.getCoily().get().update(elapsed);
        }

        this.updateCollisions(elapsed);

        this.updateQbert(elapsed);
        this.updateDisks(elapsed);
        this.updateEntities(elapsed);
    }
}
