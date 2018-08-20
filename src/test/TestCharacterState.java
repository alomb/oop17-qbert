package test;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Test;

import qbert.controller.ControllerImpl;
import qbert.controller.GameStatus;
import qbert.model.characters.Character;
import qbert.model.characters.Coily;
import qbert.model.characters.DownUpwardCharacter;
import qbert.model.characters.DownwardCharacter;
import qbert.model.characters.Player;
import qbert.model.characters.Qbert;
import qbert.model.characters.RedBall;
import qbert.model.characters.states.DeathState;
import qbert.model.characters.states.DownwardCharStandingState;
import qbert.model.characters.states.FallState;
import qbert.model.characters.states.LandState;
import qbert.model.characters.states.MoveState;
import qbert.model.characters.states.QbertStandingState;
import qbert.model.characters.states.SpawnState;
import qbert.model.components.graphics.CoilyGCImpl;
import qbert.model.components.graphics.DownwardCharacterGC;
import qbert.model.components.graphics.PlayerGCImpl;
import qbert.model.components.sounds.DownUpwardCharacterSC;
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
public class TestCharacterState {

    private static final int DT = 20;
    private static final float SPEED = 0.35f;
    private static final int TIMER = 1000;

    private static final int ROWS = 10;
    private static final int COLUMNS = 20;

    private static final int WIDTH = 20;
    private static final int HEIGHT = 40;
    private final OneSideCharacterSprites side;
    private final SpecialCharacterSprites sSide;

    /**
     * Initialize useful variables.
     */
    public TestCharacterState() {
        final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.side = new OneSideCharacterSpritesImpl(image, image);
        this.sSide = new SpecialCharacterSpritesImpl(image, image);
    }

    /**
     * Test a {@link DownwardCharacter} life cycle.
     */
    @Test
    public void testDownwardCharacterStates() {
        final DownwardCharacter character = new RedBall(new Position2D(ROWS - 1, (COLUMNS / 2) + 1),
                SPEED, new DownwardCharacterGC(side, new Position2D(0, 0)), TIMER);
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
        final Player qbert = new Qbert(new Position2D(Dimensions.MAP_SPAWNING_QBERT_X, Dimensions.MAP_SPAWNING_QBERT_X), SPEED, 
                new PlayerGCImpl(side, side, sSide, new Position2D(0, 0)), null);
        final DownUpwardCharacter coily = new Coily(new Position2D(ROWS - 1, (COLUMNS / 2) + 1),
                SPEED, new CoilyGCImpl(side, side, side, new Position2D(0, 0)), new DownUpwardCharacterSC(new ControllerImpl(GameStatus.GAMEPLAY, new ViewImpl())), TIMER, qbert);
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
