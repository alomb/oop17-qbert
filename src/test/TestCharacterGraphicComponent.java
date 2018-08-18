package test;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import qbert.controller.LoadResources;
import qbert.controller.Sprites;
import qbert.model.components.graphics.CharacterGC;
import qbert.model.components.graphics.CoilyGCImpl;
import qbert.model.components.graphics.DownUpwardCharacterGC;
import qbert.model.components.graphics.DownwardCharacterGC;
import qbert.model.components.graphics.animations.ComposedAnimation;
import qbert.model.components.graphics.animations.StraightMovementAnimation;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

import java.io.IOException;
import java.util.Random;

/**
 * A class with some jUnit tests for {@link CharacterGC}.
 */
public class TestCharacterGraphicComponent {

    private static final float SPEED = 7;

    private final Random rnd;
    private static final int TEST = 20;

    private final Position2D spawningPointLeft;
    private final Position2D spawningPointRight;

    /**
     * The constructor create a {@link Game} object to initialize some {@link Dimensions} used in {@link CharacterGC}.
     */
    public TestCharacterGraphicComponent() {
        final LoadResources loader = new LoadResources();
        loader.load();
        this.rnd = new Random();
        this.spawningPointLeft = new Position2D(Math.round(new Float(Dimensions.getWindowWidth()) / 2f) - Dimensions.getCubeWidth(), 0);
        this.spawningPointRight = new Position2D(Math.round(new Float(Dimensions.getWindowWidth()) / 2f), 0);
    }

    /**
     * A test method for {@link DownwardCharacterGC}.
     */
    @Test
    public void testDownwardCGC() {
        try {
            final CharacterGC cgc = new DownwardCharacterGC(Sprites.getInstance().getGreenBallSprites(), new Position2D(spawningPointLeft));
            assertEquals(cgc.getPosition(), this.spawningPointLeft);
            final Position2D landPos = new Position2D(cgc.getSpawnPosition().getX(), (Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2 + Dimensions.getCubeHeight() - cgc.getSpriteHeight());
            cgc.setSpawnAnimation();
            assertTrue(cgc.getSprite().equals(Sprites.getInstance().getGreenBallSprites().getMoveSprite()));
            assertTrue(cgc.getCurrentAnimation() instanceof StraightMovementAnimation);
            this.finishAnimation(cgc);
            assertEquals(cgc.getPosition(), landPos);

            for (int i = 0; i < TestCharacterGraphicComponent.TEST; i++) {
                if (this.rnd.nextFloat() >= 0.5) {
                    this.moveDownLeft(cgc);
                } else {
                    this.moveDownRight(cgc);
                }
            }
            cgc.setStandingAnimation();
            cgc.setFallAnimation();
            this.finishAnimation(cgc);
            assertTrue(cgc.getPosition().equals(new Position2D(cgc.getPosition().getX(), Dimensions.getWindowHeight() + cgc.getSpriteHeight())));
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * A test method for {@link CoilyGC} and other {@link DownUpwardCharacterGC}.
     */
    @Test
    public void testDownwardUpwardGC() {
        try {
            final Sprites s = Sprites.getInstance();
            final DownUpwardCharacterGC cgc = new CoilyGCImpl(s.getPurpleBallSprites(), s.getCoilyFrontSprites(), s.getCoilyBackSprites(), new Position2D(this.spawningPointRight));
            final Position2D landPos = new Position2D(cgc.getSpawnPosition().getX(), (Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2 + Dimensions.getCubeHeight() - cgc.getSpriteHeight());
            assertEquals(cgc.getPosition(), this.spawningPointRight);
            cgc.setSpawnAnimation();
            assertTrue(cgc.getCurrentAnimation() instanceof StraightMovementAnimation);
            this.finishAnimation(cgc);
            assertEquals(cgc.getPosition(), landPos);

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
            assertEquals(cgc.getPosition(), new Position2D(cgc.getPosition().getX(), Dimensions.getWindowHeight() + cgc.getSpriteHeight()));
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * Method that provides an infinite loop to conclude the {@link CharacterGC}'s current animation.
     */
    private void finishAnimation(final CharacterGC cgc) {
        while (cgc.getCurrentAnimation() != null && !cgc.getCurrentAnimation().hasNext()) {
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
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() - Dimensions.getCubeWidth() / 2, oldPos.getY() + Dimensions.getCubeHeight()));
    }

    /**
     * @param cgc the {@link CharacterGC} to apply a downRight animation
     */
    private void moveDownRight(final CharacterGC cgc) {
        cgc.setMoveDownRightAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpDownRight);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() + Dimensions.getCubeWidth() / 2, oldPos.getY() + Dimensions.getCubeHeight()));
    }

    /**
     * @param cgc the {@link CharacterGC} to apply a upLeft animation
     */
    private void moveUpLeft(final DownUpwardCharacterGC cgc) {
        cgc.setMoveUpLeftAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpUpLeft);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() - Dimensions.getCubeWidth() / 2, oldPos.getY() - Dimensions.getCubeHeight()));
    }

    /**
     * @param cgc the {@link CharacterGC} to apply a upRight animation
     */
    private void moveUpRight(final DownUpwardCharacterGC cgc) {
        cgc.setMoveUpRightAnimation();
        assertTrue(cgc.getCurrentAnimation() instanceof ComposedAnimation.JumpUpRight);
        final Position2D oldPos = new Position2D(cgc.getPosition());
        this.finishAnimation(cgc);
        assertEquals(cgc.getPosition(), new Position2D(oldPos.getX() + Dimensions.getCubeWidth() / 2, oldPos.getY() - Dimensions.getCubeHeight()));
    }
}
