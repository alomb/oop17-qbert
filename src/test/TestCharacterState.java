package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import qbert.controller.Controller;
import qbert.controller.ControllerImpl;
import qbert.controller.GameStatus;
import qbert.model.characters.Character;
import qbert.model.characters.Player;
import qbert.model.characters.states.DeathState;
import qbert.model.characters.states.DownwardCharStandingState;
import qbert.model.characters.states.FallState;
import qbert.model.characters.states.LandState;
import qbert.model.characters.states.MoveState;
import qbert.model.characters.states.QbertStandingState;
import qbert.model.characters.states.SpawnState;
import qbert.model.spawner.EnemyFactory;
import qbert.model.spawner.EnemyFactoryImpl;
import qbert.view.ViewImpl;

/**
 * A class with some jUnit tests for {@link CharacterState}.
 */
public class TestCharacterState {

    private static final int DT = 20;
    private static final float SPEED = 0.35f;
    private static final int LIVES = 3;
    private static final int STANDING_TIME = 1000;
    private static final int ROWS = 10;
    private static final int COLUMNS = 20;

    private final EnemyFactory ef;

    /**
     * Initialize useful variables.
     */
    public TestCharacterState() {
        final Controller controller = new ControllerImpl(GameStatus.GAMEPLAY, new ViewImpl());
        this.ef = new EnemyFactoryImpl(controller);
    }

    /**
     * Test a {@link DownwardCharacter} life cycle.
     */
    @Test
    public void testDownwardCharacterStates() {
        final Character character = this.ef.createRedBall(SPEED, STANDING_TIME);
        assertTrue(character.getCurrentState() instanceof SpawnState);
        while (character.getCurrentState() instanceof SpawnState) {
            character.update(DT);
        }
        while (isOnMap(character)) {
            assertTrue(character.getCurrentState() instanceof LandState);
            character.setCurrentState(character.getStandingState());
            while (character.getCurrentState() instanceof DownwardCharStandingState) {
                character.update(DT);
            }
            assertTrue(character.getCurrentState() instanceof MoveState);
            while (character.getCurrentState() instanceof MoveState) {
                character.update(DT);
            }
        }

        character.setCurrentState(new FallState(character));
        while (character.getCurrentState() instanceof FallState) {
            character.update(DT);
        }
        assertTrue(character.getCurrentState() instanceof DeathState);
    }

    /**
     * Test a {@link DownUpwardCharacter} life cycle.
     */
    @Test
    public void testDownUpwardCharacterStates() {
        final Player qbert = this.ef.createQbert(SPEED, LIVES);
        final Character coily = this.ef.createCoily(SPEED, STANDING_TIME, qbert);
        assertTrue(coily.getCurrentState() instanceof SpawnState);
        assertTrue(qbert.getCurrentState() instanceof QbertStandingState);

        coily.setCurrentState(new FallState(coily));
        while (coily.getCurrentState() instanceof FallState) {
            coily.update(DT);
        }
        assertTrue(coily.getCurrentState() instanceof DeathState);
    }


    private boolean isOnMap(final Character character) {
        return character.getNextPosition().getY() < 0 || character.getNextPosition().getY() > COLUMNS
                || character.getNextPosition().getX() < 0 || character.getNextPosition().getX() > ROWS;
    }
}
