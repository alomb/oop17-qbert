package qbert.model;

/**
 * The class containing the information of a specific character.
 */
public final class EnemyInfo {

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
    public EnemyInfo(final float speed, final int quantity, final int spawningTime, final int standingTime) {
        this.speed = speed;
        this.currentQuantity = 0;
        this.totalQuantity = quantity;
        this.spawningTime = spawningTime;
        this.standingTime = standingTime;

        this.elapsedTime = 0;
    }

    /**
     * @return the {@link Character} movement speed
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * @return the current amount of a specific {@link Character} on the map
     */
    public int getCurrentQuantity() {
        return this.currentQuantity;
    }

    /**
     * @return the maximum amount of a specific {@link Character} on the map
     */
    public int getTotalQuantity() {
        return this.totalQuantity;
    }

    /**
     * @return the spawning frequency of the character
     */
    public int getSpawningTime() {
        return this.spawningTime;
    }

    /**
     * @return the time passed on standing state
     */
    public int getStandingTime() {
        return this.standingTime;
    }

    /**
     * @return the timer of the specific spawned {@link Character}
     */
    public int getElapsedTime() {
        return this.elapsedTime;
    }

    /**
     * This method increments the current amount of a specific {@link Character} on the map.
     */
    public void incCurrentQuantity() {
        this.currentQuantity++;
    }

    /**
     * This method decrements the current amount of a specific {@link Character} on the map.
     */
    public void decCurrentQuantity() {
        this.currentQuantity--;
    }

    /**
     * This method resets the timer of a specific {@Character}.
     */
    public void resetElapsedTime() {
        this.elapsedTime = 0;
    }

    /**
     * @param dt the time passed since the last game cycle
     */
    public void incElapsedTime(final float dt) {
        this.elapsedTime += dt;
    }
}

