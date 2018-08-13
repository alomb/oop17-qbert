package qbert.model.components;

import qbert.model.characters.Player;

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

    /**
     * Amount of points to be scored to get the first bonus life.
     */
    public static final int INITIAL_LIFE_THRESHOLD = 8000;

    /**
     * Amount of points to be scored to get the bonus lives after the first.
     */
    public static final int STANDARD_LIFE_THRESHOLD = 14000;

    private int points;
    private int lifeThreshold;

    /**
     * Constructor of PointComponent class.
     * @param score Initial score
     */
    public PointComponent(final int score) {
        this.points = score;
        this.lifeThreshold = PointComponent.INITIAL_LIFE_THRESHOLD;
        while (this.lifeThreshold <= this.points) {
            this.lifeThreshold += PointComponent.STANDARD_LIFE_THRESHOLD;
        }
    }

    /**
     * Increments the current points value of the given amount.
     * @param amount Number of points gained
     * @param qbert Instance of the player
     */
    public void score(final int amount, final Player qbert) {
        points += amount;
        if (points - amount < lifeThreshold && points >= lifeThreshold) {
            lifeThreshold += PointComponent.STANDARD_LIFE_THRESHOLD;
            qbert.gainLife();
        }
    }

    /**
     * @return Current player points value
     */
    public int getPoints() {
        return this.points;
    }
}
