package p03_IteratorTest;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {

    private static final String[] ELEMENTS = new String[]{"one", "two", "three"};

    private ListIterator listIterator;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.listIterator = new ListIterator(ELEMENTS);
        //currentIndex = 0
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWhenNullIsPassed() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void testMoveShouldUpdateTheInternalIndex() {
        assertTrue(listIterator.move());
        assertTrue(listIterator.move());
        assertFalse(listIterator.move());
    }

    @Test
    public void testHasNextShouldReturnWhetherTheCollectionHasNextElement() {
        assertTrue(listIterator.hasNext());
        listIterator.move();
        assertTrue(listIterator.hasNext());
        listIterator.move();
        assertFalse(listIterator.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintShouldThrowWhenCollectionIsEmpty() throws OperationNotSupportedException {
        new ListIterator().print();
    }

    @Test
    public void testPrintShouldReturnTheElementAtTheCurrentInternalIndex() {
        // assertEquals("one", listIterator.print());
        int index = 0;
        while (listIterator.hasNext()) {
            assertEquals(listIterator.print(), ELEMENTS[index++]);
            listIterator.move();
        }
    }


}