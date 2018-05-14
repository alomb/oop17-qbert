package qbert.view;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import qbert.model.Game;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.animations.DisplaceAnimation;
import qbert.view.animations.ComposedAnimation;
import qbert.view.animations.BasicAnimation;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * A class with some jUnit tests for {@link CharacterGraphicComponent}.
 */
public class TestCharacterGraphicComponent {

    private static final float SPEED = 7;

    private static final int SPRITEHEIGHT = 5; 
    private static final int SPRITEWIDTH = 5;

    private final BufferedImage image = new BufferedImage(TestCharacterGraphicComponent.SPRITEWIDTH, 
            TestCharacterGraphicComponent.SPRITEHEIGHT, BufferedImage.TYPE_INT_ARGB);

    private final Random rnd;
    private static final int TEST = 20;

    /**
     * The constructor create a {@link Game} object to initialize some {@link Dimensions} used in {@link CharacterGraphicComponent}.
     */
    public TestCharacterGraphicComponent() {
        new Game();
        this.rnd = new Random();
    }

    /**
     * Method that provides an infinite loop to conclude the {@link CharacterGraphicComponent}'s current animation.
     */
    private void finishAnimation(final CharacterGraphicComponent cgc) {
        while (cgc.getCurrentAnimation() != null && !cgc.getCurrentAnimation().hasFinished()) {
            cgc.updateGraphics(TestCharacterGraphicComponent.SPEED);
        }
    }

    /**
     * @param cgc the {@link CharacterGraphicComponent} to apply a downLeft animation
     */
    private void moveDownLeft(final CharacterGraphicComponent cgc) {
        cgc.setMoveDownLeftAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpDownLeft);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() - Dimensions.tileWidth / 2, oldPos.getY() + Dimensions.cubeHeight));
    }

    /**
     * @param cgc the {@link CharacterGraphicComponent} to apply a downRight animation
     */
    private void moveDownRight(final CharacterGraphicComponent cgc) {
        cgc.setMoveDownRightAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpDownRight);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() + Dimensions.tileWidth / 2, oldPos.getY() + Dimensions.cubeHeight));
    }

    /**
     * @param cgc the {@link CharacterGraphicComponent} to apply a upLeft animation
     */
    private void moveUpLeft(final DownUpwardCharacterGC cgc) {
        cgc.setMoveUpLeftAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpUpLeft);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() - Dimensions.tileWidth / 2, oldPos.getY() - Dimensions.cubeHeight));
    }

    /**
     * @param cgc the {@link CharacterGraphicComponent} to apply a upRight animation
     */
    private void moveUpRight(final DownUpwardCharacterGC cgc) {
        cgc.setMoveUpRightAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpUpRight);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() + Dimensions.tileWidth / 2, oldPos.getY() - Dimensions.cubeHeight));
    }

    /**
     * A test method for {@link DownwardCharacterGCImpl}.
     */
    @Test
    public void testDownwardCGC() {
        final CharacterGraphicComponent cgc = new DownwardCharacterGCImpl(image, image, new Position2D(Dimensions.spawningPointLeft));
        assertEquals(cgc.getPosition(), Dimensions.spawningPointLeft);
        cgc.setSpawnAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof BasicAnimation.Down);
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(Dimensions.spawningPointLeft.getX(), 
                (Dimensions.windowHeight - Dimensions.backgroundHeight) / 2 + Dimensions.cubeHeight - TestCharacterGraphicComponent.SPRITEHEIGHT));

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
        final DownUpwardCharacterGC cgc = new CoilyGCImpl(image, image, image, image, image, image, new Position2D(Dimensions.spawningPointRight));
        final Position2D spawnPos = new Position2D(rnd.nextInt(10), rnd.nextInt(10));
        assertEquals(cgc.getPosition(), Dimensions.spawningPointRight);
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
        assertTrue(cgc.getCurrentAnimation() instanceof BasicAnimation.Down);
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(cgc.getPosition().getX(), Dimensions.deathHeight));
    }
}
