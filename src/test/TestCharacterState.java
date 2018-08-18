package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.Test;

import qbert.controller.Sprites;
import qbert.model.characters.Character;
import qbert.model.characters.DownwardCharacter;
import qbert.model.characters.RedBall;
import qbert.model.characters.states.DeathState;
import qbert.model.characters.states.DownwardCharStandingState;
import qbert.model.characters.states.FallState;
import qbert.model.characters.states.LandState;
import qbert.model.characters.states.MoveState;
import qbert.model.characters.states.SpawnState;
import qbert.model.components.graphics.DownwardCharacterGC;
import qbert.model.utilities.Position2D;

/**
 * A class with some jUnit tests for {@link CharacterState}.
 */
public class TestCharacterState {

    private static final int DT = 20;
    private static final float SPEED = 0.35f;
    private static final int TIMER = 1000;

    private static final int ROWS = 10;
    private static final int COLUMNS = 20;

    /**
     * Test a {@link DownwardCharacter} life cycle.
     */
    @Test
    public void testDownwardCharacterStates() {
        try {
            final DownwardCharacter character = new RedBall(new Position2D(ROWS - 1, (COLUMNS / 2) + 1),
                    SPEED, new DownwardCharacterGC(Sprites.getInstance().getRedBallSprites(), new Position2D(0, 0)), TIMER);
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
        } catch (IOException e) {
            fail();
        }
    }

    private boolean isOnMap(final Character character) {
        return character.getNextPosition().getY() < 0 || character.getNextPosition().getY() > COLUMNS
                || character.getNextPosition().getX() < 0 || character.getNextPosition().getX() > ROWS;
    }
}
