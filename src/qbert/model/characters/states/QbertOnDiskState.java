package qbert.model.characters.states;

import qbert.model.characters.Player;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * The {@link CharacterState} used to manage {@link Player} when it's surfing the disk.
 */
public class QbertOnDiskState extends WaitAnimationState {

    /**
     * @param qbert the {@link Player} linked to this state.
     */
    public QbertOnDiskState(final Player qbert) {
        super(qbert);
        qbert.setCurrentPosition(new Position2D(qbert.getNextPosition()));
        qbert.setNextPosition(Dimensions.getSpawningLogQBert());
        qbert.getPlayerGraphicComponent().setOnDiskAnimation();
    }

    @Override
    public final void conclude() {
        this.getCharacter().getGraphicComponent().setSpawnPosition(Dimensions.getSpawningQBert());
        this.getCharacter().setCurrentState(new SpawnState(this.getCharacter()));
    }

}
