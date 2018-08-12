package qbert.model.components.graphics;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import qbert.controller.LoadResources;
import qbert.controller.Sprites;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.characters.DisplaceAnimation;
import qbert.view.characters.ComposedAnimation;
import qbert.view.characters.StraightMovementAnimation;

import java.util.Random;

/**
 * A class with some jUnit tests for {@link CharacterGC}.
 */
public class TestCharacterGraphicComponent {

    private static final float SPEED = 7;

    private static final int SPRITEHEIGHT = 5;

    private final Random rnd;
    private static final int TEST = 20;

    /**
     * The constructor create a {@link Game} object to initialize some {@link Dimensions} used in {@link CharacterGC}.
     */
    public TestCharacterGraphicComponent() {
        final LoadResources loader = new LoadResources();
        loader.load();
        this.rnd = new Random();
    }

    /**
     * Method that provides an infinite loop to conclude the {@link CharacterGC}'s current animation.
     */
    private void finishAnimation(final CharacterGC cgc) {
        while (cgc.getCurrentAnimation() != null && !cgc.getCurrentAnimation().hasFinished()) {
            cgc.updateGraphics(TestCharacterGraphicComponent.SPEED);
        }
    }

    /**
     * @param cgc the {@link CharacterGC} to apply a downLeft animation
     */
    private void moveDownLeft(final CharacterGC cgc) {
        cgc.setMoveDownLeftAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpDownLeft);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() - Dimensions.getTileWidth() / 2, oldPos.getY() + Dimensions.getCubeHeight()));
    }

    /**
     * @param cgc the {@link CharacterGC} to apply a downRight animation
     */
    private void moveDownRight(final CharacterGC cgc) {
        cgc.setMoveDownRightAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpDownRight);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() + Dimensions.getTileWidth() / 2, oldPos.getY() + Dimensions.getCubeHeight()));
    }

    /**
     * @param cgc the {@link CharacterGC} to apply a upLeft animation
     */
    private void moveUpLeft(final DownUpwardCharacterGC cgc) {
        cgc.setMoveUpLeftAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpUpLeft);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() - Dimensions.getTileWidth() / 2, oldPos.getY() - Dimensions.getCubeHeight()));
    }

    /**
     * @param cgc the {@link CharacterGC} to apply a upRight animation
     */
    private void moveUpRight(final DownUpwardCharacterGC cgc) {
        cgc.setMoveUpRightAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpUpRight);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() + Dimensions.getTileWidth() / 2, oldPos.getY() - Dimensions.getCubeHeight()));
    }

    /**
     * A test method for {@link DownwardCharacterGCImpl}.
     */
    @Test
    public void testDownwardCGC() {
        final CharacterGC cgc = new DownwardCharacterGCImpl(Sprites.getInstance().getGreenBallSprites(), new Position2D(Dimensions.getSpawningPointLeft()));
        assertEquals(cgc.getPosition(), Dimensions.getSpawningPointLeft());
        cgc.setSpawnAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof StraightMovementAnimation);
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(Dimensions.getSpawningPointLeft().getX(), 
                (Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2 + Dimensions.getCubeHeight() - TestCharacterGraphicComponent.SPRITEHEIGHT));

        for (int i = 0; i < TestCharacterGraphicComponent.TEST; i++) {
            if (this.rnd.nextFloat() >= 0.5) {
                this.moveDownLeft(cgc);
            } else {
                this.moveDownRight(cgc);
            }
        }
    }

    /**
     * A test method for {@link CoilyGC}.
     */
    @Test
    public void testDownwardUpwardGC() {
        final Sprites s = Sprites.getInstance();
        final DownUpwardCharacterGC cgc = new CoilyGCImpl(s.getPurpleBallSprites(), s.getCoilyFrontSprites(), s.getCoilyBackSprites(), new Position2D(Dimensions.getSpawningPointRight()));
        final Position2D spawnPos = new Position2D(rnd.nextInt(10), rnd.nextInt(10));
        assertEquals(cgc.getPosition(), Dimensions.getSpawningPointRight());
        cgc.setSpawnPosition(spawnPos);
        cgc.setSpawnAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof DisplaceAnimation);
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), spawnPos);

        for (int i = 0; i < TestCharacterGraphicComponent.TEST; i++) {
            switch (this.rnd.nextInt(4)) {
                case 1:
                    this.moveDownLeft(cgc);
                    break;
                case 2:
                    this.moveDownRight(cgc);
                    break;
                case 3:
                    this.moveUpLeft(cgc);
                    break;
                default:
                    this.moveUpRight(cgc);
                    break;
            }
        }

        cgc.setFallAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof StraightMovementAnimation);
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(cgc.getPosition().getX(), Dimensions.getDeathHeight()));
    }
}
