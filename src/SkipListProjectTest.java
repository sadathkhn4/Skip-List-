import student.TestCase;

/**
 * This class is used to perform testing on SkipListProject class
 * 
 * 
 * @author Sadath
 * 
 * @version 2024-02-10
 */

public class SkipListProjectTest extends TestCase {

    /**
     * USed to test the Main() function with a valid file name parameter
     */
    public void testMainValid() {
        SkipListProject.main(new String[] { "src/validInput.txt" });
        assertTrue(systemOut().getHistory().toString().contains("alpha"));
        assertTrue(systemOut().getHistory().toString().contains("gamma"));

    }


    /**
     * Used to test the Main() function with an Invalid file name parameter
     */
    public void testMainInvalid() {
        SkipListProject.main(new String[] { "doesNotExist.txt" });
        assertFuzzyEquals(systemOut().getHistory(), "Invalid file");

    }

}
