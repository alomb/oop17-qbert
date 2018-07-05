package qbert.model.components;

/**
 * Component managing informations about player points
 */
public class PointComponent {

    public static final int COILY_FALL_SCORE = 500;
    public static final int INTERMEDIATE_COLOR_SCORE = 15;
    public static final int TARGET_COLOR_SCORE = 25;
    public static final int KILL_GREEN_BALL_SCORE = 100;
    public static final int KILL_SAM_SLICK_SCORE = 300;
    public static final int UNUSED_DISK_SCORE = 50;

    private int points;

    /**
     * Constructor of PointComponent class
     */
    public PointComponent() {
        this.points = 0;
    }

    /**
     * Increments the current points value of the given amount
     * @param amount Number of points gained
     */
    public void score(int amount) {
        this.points += amount;
    }

    /**
     * Gets number of points
     * @return Current player points value
     */
    public int getPoints() {
        return this.points;
    }
}
