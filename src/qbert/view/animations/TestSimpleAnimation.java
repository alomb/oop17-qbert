package qbert.view.animations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import qbert.model.utilities.Position2D;

/**
 * A JUnit class to test basic animations.
 */
public class TestSimpleAnimation {

    private final Random random = new Random();
    private static final float SPEED = 7;

    private List<Position2D> returnArrayOfRandomPosition2D(final int size) {
        final List<Position2D> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(this.returnRandomPosition2D());
        }
        return result;
    }

    private Position2D returnRandomPosition2D() {
        return new Position2D(this.random.nextInt(10), this.random.nextInt(10));
    }

    /**
     * Test {@link DisplaceAnimation}.
     */
    @Test
    public void testDisplaceAnimation() {
        final int size = this.random.nextInt(10) + 1;
        Position2D source = returnRandomPosition2D();
        final List<Position2D> target = returnArrayOfRandomPosition2D(size);
        for (final Position2D t : target) {
            final Animation anim = new DisplaceAnimation(source, t);
            while (!anim.hasFinished()) {
                source = anim.updateAnimation(TestSimpleAnimation.SPEED);
            }
        }
        assertEquals(source, target.get(size - 1));
    }

    /**
     * Test {@link Down} and {@link Up}.
     */
    @Test
    public void testMoveDownAnimation() {
        Position2D source = returnRandomPosition2D();
        final int size = this.random.nextInt(5) + 10;

        for (int i = 0; i < size; i++) {
            final Position2D target = new Position2D(source.getX(), this.random.nextInt(5) + source.getY());
            final MoveAnimation.Down anim = new MoveAnimation.Down(source, target);
            while (!anim.hasFinished()) {
                source = anim.updateAnimation(TestSimpleAnimation.SPEED);
            }
            assertEquals(source, target);
        }

        for (int i = 0; i < size; i++) {
            final Position2D target = new Position2D(source.getX(), source.getY() - this.random.nextInt(10));
            final MoveAnimation.Up anim = new MoveAnimation.Up(source, target);
            while (!anim.hasFinished()) {
                source = anim.updateAnimation(TestSimpleAnimation.SPEED);
            }
            assertEquals(source, target);
        }
    }

    /**
     * Test {@link ArcClockwise} and {@link ArcCounterClockwise}.
     */
    @Test
    public void testMoveAnimation() {
        Position2D source = returnRandomPosition2D();
        final int size = this.random.nextInt(5) + 10;

        for (int i = 0; i < size; i++) {
            final Position2D target = new Position2D(source.getX() + this.random.nextInt(20), source.getY());
            final MoveAnimation.ArcClockwise anim = new MoveAnimation.ArcClockwise(source, target);
            while (!anim.hasFinished()) {
                source = anim.updateAnimation(TestSimpleAnimation.SPEED);
            }
            assertEquals(source, target);
        }

        for (int i = 0; i < size; i++) {
            final Position2D target = new Position2D(source.getX() - this.random.nextInt(20), source.getY());
            final MoveAnimation.ArcCounterclockwise anim = new MoveAnimation.ArcCounterclockwise(source, target);
            while (!anim.hasFinished()) {
                source = anim.updateAnimation(TestSimpleAnimation.SPEED);
            }
            assertEquals(source, target);
        }
    }
}
