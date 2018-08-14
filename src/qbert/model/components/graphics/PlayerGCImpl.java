package qbert.model.components.graphics;

import qbert.model.sprites.OneSideCharacterSprites;
import qbert.model.sprites.SpecialCharacterSprites;
import qbert.model.utilities.Position2D;
import qbert.view.characters.DisplaceAnimation;
import qbert.view.characters.ComposedAnimation;

/**
 * GC stands for graphic component, this implementation is used to manage the {@link Player}.
 */
public class PlayerGCImpl extends DownUpwardCharacterGCImpl implements PlayerGC {

    private final SpecialCharacterSprites specialSprites;
    private final Position2D firstSpawningPosition;

    /**
     * @param frontSprites the container of the {@link Character} front sprites
     * @param backSprites the container of the {@link Character} back sprites
     * @param specialSprites the container of the {@link Character} special sprites
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public PlayerGCImpl(final OneSideCharacterSprites frontSprites, final OneSideCharacterSprites backSprites, final SpecialCharacterSprites specialSprites, final Position2D startSpritePos) {
        super(frontSprites, backSprites, startSpritePos);
        this.specialSprites = specialSprites;
        this.firstSpawningPosition = new Position2D(startSpritePos);
    }

    @Override
    public final void setDeathAnimation() {
        this.setPosition(new Position2D(this.getPosition().getX(), 
                this.getPosition().getY() - Math.abs(this.getSpriteHeight() - this.specialSprites.getDeathSprite().getHeight())));
        this.setSprite(this.specialSprites.getDeathSprite());
        if (this.isRight()) {
            this.flipOnYImage();
        }
    }

    @Override
    public final void setSpawnAnimation() {
        this.setSprite(this.getFrontSprites().getStandSprite());
        this.setRight(true);
        this.setFront(true);
        this.setCurrentAnimation(new DisplaceAnimation(this.getPosition(), this.getSpawnPosition()));
    }

    @Override
    public final void setOnDiskAnimation() {
        this.setSprite(this.specialSprites.getOnDiskSprite());
        final Position2D intermediatePos = new Position2D(this.firstSpawningPosition.getX(), this.firstSpawningPosition.getY() - this.getSpriteHeight() * 2);
        this.setCurrentAnimation(new ComposedAnimation.OnDisk(this.getPosition(), intermediatePos, new Position2D(this.firstSpawningPosition)));
    }
}
