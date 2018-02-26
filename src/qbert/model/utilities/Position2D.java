package qbert.model.utilities;

/**
 * A Class representing a 2 dimensional position.
 */
public class Position2D {
    /**
     * X coordinate.
     */
    private double x;
    /**
     * Y coordinate.
     */
    private double y;

    /**
     * Constructor receiving the two coordinate.
     */
    public Position2D(final double x, final double y){
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
     * @return
     */
    public double getX() {
        return this.x;
    }

    /**
     * @param x
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * @return
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param y
     */
    public void setY(final double y) {
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
}
