package test;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import qbert.controller.Controller;
import qbert.controller.ControllerImpl;
import qbert.controller.GameStatus;
import qbert.model.LevelSettings;
import qbert.model.LevelSettingsImpl;
import qbert.model.LevelStatus;
import qbert.model.LevelStatusImpl;
import qbert.model.characters.CharactersList;
import qbert.model.characters.Player;
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
import qbert.model.components.sounds.GameSC;
import qbert.model.spawner.EnemyInfoImpl;
import qbert.model.spawner.Spawner;
import qbert.model.spawner.SpawnerImpl;
import qbert.model.utilities.Position2D;
import qbert.view.ViewImpl;

/**
 * A class with some jUnit tests for {@link CharacterState}.
 */
public class TestMovements {

    private static final int DT = 20;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 40;
    private static final float SPEED = 0.35f;
    private static final int LIVES = 3;
    private static final int SCORE_1 = 7000;
    private static final int SCORE_2 = 2000;
    private static final int SCORE_3 = 8000;
    private static final int SCORE_4 = 5;

    private final Player qbert;
    private final TimerComponent timer;
    private final PointComponent points;
    private final MapComponent map;

    /**
     * Initialize useful variables.
     * @throws IOException 
     */
    public TestMovements() throws IOException {
        final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        final Map<Integer, BufferedImage> colorMap = new HashMap<>();
        colorMap.put(0, image);
        colorMap.put(1, image);
        final Map<CharactersList, EnemyInfoImpl> mapInfo = new HashMap<>();
        final LevelSettings settings = new LevelSettingsImpl(2, false, image, colorMap, 2, mapInfo, SPEED, 6000);
        final Controller controller = new ControllerImpl(GameStatus.GAMEPLAY, new ViewImpl());
        points = new PointComponentImpl(0);
        final Spawner spawner = new SpawnerImpl(mapInfo, SPEED, controller, LIVES);
        qbert = spawner.spawnQbert();
        map = new MapComponentImpl(settings);
        final LevelStatus status = new LevelStatusImpl(settings, qbert, spawner, points, map, new GameSC(controller));
        timer = new TimerComponentImpl(qbert, spawner, points, map, status);
    }

    /**
     * @throws InterruptedException when a thread problem occur.
     */
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

    /**
     * @throws InterruptedException when a thread problem occur.
     */
    @Test
    public void testLifeFlow() throws InterruptedException {
        points.score(SCORE_1, qbert);
        assertEquals(3, qbert.getLivesNumber());
        points.score(SCORE_2, qbert);
        assertEquals(4, qbert.getLivesNumber());
        points.score(SCORE_3, qbert);
        assertEquals(4, qbert.getLivesNumber());
        points.score(SCORE_3, qbert);
        assertEquals(SCORE_4, qbert.getLivesNumber());

        qbert.setCurrentState(new FallState(qbert));
        while (qbert.getCurrentState() instanceof FallState || qbert.getCurrentState() instanceof DeathState) {
            timer.update(DT);
            Thread.sleep(10);
        }

        assertEquals(4, qbert.getLivesNumber());
    }

}
