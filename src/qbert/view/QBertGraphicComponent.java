package qbert.view;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Position2D;
import qbert.view.animations.DisplaceAnimation;

/**
 * The {@link CharacterGraphicComponent} implementation for {@link Qbert}.
 */
public class QBertGraphicComponent extends CharacterGraphicComponentImpl {

    private Position2D spawnPos;

    /**
     * @param sprite the {@link BufferedImage} containing the {@link Character}'s sprite
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public QBertGraphicComponent(final BufferedImage sprite, final Position2D startSpritePos) {
        super(sprite, startSpritePos);
        this.spawnPos = startSpritePos;
    }

    @Override
    public void setSpawnPosToCurrentPos() {
        this.spawnPos = this.getPosition();
    }

    @Override
    public void setSpawnAnimation() {
        this.setCurrentAnimation(new DisplaceAnimation(this.getPosition(), this.spawnPos));
    }
}
