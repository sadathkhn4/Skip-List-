import student.TestCase;

/**
 * This class is used to perform testing on Rectangle class
 * 
 * 
 * @author Sadath
 * 
 * @version 2024-02-10
 */

public class RectangleTest extends TestCase {

    // private Rectangle rectangle;
    /**
     * Validity check for a valid rectangle
     */
    public void testValidRectangle() {
        Rectangle rectangle = new Rectangle(1, 0, 2, 4);
        assertFalse(rectangle.isInvalid());
    }


    /**
     * Validity check for a valid rectangle
     */
    public void testValidRectangle2() {
        Rectangle rectangle = new Rectangle(1, 3, 2, 4);
        assertFalse(rectangle.isInvalid());
    }


    /**
     * Validity check for a Invalid
     * X coordinate
     */
    public void testInvalidXCoordinate() {
        Rectangle rectangle = new Rectangle(-1, 100, 10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * Y coordinate
     */
    public void testInvalidYCoordinate() {
        Rectangle rectangle = new Rectangle(100, -1, 10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * Width=0
     */
    public void testInvalidWidth() {
        Rectangle rectangle = new Rectangle(100, 100, 0, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * Width
     */
    public void testInvalidWidth2() {
        Rectangle rectangle = new Rectangle(100, 100, -5, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * Height=0
     */
    public void testInvalidHeight() {
        Rectangle rectangle = new Rectangle(100, 100, 10, 0);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * Height
     */
    public void testInvalidHeight2() {
        Rectangle rectangle = new Rectangle(100, 100, 10, -5);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * X coordinate + Width
     */
    public void testInvalidXCoordinateAndWidth() {
        Rectangle rectangle = new Rectangle(1025, 100, 10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * X coordinate + Width
     */
    public void testInvalidXCoordinateAndWidth1() {
        Rectangle rectangle = new Rectangle(1022, 100, 10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * Y coordinate + Height
     */
    public void testInvalidYCoordinateAndHeight() {
        Rectangle rectangle = new Rectangle(100, 1025, 10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * Y coordinate + Height
     */
    public void testInvalidYCoordinateAndHeight1() {
        Rectangle rectangle = new Rectangle(100, 1021, 10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * X coordinate + Width
     * Y coordinate + Height
     */
    public void testInvalidXCoordinateAndWidthAndYCoordinateAndHeight() {
        Rectangle rectangle = new Rectangle(1025, 1025, 10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * X, Y coordinate
     */
    public void testInvalidXYCoordinate1() {
        Rectangle rectangle = new Rectangle(-1, -1, 10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * X, Y coordinate
     */
    public void testInvalidXYCoordinate2() {
        Rectangle rectangle = new Rectangle(-1, 1025, 10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * X, Y coordinate
     */
    public void testInvalidXYCoordinate3() {
        Rectangle rectangle = new Rectangle(1026, -2, 10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Validity check for an Invalid
     * X, Y coordinate
     */
    public void testInvalidXYCoordinate6() {
        Rectangle rectangle = new Rectangle(1026, 2, -10, 10);
        assertTrue(rectangle.isInvalid());
    }


    /**
     * Tests getX() method
     */
    public void testGetX() {
        Rectangle rectangle = new Rectangle(4, 5, 10, 12);
        assertEquals(4, rectangle.getxCoordinate());
    }


    /**
     * Tests getY() method
     */
    public void testGetY() {
        Rectangle rectangle = new Rectangle(4, 5, 10, 12);
        assertEquals(5, rectangle.getyCoordinate());
    }


    /**
     * Tests getW() method
     */
    public void testGetW() {
        Rectangle rectangle = new Rectangle(4, 5, 10, 12);
        assertEquals(10, rectangle.getWidth());
    }


    /**
     * Tests getH() method
     */
    public void testGetH() {
        Rectangle rectangle = new Rectangle(4, 5, 10, 12);
        assertEquals(12, rectangle.getHeight());
    }


    /**
     * Tests toString() method
     */
    public void testToString() {
        Rectangle rectangle = new Rectangle(4, 5, 10, 12);
        String output = rectangle.toString();
        String expected = "4, 5, 10, 12";
        assertFuzzyEquals(expected, output);
    }


    /**
     * Tests equals() method with wrong x
     */
    public void testEquals1() {
        Rectangle a = new Rectangle(1, 2, 3, 4);
        Rectangle b = new Rectangle(2, 2, 3, 4);
        assertFalse(a.equals(b));
    }


    /**
     * Tests equals() method with wrong y
     */
    public void testEquals2() {
        Rectangle a = new Rectangle(1, 2, 3, 4);
        Rectangle b = new Rectangle(1, 5, 3, 4);
        assertFalse(a.equals(b));
    }


    /**
     * Tests equals() method with wrong w
     */
    public void testEquals3() {
        Rectangle a = new Rectangle(1, 2, 3, 4);
        Rectangle b = new Rectangle(1, 2, 5, 4);
        assertFalse(a.equals(b));
    }


    /**
     * Tests equals() method with wrong h
     */
    public void testEquals4() {
        Rectangle a = new Rectangle(1, 2, 3, 4);
        Rectangle b = new Rectangle(1, 2, 3, 5);
        assertFalse(a.equals(b));
    }

}
