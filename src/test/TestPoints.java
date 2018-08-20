package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import qbert.controller.Controller;
import qbert.controller.ControllerImpl;
import qbert.controller.GameStatus;
import qbert.controller.LoadResources;
import qbert.model.LevelSettings;
import qbert.model.LevelSettingsImpl;
import qbert.model.LevelStatus;
import qbert.model.LevelStatusImpl;
import qbert.model.characters.CharactersList;
import qbert.model.characters.Player;
import qbert.model.characters.Qbert;
import qbert.model.characters.states.DeathState;
import qbert.model.characters.states.FallState;
import qbert.model.characters.states.MoveState;
import qbert.model.characters.states.QbertStandingState;
import qbert.model.components.MapComponent;
import qbert.model.components.MapComponentImpl;
import qbert.model.components.PointComponent;
import qbert.model.components.PointComponentImpl;
import qbert.model.components.TimerComponent;
import qbert.model.components.TimerComponentImpl;
import qbert.model.components.graphics.PlayerGCImpl;
import qbert.model.components.sounds.GameSC;
import qbert.model.components.sounds.QbertSC;
import qbert.model.spawner.EnemyInfoImpl;
import qbert.model.spawner.Spawner;
import qbert.model.spawner.SpawnerImpl;
import qbert.model.sprites.OneSideCharacterSprites;
import qbert.model.sprites.OneSideCharacterSpritesImpl;
import qbert.model.sprites.SpecialCharacterSprites;
import qbert.model.sprites.SpecialCharacterSpritesImpl;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.ViewImpl;


/**
 * A class with some jUnit tests for {@link CharacterState}.
 */
public class TestPoints {

    private static final int DT = 20;

    private static final int WIDTH = 20;
    private static final int HEIGHT = 40;
    private final BufferedImage image;

    private final Player qbert;
    private final TimerComponent timer;
    private final PointComponent points;
    private final MapComponent map;
    final Map<CharactersList, EnemyInfoImpl> mapInfo;
    final LevelSettings settings;
    final Controller controller;
    final Spawner spawner;
    final LevelStatus status;

    /**
     * Initialize useful variables.
     * @throws IOException 
     */
    public TestPoints() throws IOException {
        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        final Map<Integer, BufferedImage> colorMap = new HashMap<>();
        colorMap.put(0, image);
        colorMap.put(1, image);
        mapInfo = new HashMap<>();
        settings = new LevelSettingsImpl(2, false, image, colorMap, 2, mapInfo, 0.35f, 6000);
        controller = new ControllerImpl(GameStatus.GAMEPLAY, new ViewImpl());
        points = new PointComponentImpl(0);
        spawner = new SpawnerImpl(mapInfo, 0.35f, controller, 3);
        qbert = spawner.spawnQbert();
        map = new MapComponentImpl(settings);
        status = new LevelStatusImpl(settings, qbert, spawner, points, map, new GameSC(controller));
        timer = new TimerComponentImpl(qbert, spawner, points, map, status);
    }

    @Test
    public void testTiles() throws InterruptedException {
            Position2D newPosition = new Position2D(qbert.getCurrentPosition().getX() + qbert.getStep(), qbert.getCurrentPosition().getY() - qbert.getStep());

            assertEquals(0, map.getTile(newPosition).get().getColor());

            qbert.setNextPosition(newPosition);
            qbert.setCurrentState(new MoveState.DownRight(qbert));

            while (!(qbert.getCurrentState() instanceof QbertStandingState)) {
                timer.update(DT);
                Thread.sleep(10);
            }

            assertEquals(1, map.getTile(newPosition).get().getColor());

            newPosition = new Position2D(qbert.getCurrentPosition().getX() - qbert.getStep(), qbert.getCurrentPosition().getY() + qbert.getStep());

            assertEquals(0, map.getTile(newPosition).get().getColor());

            qbert.setNextPosition(newPosition);
            qbert.setCurrentState(new MoveState.UpLeft(qbert));

            while (!(qbert.getCurrentState() instanceof QbertStandingState)) {
                timer.update(DT);
                Thread.sleep(10);
            }

            assertEquals(1, map.getTile(newPosition).get().getColor());

            newPosition = new Position2D(qbert.getCurrentPosition().getX() + qbert.getStep(), qbert.getCurrentPosition().getY() - qbert.getStep());

            qbert.setNextPosition(newPosition);
            qbert.setCurrentState(new MoveState.DownRight(qbert));

            while (!(qbert.getCurrentState() instanceof QbertStandingState)) {
                timer.update(DT);
                Thread.sleep(10);
            }

            assertEquals(1, map.getTile(newPosition).get().getColor());
    }

    @Test
    public void testLifeFlow() throws InterruptedException {
        points.score(7000, qbert);
        assertEquals(3, qbert.getLivesNumber());
        points.score(2000, qbert);
        assertEquals(4, qbert.getLivesNumber());
        points.score(8000, qbert);
        assertEquals(4, qbert.getLivesNumber());
        points.score(8000, qbert);
        assertEquals(5, qbert.getLivesNumber());

        qbert.setCurrentState(new FallState(qbert));
        while (qbert.getCurrentState() instanceof FallState || qbert.getCurrentState() instanceof DeathState) {
            timer.update(DT);
            Thread.sleep(10);
        }

        assertEquals(4, qbert.getLivesNumber());
    }

}
