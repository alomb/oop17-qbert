package qbert.model.components;

/**
 * Component managing informations about player points.
 */
public class PointComponent {

    /**
     * Points gained on {@link Snake} fall after following the player jumping over a {@link Disk}.
     */
    public static final int COILY_FALL_SCORE = 500;

    /**
     * Points gained after landing on a {@link Tile} changing its color to the intermediate one.
     */
    public static final int INTERMEDIATE_COLOR_SCORE = 15;

    /**
     * Points gained after landing on a {@link Tile} changing its color to the target one.
     */
    public static final int TARGET_COLOR_SCORE = 25;

    /**
     * Points gained after stepping on {@link GreenBall}.
     */
    public static final int KILL_GREEN_BALL_SCORE = 100;

    /**
     * Points gained after stepping on {@link SamAndSlick}.
     */
    public static final int KILL_SAM_SLICK_SCORE = 300;

    /**
     * Points gained for every {@link Disk} left after the end of a round.
     */
    public static final int UNUSED_DISK_SCORE = 50;

    /**
     * Points gained every round ending.
     */
    public static final int ROUND_SCORE = 1500;

    private int points;

    /**
     * Constructor of PointComponent class.
     */
    public PointComponent() {
        this.points = 0;
    }

    /**
     * Increments the current points value of the given amount.
     * @param amount Number of points gained
     */
    public void score(final int amount) {
        this.points += amount;
    }

    /**
     * Gets number of points.
     * @return Current player points value
     */
    public int getPoints() {
        return this.points;
    }
}
