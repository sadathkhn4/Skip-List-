import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * Also note that the Database class will have a clearer role in Project2,
 * where we will have two data structures. The Database class will then
 * determine
 * which command should be directed to which data structure.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    // This is an Iterator object over the SkipList to loop through it from
    // outside the class.
    // You will need to define an extra Iterator for the intersections method.
    // private Iterator<KVPair<String, Rectangle>> itr1;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * add the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        // Delegates the decision mostly to SkipList, only
        // writing the correct message to the console from
        // that

        String name = pair.getKey();
        int x = pair.getValue().getxCoordinate();
        int y = pair.getValue().getyCoordinate();
        int w = pair.getValue().getWidth();
        int h = pair.getValue().getHeight();

        if (!pair.getValue().isInvalid()) {
            list.insert(pair);
            System.out.println("Rectangle inserted: " + name + " " + x + " " + y
                + " " + w + " " + h);
        }
        else {
            System.out.println("Rectangle rejected: " + name + " " + x + " " + y
                + " " + w + " " + h);
        }

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     *
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        KVPair<String, Rectangle> removedPair = list.remove(name);
        if (removedPair == null) {
            System.out.println("Rectangle not removed: " + name);
        }
        else {
            System.out.println("Rectangle removed: (" + name + ", "
                + removedPair.getValue().toString() + ")");
        }
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     *
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {
        Rectangle rect = new Rectangle(x, y, w, h);
        if (!rect.isInvalid()) {
            KVPair<String, Rectangle> removedPair = list.removeByValue(rect);
            if (removedPair != null) {
                String name = removedPair.getKey();
                System.out.print("Rectangle removed: (");
                System.out.println(name + ", " + rect.toString() + ")");
            }
            else {
                System.out.print("Rectangle not found: (");
                System.out.println(rect.toString() + ")");
            }
        }

        else {
            System.out.print("Rectangle rejected: (");
            System.out.println(rect.toString() + ")");
        }
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region.
     *
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        int x1 = x;
        int y1 = y;
        int w1 = w;
        int h1 = h;
        Rectangle rec1 = new Rectangle(x1, y1, w1, h1);

        if (w > 0 && h > 0) {
            System.out.println("Rectangles intersecting region (" + rec1
                .toString() + "):");

            SkipList<String, Rectangle>.SkipNode p = list.getHead();
            int level = list.getLevel();
            int baseLevel = 0;
            while (p != null) { // loop through skiplist
                if (p.getForward()[baseLevel] != null) {
                    KVPair<String, Rectangle> currentPair;
                    currentPair = p.getForward()[baseLevel].element();
                    Rectangle rec2 = currentPair.getValue();
                    String name2 = currentPair.getKey();
                    int x2 = rec2.getxCoordinate();
                    int y2 = rec2.getyCoordinate();
                    int w2 = rec2.getWidth();
                    int h2 = rec2.getHeight();

                    if (((x2 <= x1) && (x1 < x2 + w2)) || ((x1 <= x2)
                        && (x2 < x1 + w1))) { // condition for overlap on x-axis

                        if (((y2 <= y1) && (y1 < y2 + h2)) || ((y1 <= y2)
                            && (y2 < y1 + h1))) { // condition for overlap on
                                                  // y-axis

                            System.out.println("(" + name2 + ", " + rec2
                                .toString() + ")");

                        }
                    }
                }
                p = p.getForward()[baseLevel];
            }

        }
        else {
            System.out.println("Rectangle rejected: (" + rec1.toString() + ")");
        }
    }


    /**
     * Creates overlap pairs of rectangles
     * 
     * @param name
     *            name of the rectangle-1
     * @param x
     *            x-Coordinate of the rectangle-1
     * @param y
     *            y-Coordinate of the rectangle-1
     * @param w
     *            width of the rectangle-1
     * @param h
     *            height of the rectangle-1
     */
    public void overlaps(String name, int x, int y, int w, int h) {
        int x1 = x;
        int y1 = y;
        int w1 = w;
        int h1 = h;
        String name1 = name;
        Rectangle rec1 = new Rectangle(x1, y1, w1, h1);
        SkipList<String, Rectangle>.SkipNode p = list.getHead();
        int level = list.getLevel();
        int baseLevel = 0;
        int flag = 1;
        while (p != null) { // loop through the skiplist to get overlaps
            if (p.getForward()[baseLevel] != null) {
                KVPair<String, Rectangle> currentPair;
                currentPair = p.getForward()[baseLevel].element();
                Rectangle rec2 = currentPair.getValue();
                String name2 = currentPair.getKey();
                int x2 = rec2.getxCoordinate();
                int y2 = rec2.getyCoordinate();
                int w2 = rec2.getWidth();
                int h2 = rec2.getHeight();
                
                if (((x2 <= x1) && (x1 < x2 + w2)) || ((x1 <= x2) && (x2 < x1
                    + w1))) { // condition for overlap on x-axis

                    if (((y2 <= y1) && (y1 < y2 + h2)) || ((y1 <= y2)
                        && (y2 < y1 + h1))) { // condition for overlap on y-axis

                        if ((name1.compareTo(name2) == 0) && (rec1.toString()
                            .compareTo(rec2.toString()) == 0) && (flag == 1)) {
                            flag = 0;
                        }
                        else {
                            System.out.print("(" + name1 + ", " + rec1
                                .toString() + ") | ");
                            System.out.println("(" + name2 + ", " + rec2
                                .toString() + ")");
                        }

                    }
                }
            }
            p = p.getForward()[baseLevel];
        }

    }


    /**
     * Prints out all the rectangles that intersect each other. Note that
     * it is better not to implement an intersections method in the SkipList
     * class
     * as the SkipList needs to be agnostic about the fact that it is storing
     * Rectangles.
     */
    public void intersections() {

        System.out.println("Intersection pairs:");
        SkipList<String, Rectangle>.SkipNode k = list.getHead();
        int level = list.getLevel();
        int baseLevel = 0;
        while (k != null) { // fix Rectangle-1
            if (k.getForward()[baseLevel] != null) {
                KVPair<String, Rectangle> currentPair;
                currentPair = k.getForward()[baseLevel].element();
                Rectangle rec1 = currentPair.getValue();
                String name1 = currentPair.getKey();
                int x1 = rec1.getxCoordinate();
                int y1 = rec1.getyCoordinate();
                int w1 = rec1.getWidth();
                int h1 = rec1.getHeight();

                overlaps(name1, x1, y1, w1, h1); // Rectangle-2 loop
            }
            k = k.getForward()[baseLevel];
        }

    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     *
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        ArrayList<KVPair<String, Rectangle>> result = list.search(name);
        if (result != null) {
            System.out.println("Rectangles found:");
            for (KVPair<String, Rectangle> pair : result) {
                String key = pair.getKey(); // Get the key (String)
                Rectangle rect = pair.getValue(); // Get the value (Rectangle)
                System.out.println("(" + name + ", " + rect.toString() + ")");
            }
        }

        else {
            System.out.println("Rectangle not found: (" + name + ")");
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }

}
