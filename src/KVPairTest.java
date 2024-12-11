import student.TestCase;

/**
 * This class is used to perform testing on KVPair class
 * 
 * 
 * @author Sadath
 * 
 * @version 2024-02-10
 */

public class KVPairTest extends TestCase {

    /**
     * Creates an object of KVPair class
     */
    private KVPair<String, String> pair;

    /**
     * Tests the conversion of KVPair
     * to String using the .toString() method
     */
    public void testToString() {
        pair = new KVPair<>("a", "apple");
        assertFuzzyEquals("(a, apple)", pair.toString());
    }

}
