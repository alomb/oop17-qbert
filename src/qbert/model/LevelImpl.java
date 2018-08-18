package qbert.model;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import qbert.controller.Controller;
import qbert.model.characters.Player;
import qbert.model.characters.states.DeathState;
import qbert.model.components.MapComponent;
import qbert.model.components.MapComponentImpl;
import qbert.model.components.PointComponent;
import qbert.model.components.PointComponentImpl;
import qbert.model.components.TimerComponent;
import qbert.model.components.TimerComponentImpl;
import qbert.model.models.Game;
import qbert.model.spawner.Spawner;
import qbert.model.spawner.SpawnerImpl;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.components.graphics.GenericGC;
import qbert.model.components.graphics.GraphicComponent;
import qbert.model.components.graphics.GraphicComponentImpl;
import qbert.model.components.graphics.Renderable;
import qbert.model.components.graphics.RenderableObject;
import qbert.model.components.sounds.GameSC;
import qbert.model.components.sounds.SoundComponent;

/**
 * Implementation of interface {@link Level}.
 * Manages classes that keeps information about the current game
 *
 */
public final class LevelImpl implements Level {

    private final Player qbert;
    private final Spawner spawner;
    private final PointComponent points;
    private final MapComponent map;
    private final TimerComponent timer;
    private final Renderable background;

    private final LevelSettings settings;
    private final Controller controller;
    private final SoundComponent sounds;

    private Game gameObserver;

    /**
     * Constructor of class LevelImpl.
     * @param levelSettings Set of settings that determine how to build the level
     * @param lives Number of lives the player is starting the level with
     * @param score Amount of points the player is starting the level with
     * @param controller Instance of controller
     */
    public LevelImpl(final LevelSettings levelSettings, final int lives, final int score, final Controller controller) {
        this.settings = levelSettings;
        this.controller = controller;
        this.sounds = new GameSC(this.controller);
        this.spawner = new SpawnerImpl(levelSettings.getMapInfo(), levelSettings.getQBertSpeed(), controller, lives);
        this.qbert = this.spawner.spawnQbert();
        this.points = new PointComponentImpl(score);

        this.map = new MapComponentImpl(settings);

        this.timer = new TimerComponentImpl(qbert, spawner, points, map, this);

        final GraphicComponent backgroundGC = new GenericGC(this.settings.getBackgroundImage(), 
                new Position2D(Dimensions.getBackgroundPos().getX(), Dimensions.getBackgroundPos().getY()));
        this.background = new RenderableObject(backgroundGC);
    }

    @Override
    public void addObserver(final Game gameObserver) {
        this.gameObserver = gameObserver;
    }

    @Override
    public void notifyEndLevel() {
        this.gameObserver.changeRound();
    }

    @Override
    public MapComponent getMap() {
        return this.map;
    }

    @Override
    public BufferedImage getBackground() {
        return this.settings.getBackgroundImage();
    }

    @Override
    public Player getQBert() {
        return this.qbert;
    }

    @Override
    public int getPoints() {
        return this.points.getPoints();
    }

    @Override
    public void checkStatus() {
        int coloredTiles = 0;
        for (final Tile t : this.map.getTileList()) {
            if (t.getColor() == this.settings.getColorsNumber()) {
                coloredTiles++;
            }
        }

        if (coloredTiles == this.map.getTileList().stream().count()) {
            this.changeRound();
        }
    }

    @Override
    public int getLives() {
        return qbert.getLivesNumber();
    }

    @Override
    public void changeRound() {
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
    public List<Renderable> getRenderables() {
        Stream<Renderable> resultingStream = Stream.of(
                Stream.of(this.getTargetColor()),
                Stream.of(this.background),
                Stream.of(this.qbert),
                map.getTileList().stream(),
                spawner.getGameCharacters().stream()
        ).flatMap(i -> i);

        if (spawner.getCoily().isPresent()) {
            resultingStream = Stream.concat(resultingStream, Stream.of(spawner.getCoily().get()));
        }
        return resultingStream.collect(Collectors.toList());
    }

    @Override
    public void update(final float elapsed) {
        timer.update(elapsed);
    }

    private Renderable getTargetColor() {
        final Optional<Integer> i = settings.getColorMap().keySet().stream().collect(Collectors.toList()).stream().max((o1, o2) -> o1.compareTo(o2));
        if (i.isPresent()) {
            final GraphicComponent gc = new GraphicComponentImpl(settings.getColorMap().get(i.get()), new Position2D(Math.round(Dimensions.getWindowWidth() / 9f), Math.round(Dimensions.getWindowHeight() / 4f)));
            return new RenderableObject(gc);
        }

        return null;
    }
}
