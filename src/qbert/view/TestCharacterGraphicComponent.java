package qbert.view;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import qbert.model.Dimensions;
import qbert.model.Game;
import qbert.model.utilities.Position2D;
import qbert.view.animations.Jump;
import qbert.view.animations.MoveAnimation;

import java.awt.image.BufferedImage;

/**
 *
 */
public class TestCharacterGraphicComponent {

    private static final float SPEED = 7;

    private static final int SPRITEHEIGHT = 5; 
    private static final int SPRITEWIDTH = 5;

    private final BufferedImage image = new BufferedImage(TestCharacterGraphicComponent.SPRITEWIDTH, 
            TestCharacterGraphicComponent.SPRITEHEIGHT, BufferedImage.TYPE_INT_ARGB);

    /**
     * 
     */
    public TestCharacterGraphicComponent() {
        new Game();
    }

    private void finishAnimation(final CharacterGraphicComponent cgc) {
        Position2D oldPos = null;

        while (true) {
            cgc.updateGraphics(TestCharacterGraphicComponent.SPEED);

            if (cgc.getPosition().equals(oldPos)) {
                break;
            } else {
                oldPos = cgc.getPosition();
            }
        }
    }

    /**
     * @param cgc the {@link CharacterGraphicComponent} to apply a downLeft animation
     */
    public void moveDownLeft(final CharacterGraphicComponent cgc) {
        cgc.setMoveDownLeftAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof Jump.DownLeft);
        Position2D oldPos = cgc.getPosition();
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() - Dimensions.tileWidth / 2, oldPos.getY() + Dimensions.cubeHeight));
    }

    /**
     * @param cgc the {@link CharacterGraphicComponent} to apply a downRight animation
     */
    public void moveDownRight(final CharacterGraphicComponent cgc) {
        cgc.setMoveDownRightAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof Jump.DownRight);
        Position2D oldPos = cgc.getPosition();
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() + Dimensions.tileWidth / 2, oldPos.getY() + Dimensions.cubeHeight));
    }
    /**
     * 
     */
    @Test
    public void testDownwardCGC() {
        final CharacterGraphicComponent cgc = new DownwardCGC(image, image, new Position2D(Dimensions.spawingPointLeft));
        assertEquals(cgc.getPosition(), Dimensions.spawingPointLeft);
        cgc.setSpawnAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof MoveAnimation.Down);
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(Dimensions.spawingPointLeft.getX(), 
                (Dimensions.windowHeight - Dimensions.backgroundHeight) / 2 + Dimensions.cubeHeight - TestCharacterGraphicComponent.SPRITEHEIGHT));

        this.moveDownLeft(cgc);
        this.moveDownRight(cgc);
    }
}
