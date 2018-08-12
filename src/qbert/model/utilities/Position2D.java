package qbert.model.utilities;

/**
 * A Class representing a 2 dimensional position.
 */
public class Position2D {
    /**
     * X coordinate.
     */
    private int x;
    /**
     * Y coordinate.
     */
    private int y;

    /**
     * Constructor receiving the two coordinate.
     * @param x the horizontal coordinate
     * @param y the vertical coordinate
     */
    public Position2D(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor receiving a new {@link Position2D}.
     * @param position the new {@link Position2D}
     */
    public Position2D(final Position2D position) {
        this.x = position.x;
        this.y = position.y;
    }

    /**
     * @return the x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * @param d the new horizontal coordinate
     */
    public void setX(final int d) {
        this.x = d;
    }

    /**
     * @return the y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * @param y the new vertical coordinate
     */
    public void setY(final int y) {
        this.y = y;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position2D other = (Position2D) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        } else if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
            return true;
    }

    /**
     * @return the string representation of the {@link Position2D}.
     */
    public String toString() {
        return "P2d(" + x + "," + y + ")";
    }

    /**
     * @param other other {@link Position2D}
     * @return the distance from two points
     */
    public int distance(final Position2D other) {
        return (int) Math.sqrt((other.getX() - this.getX()) * (other.getX() - this.getX())
                + (other.getY() - this.getY()) * (other.getY() - this.getY()));
    }

    /**
     * @return the vector length
     */
    public int length() {
        return (int) Math.sqrt((this.getX() * this.getX()) + (this.getY() * this.getY()));
    }

    /**
     * @param other the vector to sum
     */
    public void vectorSum(final Position2D other) {
        this.setX(this.getX() + other.getX());
        this.setY(this.getY() + other.getY());
    }

    /**
     * @param scalar the scalar multiplied to the vector
     */
    public void scalarMul(final float scalar) {
        this.setX((int) (this.getX() * scalar));
        this.setY((int) (this.getY() * scalar));
    }
}
