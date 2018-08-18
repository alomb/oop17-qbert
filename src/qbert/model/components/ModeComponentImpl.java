package qbert.model.components;

import qbert.model.LevelSettings;
import qbert.model.Tile;
import qbert.model.characters.Player;
import qbert.model.characters.states.DeathState;
import qbert.model.components.sounds.SoundComponent;
import qbert.model.models.Game;
import qbert.model.spawner.Spawner;

public class ModeComponentImpl implements ModeComponent {

    private final LevelSettings settings;
    private final Player qbert; 
    private final Spawner spawner;
    private final PointComponent points;
    private final MapComponent map;
    private final SoundComponent sounds;

    private Game gameObserver;

    public ModeComponentImpl(final LevelSettings settings, final Player qbert, final Spawner spawner, final PointComponent points, final MapComponent map, final SoundComponent sounds) {
        this.qbert = qbert;
        this.settings = settings;
        this.spawner = spawner;
        this.points = points;
        this.map = map;
        this.sounds = sounds;
    }

    @Override
    public final void changeRound(TimerComponent timer) {
        this.points.score(this.settings.getRoundScore(), qbert);
        this.points.score(PointComponentImpl.UNUSED_DISK_SCORE * map.getDiskList().size(), qbert);

        this.spawner.getGameCharacters().forEach(c -> c.setCurrentState(new DeathState(c)));

        this.sounds.setWinningARoundSound();

        //TODO: Start of animation
        timer.freezeEverything(() -> {
            //TODO: End of animation
            this.notifyEndLevel();
        }, TimerComponentImpl.ROUND_ANIMATION_TIME);
    }


    @Override
    public final void checkStatus(TimerComponent timer) {
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
    public void addObserver(final Game gameObserver) {
        this.gameObserver = gameObserver;
    }

    @Override
    public void notifyEndLevel() {
        this.gameObserver.changeRound();
    }
}
