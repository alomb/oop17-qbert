package qbert.model.spawner;

import java.util.Random;

/**
 * The implementation of {@link EnemyInfo}.
 */
public final class EnemyInfoImpl implements EnemyInfo {

    private static final int MILLIS_OFFSET = 1500;

    private final float speed;
    private int currentQuantity;
    private final int totalQuantity;
    private final int spawningTime;
    private final int standingTime;

    private int elapsedTime;

    /**
     * @param speed the {@link Character} movement speed
     * @param quantity the amount of a particular enemy on the map at a given time
     * @param spawningTime the spawning frequency of the character
     * @param standingTime the time passed on standing state
     */
    public EnemyInfoImpl(final float speed, final int quantity, final int spawningTime, final int standingTime) {
        this.speed = speed;
        this.currentQuantity = 0;
        this.totalQuantity = quantity;
        this.spawningTime = this.addRandomness(spawningTime);
        this.standingTime = standingTime;

        this.elapsedTime = 0;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public int getCurrentQuantity() {
        return this.currentQuantity;
    }

    @Override
    public int getTotalQuantity() {
        return this.totalQuantity;
    }

    @Override
    public int getSpawningTime() {
        return this.spawningTime;
    }

    @Override
    public int getStandingTime() {
        return this.standingTime;
    }

    @Override
    public int getElapsedTime() {
        return this.elapsedTime;
    }

    @Override
    public void incCurrentQuantity() {
        this.currentQuantity++;
    }

    @Override
    public void decCurrentQuantity() {
        this.currentQuantity--;
    }

    @Override
    public void resetElapsedTime() {
        this.elapsedTime = 0;
    }

    @Override
    public void incElapsedTime(final float dt) {
        this.elapsedTime += dt;
    }

    /**
     * This method adds an offset of randomness to the spawning timing of each enemy.
     */
    private int addRandomness(final int spawningTime) {
        return (spawningTime + new Random().nextInt(EnemyInfoImpl.MILLIS_OFFSET));
    }
}

