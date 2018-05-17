package qbert.view.characters;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.animations.DisplaceAnimation;
import qbert.view.animations.UpwardDiagonalAnimation;

/**
 * GC stands for graphic component, this implementation is used to manage the {@link Player}.
 */
public class PlayerGCImpl extends DownUpwardCharacterGCImpl implements PlayerGC {

    private final BufferedImage deathSprite;
    private final BufferedImage onDiskSprite;

    /**
     * @param frontStandSprite the {@link BufferedImage} containing the {@link Character}'s standing front sprite
     * @param frontMoveSprite the {@link BufferedImage} containing the {@link Character}'s moving front sprite
     * @param backStandSprite the {@link BufferedImage} containing the {@link Character}'s standing back sprite
     * @param backMoveSprite the {@link BufferedImage} containing the {@link Character}'s moving back sprite
     * @param deathSprite the {@link BufferedImage} containing the {@link Character}'s sprite when it dies
     * @param onDiskSprite the {@link BufferedImage} containing the {@link Character}'s sprite when it stays on the disk
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public PlayerGCImpl(final BufferedImage frontStandSprite, final BufferedImage frontMoveSprite, final BufferedImage backStandSprite,
            final BufferedImage backMoveSprite, final BufferedImage deathSprite, final BufferedImage onDiskSprite,
            final Position2D startSpritePos) {
        super(frontStandSprite, frontMoveSprite, backStandSprite, backMoveSprite, startSpritePos);
        this.deathSprite = deathSprite;
        this.onDiskSprite = onDiskSprite;
    }

    @Override
    public final void setDeathAnimation() {
        this.setPosition(new Position2D(this.getPosition().getX(), 
                this.getPosition().getY() + (this.getSpriteHeight() - deathSprite.getHeight())));
        this.setSprite(this.deathSprite);
        if (this.isRight()) {
            this.flipOnYImage();
        }
    }

    @Override
    public final void setSpawnAnimation() {
        this.setSprite(this.getFrontStandSprite());
        this.setRight(true);
        this.setFront(true);
        this.setCurrentAnimation(new DisplaceAnimation(this.getPosition(), this.getSpawnPosition()));
    }

    @Override
    public final void setOnDiskAnimation() {
        this.setSprite(this.onDiskSprite);
        /*Possible flip?*/

        /*Temporary position*/
        final Position2D ondiskendPos = new Position2D(Dimensions.spawningQBert.getX(), Dimensions.spawningQBert.getY() + this.getSpriteHeight() * 2);
        this.setCurrentAnimation(new UpwardDiagonalAnimation(this.getPosition(), ondiskendPos));
    }
}
