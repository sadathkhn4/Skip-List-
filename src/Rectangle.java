/**
 * This class holds the coordinates and dimensions of a rectangle and methods to
 * check if it intersects or has the same coordinates as an other rectangle.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Rectangle {
    // the x coordinate of the rectangle
    private int xCoordinate;
    // the y coordinate of the rectangle
    private int yCoordinate;
    // the distance from the x coordinate the rectangle spans
    private int width;
    // the distance from the y coordinate the rectangle spans
    private int height;

    /**
     * Creates an object with the values to the parameters given in the
     * xCoordinate, yCoordinate, width, height
     * 
     * @param x
     *            x-coordinate of the rectangle
     * @param y
     *            y-coordinate of the rectangle
     * @param w
     *            width of the rectangle
     * @param h
     *            height of the rectangle
     */
    public Rectangle(int x, int y, int w, int h) {
        xCoordinate = x;
        yCoordinate = y;
        width = w;
        height = h;
    }


    /**
     * Getter for the x coordinate
     *
     * @return the x coordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }


    /**
     * Getter for the y coordinate
     *
     * @return the y coordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }


    /**
     * Getter for the width
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }


    /**
     * Getter for the height
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

// /**
// * Checks if the invoking rectangle intersects with rec.
// *
// * @param r2
// * Rectangle parameter
// * @return true if the rectangle intersects with rec, false if not
// */
// public boolean intersect(Rectangle r2) {
// return false;
//
// }


    /**
     * Checks, if the invoking rectangle has the same coordinates as rec.
     * 
     * @param rec
     *            the rectangle parameter
     * @return true if the rectangle has the same coordinates as rec, false if
     *         not
     */
    public boolean equals(Object rec) {
        Rectangle r = (Rectangle)rec;
        int x2 = r.getxCoordinate();
        int y2 = r.getyCoordinate();
        int w2 = r.getWidth();
        int h2 = r.getHeight();

        if (x2 == xCoordinate) {
            if (y2 == yCoordinate) {
                if (w2 == width) {
                    return h2 == height;
                }
            }
        }
        return false;
    }


    /**
     * Outputs a human readable string with information about the rectangle
     * which includes the x and y coordinate and its height and width
     * 
     * @return a human readable string containing information about the
     *         rectangle
     */
    public String toString() {
        return (xCoordinate + ", " + yCoordinate + ", " + width + ", "
            + height);
    }


    /**
     * Checks if the rectangle has invalid parameters
     * 
     * @return true if the rectangle has invalid parameters, false if not
     */
    public boolean isInvalid() {
// return (xCoordinate < 0 ||
// yCoordinate < 0 ||
// xCoordinate > 1024 ||
// yCoordinate > 1024 ||
// height <= 0 || width <= 0 ||
// xCoordinate + width > 1024 ||
// yCoordinate + height > 1024);

// return true;
// else if (height <= 0 || width <= 0)
// return true;
// else if (xCoordinate + width > 1024 )
// return true;
// // else if (yCoordinate + height > 1024)
// // return true;
// return (yCoordinate + height > 1024);
//
// // return false;
        if (xCoordinate + width > 1024) {
            return true;
        }
        if (yCoordinate + height > 1024) {
            return true;
        }
        if (xCoordinate < 0) {
            return true;
        }
        if (yCoordinate < 0) {
            return true;
        }
// if (xCoordinate > 1024) {
// return true;
// }
// if (yCoordinate > 1024) {
// return true;
// }
        if (height <= 0) {
            return true;
        }
        return (width <= 0);

    }
}
