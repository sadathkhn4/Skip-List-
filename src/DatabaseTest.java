import student.TestCase;

/**
 * This class is used to perform testing on Database class
 * 
 * 
 * @author Sadath
 * 
 * @version 2024-02-10
 */

public class DatabaseTest extends TestCase {
    /**
     * An instance of Database class
     */
    private Database data = new Database();
    /**
     * An instance of Rectangle class
     */
    private Rectangle rect;
    /**
     * An instance of KVPair class
     */
    private KVPair<String, Rectangle> pair;

    /**
     * Used to test the insertion of Invalid rectangle
     */
    public void testInvalidInsert1() {
        rect = new Rectangle(-1, 2, 3, 4);
        pair = new KVPair<>("a", rect);
        data.insert(pair);
        String output = systemOut().getHistory();
        String expected = "Rectangle rejected: a -1 2 3 4\n";
        assertFuzzyEquals(expected, output);
        // systemOut().clearHistory();
    }


    /**
     * Used to test the insertion of Valid rectangle
     */
    public void testValidInsert1() {
        rect = new Rectangle(3, 4, 5, 6);
        pair = new KVPair<>("a", rect);
        data.insert(pair);
        String output = systemOut().getHistory();
        String expected = "Rectangle inserted: a 3 4 5 6\n";
        assertFuzzyEquals(expected, output);
        // systemOut().clearHistory();
    }

}
