import student.TestCase;

/**
 * This class is used to perform testing on CommandProcessor.java class
 * 
 * 
 * @author Sadath
 * 
 * @version 2024-02-10
 */

public class CommandProcessorTest extends TestCase {

    /**
     * Creates an object of CommandProcessor class
     */
    private CommandProcessor cmd = new CommandProcessor();

    /**
     * Tests the output for a valid rectangle insertion
     */
    public void testValidInsert1() {
        String line = "insert a 1 2 3 4";
        cmd.processor(line);
        String output = systemOut().getHistory();
        String expected = "Rectangle inserted: a 1 2 3 4\n";
        assertFuzzyEquals(expected, output);
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "SkipList size is: 1"));
        // systemOut().clearHistory();
    }


    /**
     * Tests the output for a Invalid rectangle (with negative coordinates)
     * insertion
     */
    public void testInvalidInsert1() {
        String line = "insert a -1 2 3 4";
        cmd.processor(line);
        String output = systemOut().getHistory();
        String expected = "Rectangle rejected: a -1 2 3 4\n";
        assertFuzzyEquals(expected, output);
        // systemOut().clearHistory();
    }


    /**
     * Tests the output for a valid rectangle coordinates insertion
     */
    public void testProcessorWithValidCommands() {
        cmd.processor("insert k 7 8 9 10");
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle inserted: k 7 8 9 10", output);
        // systemOut().clearHistory();
    }


    /**
     * Tests the output for an Unrecognized command
     */
    public void testProcessorWithInvalidCommands() {
        cmd.processor("scan");
        String output = systemOut().getHistory();
        assertFuzzyEquals("Unrecognized command.", output);
        // systemOut().clearHistory();
    }


    /**
     * Tests the output for a Dump() with non-empty Skiplist
     */
    public void testDump1() {
        cmd.processor("insert r1 1 2 3 4");
        cmd.processor("insert r2 6 5 6 8");
        cmd.processor("insert r3 -1 5 41 89");
        cmd.processor("insert r12 4 3 2 6");
        cmd.processor("");
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "Value (r1, 1, 2, 3, 4)"));
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "SkipList size is: 3"));
    }


    /**
     * Tests the output for a Dump() with an empty Skiplist
     */
    public void testDumpEmpty() {
        cmd.processor("insert r3 -1 5 41 89");
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "depth 1, Value (null)"));
    }


    /**
     * Tests the output for a Search() with valid input
     */
    public void testSearch1() {
        cmd.processor("insert r1 1 2 3 4");
        cmd.processor("insert r2 6 5 6 8");
        cmd.processor("insert r1 1 5 41 89");
        cmd.processor("insert r12 4 3 2 6");
        cmd.processor("insert r2 7 9 10 12");
        cmd.processor("search r1");
        assertTrue(systemOut().getHistory().toString().contains(
            "(r1, 1, 2, 3, 4)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(r1, 1, 5, 41, 89)"));
    }


    /**
     * Tests the output for a Search() with valid input
     */
    public void testSearch2() {
        cmd.processor("insert r1 1 2 3 4");
        cmd.processor("insert r2 6 5 6 8");
        cmd.processor("insert r1 1 5 41 89");
        cmd.processor("insert r12 4 3 2 6");
        cmd.processor("insert r2 7 9 10 12");
        cmd.processor("search r2");
        assertTrue(systemOut().getHistory().toString().contains(
            "(r2, 6, 5, 6, 8)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(r2, 7, 9, 10, 12)"));
    }


    /**
     * Tests the output for a Search() with Invalid input
     */
    public void testSearch3() {
        cmd.processor("insert r1 1 2 3 4");
        cmd.processor("insert r2 6 5 6 8");
        cmd.processor("insert r1 1 5 41 89");
        cmd.processor("insert r12 4 3 2 6");
        cmd.processor("insert r2 7 9 10 12");
        cmd.processor("search r5");
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle not found: (r5)"));
    }


    /**
     * Tests the output for a Remove by name with valid input
     */
    public void testRemoveByName() {
        cmd.processor("insert r1 1 2 3 4");
        cmd.processor("insert r2 6 5 6 8");
        cmd.processor("insert r4 1 5 41 89");
        cmd.processor("insert r12 4 3 2 6");
        cmd.processor("insert r5 7 9 10 12");
        cmd.processor("remove r4");
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle removed: (r4, 1, 5, 41, 89)"));
        cmd.processor("dump");
    }


    /**
     * Tests the output for a Remove last by name with valid input
     */
    public void testRemoveByNameLast() {
        cmd.processor("insert r1 1 2 3 4");
        cmd.processor("insert r2 6 5 6 8");
        cmd.processor("insert r4 1 5 41 89");
        cmd.processor("insert r12 4 3 2 6");
        cmd.processor("insert r5 7 9 10 12");
        cmd.processor("remove r5");
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle removed: (r5, 7, 9, 10, 12)"));
        cmd.processor("dump");
    }


    /**
     * Tests the output for a Remove by name with Invalid input
     */
    public void testRemoveByName2() {
        cmd.processor("insert r1 1 2 3 4");
        cmd.processor("insert r2 6 5 6 8");
        cmd.processor("insert r4 1 5 41 89");
        cmd.processor("insert r12 4 3 2 6");
        cmd.processor("insert r5 7 9 10 12");
        cmd.processor("remove a");
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle not removed: a"));
    }


    /**
     * Tests the output for a Remove by vale with Valid input
     */
    public void testRemoveByVal1() {
        cmd.processor("insert r1 1 2 3 4");
        cmd.processor("insert r2 6 5 6 8");
        cmd.processor("insert r4 1 5 41 89");
        cmd.processor("insert r12 4 3 2 6");
        cmd.processor("insert r13 14 3 2 6");
        cmd.processor("insert r16 42 3 2 6");
        cmd.processor("insert r25 34 3 2 6");
        cmd.processor("insert r12 44 3 2 6");
        cmd.processor("insert r5 7 9 10 12");
        cmd.processor("remove 7 9 10 12");
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle removed: (r5, 7, 9, 10, 12)"));
    }


    /**
     * Tests the output for a Remove by value with Valid
     * but Non-existing input
     */
    public void testRemoveByVal2() {
        cmd.processor("insert k1 1 2 3 4");
        cmd.processor("insert k2 6 5 6 8");
        cmd.processor("insert k4 1 5 41 89");
        cmd.processor("insert k12 4 3 2 6");
        cmd.processor("insert k5 7 9 10 12");
        cmd.processor("remove 3 5 4 8");
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle not found: (3, 5, 4, 8)"));
    }


    /**
     * Tests the output for a Remove by value with Invalid
     * input
     */
    public void testRemoveByVal3() {
        cmd.processor("insert q1 1 2 3 4");
        cmd.processor("insert q2 6 5 6 8");
        cmd.processor("insert q4 1 5 41 89");
        cmd.processor("insert q12 4 3 2 6");
        cmd.processor("insert q5 7 9 10 12");
        cmd.processor("remove 1 2 3 -4");
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle rejected: (1, 2, 3, -4)"));
    }


    /**
     * Tests the output for a Remove by value with valid
     * input but from an empty skiplist
     */
    public void testRemoveByVal4() {
        cmd.processor("insert r1 1 2 3 -4");
        cmd.processor("insert r2 6 5 -6 8");
        cmd.processor("insert r4 1 -5 41 89");
        cmd.processor("insert r12 -4 3 2 6");
        cmd.processor("insert r5 7 9 1026 12");
        cmd.processor("remove 7 9 10 12");
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle not found: (7, 9, 10, 12)"));
    }


    /**
     * Tests the output for a Remove by value with just
     * 3 coordinates
     */
    public void testRemoveByVal5() {
        cmd.processor("insert r1 1 2 3 -4");
        cmd.processor("insert r2 6 5 -6 8");
        cmd.processor("insert r4 1 -5 41 89");
        cmd.processor("insert r12 -4 3 2 6");
        cmd.processor("insert r5 7 9 1026 12");
        cmd.processor("remove 7 9 10");
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "Unrecognized command."));
    }


    /**
     * Tests the output for a Remove by value with
     * empty SkipList
     */
    public void testRemoveByVal6() {
        cmd.processor("remove 7 9 10 1");
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle not found: (7, 9, 10, 1)"));
    }


    /**
     * Tests the output for a SkipList.size()
     * after remove by name
     */
    public void testSkiplistSize() {
        SkipList<String, Rectangle> list = new SkipList<>();
        Rectangle r1 = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> pair1 = new KVPair<>("a", r1);
        list.insert(pair1);
        Rectangle r2 = new Rectangle(2, 3, 4, 5);
        KVPair<String, Rectangle> pair2 = new KVPair<>("b", r2);
        list.insert(pair2);
        assertEquals(2, list.size());
    }


    /**
     * Tests the output for a SkipList.size()
     * after remove by value
     */
    public void testSkiplistSize2() {
        SkipList<String, Rectangle> list = new SkipList<>();
        Rectangle r1 = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> pair1 = new KVPair<>("a", r1);
        list.insert(pair1);
        Rectangle r2 = new Rectangle(2, 3, 4, 5);
        KVPair<String, Rectangle> pair2 = new KVPair<>("b", r2);
        list.insert(pair2);
        list.insert(pair1);
        list.insert(pair2);
        list.remove("b");
        assertEquals(3, list.size());
        list.removeByValue(r1);
        assertEquals(2, list.size());
    }


    /**
     * Tests the output for a region search
     * with valid intersecting values
     */
    public void testRegionSearch1() {
        cmd.processor("insert k1 1 1 10 10");
        cmd.processor("insert k2 2 3 4 5");
        cmd.processor("regionsearch -2 -3 8 8");
        assertTrue(systemOut().getHistory().toString().contains(
            "(k1, 1, 1, 10, 10)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(k2, 2, 3, 4, 5)"));
    }


    /**
     * Tests the output for a region search
     * with valid intersecting values
     * inscribed rectangles
     */
    public void testRegionSearch11() {
        cmd.processor("insert k1 2 2 1 1");
        cmd.processor("insert k2 0 0 7 7");
        cmd.processor("regionsearch 1 1 3 3");
        assertTrue(systemOut().getHistory().toString().contains(
            "(k1, 2, 2, 1, 1)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(k2, 0, 0, 7, 7)"));
    }


    /**
     * Tests the output for a region search
     * with valid intersecting values
     * inscribed rectangles
     */
    public void testRegionSearch12() {
        cmd.processor("insert k1 3 3 2 2");
        cmd.processor("insert k2 4 0 7 7");
        cmd.processor("regionsearch 4 4 2 2");
        assertTrue(systemOut().getHistory().toString().contains(
            "(k1, 3, 3, 2, 2)"));
// assertTrue(systemOut().getHistory().toString().contains(
// "(k2, 0, 0, 7, 7)"));
// assertTrue(systemOut().getHistory().toString().contains(
// "(k3, 1, 1, 1, 1)"));
    }


    /**
     * Tests the output for a region search
     * with valid non-intersecting values
     */
    public void testRegionSearch2() {
        cmd.processor("insert k1 1 8 10 10");
        cmd.processor("insert k2 7 3 4 5");
        cmd.processor("regionsearch -2 -3 8 8");
        assertFalse(systemOut().getHistory().toString().contains(
            "(k1, 1, 8, 10, 10)"));
        assertFalse(systemOut().getHistory().toString().contains(
            "(k2, 7, 3, 4, 5)"));
    }


    /**
     * Tests the output for a region search
     * with valid non-intersecting values
     */
    public void testRegionSearch4() {
        cmd.processor("insert k1 4 4 1 1");
        cmd.processor("insert k2 5 4 1 1");
        cmd.processor("regionsearch 5 5 3 3");
        assertFalse(systemOut().getHistory().toString().contains(
            "(k1, 9, 9, 1, 1)"));
        assertFalse(systemOut().getHistory().toString().contains(
            "(k2, 5, 4, 1, 1)"));
    }


    /**
     * Tests the output for a region search
     * with Invalid values
     */
    public void testRegionSearch3() {
        cmd.processor("insert k1 1 1 10 10");
        cmd.processor("insert k2 2 3 4 5");
        cmd.processor("regionsearch -2 -3 0 8");
        cmd.processor("regionsearch -2 -3 5 -1");
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle rejected: (-2, -3, 0, 8)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "Rectangle rejected: (-2, -3, 5, -1)"));
    }


    /**
     * Tests the output for a region search
     * with valid values
     */
    public void testRegionSearch5() {
        cmd.processor("insert k1 6 5 1 2");
        cmd.processor("insert k2 2 3 4 5");
        cmd.processor("regionsearch 6 3 1 4");
        cmd.processor("regionsearch -2 -3 5 -1");
        assertTrue(systemOut().getHistory().toString().contains(
            "(k1, 6, 5, 1, 2)"));
    }


    /**
     * Tests the output for a Intersections
     * with possible intersections
     */
    public void testIntersection() {
        cmd.processor("insert k1 1 1 10 10");
        cmd.processor("insert k2 2 3 4 5");
        cmd.processor("insert k3 2 2 1 1");
        cmd.processor("intersections");

    }


    /**
     * Tests the output for a Intersections
     * with possible intersections
     */
    public void testIntersection2() {
        cmd.processor("insert q1 2 1 3 1");
        cmd.processor("insert q2 1 1 2 1");
        cmd.processor("intersections");
        assertTrue(systemOut().getHistory().toString().contains(
            "Intersection pairs:"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(q1, 2, 1, 3, 1) | (q2, 1, 1, 2, 1)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(q2, 1, 1, 2, 1) | (q1, 2, 1, 3, 1)"));
    }


    /**
     * Tests the output for a Intersections
     * with possible intersections
     */
    public void testIntersection3() {
        cmd.processor("insert q1 3 2 1 1");
        cmd.processor("insert q2 0 2 2 1");
        cmd.processor("insert q3 1 2 3 1");
        cmd.processor("intersections");
        assertFalse(systemOut().getHistory().toString().contains(
            "(q1, 3, 2, 1, 1) | (q2, 0, 2, 2, 1)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(q2, 0, 2, 2, 1) | (q3, 1, 2, 3, 1)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(q1, 3, 2, 1, 1) | (q3, 1, 2, 3, 1)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(q3, 1, 2, 3, 1) | (q2, 0, 2, 2, 1)"));
        assertFalse(systemOut().getHistory().toString().contains(
            "(q2, 0, 2, 2, 1) | (q1, 3, 2, 1, 1)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(q3, 1, 2, 3, 1) | (q1, 3, 2, 1, 1)"));
    }


    /**
     * Tests the output for a Intersections
     * with possible intersections
     */
    public void testIntersection4() {
        cmd.processor("insert q1 1 4 1 1");
        cmd.processor("insert q2 1 2 1 3");
        // cmd.processor("insert q3 1 2 3 1");
        cmd.processor("intersections");
        assertTrue(systemOut().getHistory().toString().contains(
            "(q1, 1, 4, 1, 1) | (q2, 1, 2, 1, 3)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(q2, 1, 2, 1, 3) | (q1, 1, 4, 1, 1)"));
    }


    /**
     * Tests the output for a Intersections
     * with same name, coordinates
     */
    public void testIntersection5() {
        cmd.processor("insert q1 1 2 1 2");
        cmd.processor("insert q1 1 2 1 2");
        cmd.processor("insert q2 4 3 3 4");
        cmd.processor("insert q3 4 3 3 4");
        cmd.processor("intersections");
        assertTrue(systemOut().getHistory().toString().contains(
            "(q1, 1, 2, 1, 2) | (q1, 1, 2, 1, 2)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(q2, 4, 3, 3, 4) | (q3, 4, 3, 3, 4)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "(q3, 4, 3, 3, 4) | (q2, 4, 3, 3, 4)"));
        assertFalse(systemOut().getHistory().toString().contains(
            "(q2, 4, 3, 3, 4) | (q2, 4, 3, 3, 4)"));
        assertFalse(systemOut().getHistory().toString().contains(
            "(q3, 4, 3, 3, 4) | (q3, 4, 3, 3, 4)"));

    }

}
