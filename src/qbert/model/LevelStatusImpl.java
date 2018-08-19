package qbert.model;

import qbert.model.characters.Player;
import qbert.model.components.MapComponent;
import qbert.model.components.PointComponent;
import qbert.model.components.PointComponentImpl;
import qbert.model.components.TimerComponent;
import qbert.model.components.TimerComponentImpl;
import qbert.model.components.sounds.SoundComponent;
import qbert.model.models.Game;
import qbert.model.spawner.Spawner;

/**
 * Component for handling the round winning conditions and actions.
 */
public class LevelStatusImpl implements LevelStatus {

    private final LevelSettings settings;
    private final Player qbert; 
    private final Spawner spawner;
    private final PointComponent points;
    private final MapComponent map;
    private final SoundComponent sounds;

    private Game gameObserver;

    /**
     * @param settings Instance of {@link LevelSettings}
     * @param qbert Instance of {@link Qbert}
     * @param spawner Instance of {@link Spawner}
     * @param points Instance of {@link PointComponent}
     * @param map Instance of {@link MapComponent}
     * @param sounds Instance of {@link SoundComponent}
     */
    public LevelStatusImpl(final LevelSettings settings, final Player qbert, final Spawner spawner, final PointComponent points, final MapComponent map, final SoundComponent sounds) {
        this.qbert = qbert;
        this.settings = settings;
        this.spawner = spawner;
        this.points = points;
        this.map = map;
        this.sounds = sounds;
    }

    @Override
    public final void changeRound(final TimerComponent timer) {
        this.points.score(this.settings.getRoundScore(), qbert);
        this.points.score(PointComponentImpl.UNUSED_DISK_SCORE * map.getDiskList().size(), qbert);

        this.spawner.killAll();
        this.sounds.setWinningARoundSound();

        timer.freezeEverything(() -> {
            this.notifyEndLevel();
        }, TimerComponentImpl.ROUND_ANIMATION_TIME);
    }


    @Override
    public final void checkStatus(final TimerComponent timer) {
        int coloredTiles = 0;
        for (final Tile t : map.getTileList()) {
            if (t.getColor() == this.settings.getColorsNumber()) {
                coloredTiles++;
            }
        }

        if (coloredTiles == this.map.getTileList().stream().count()) {
            this.changeRound(timer);
        }
    }

    @Override
    public final void addObserver(final Game gameObserver) {
        this.gameObserver = gameObserver;
    }

    @Override
    public final void notifyEndLevel() {
        this.gameObserver.changeRound();
    }
}
