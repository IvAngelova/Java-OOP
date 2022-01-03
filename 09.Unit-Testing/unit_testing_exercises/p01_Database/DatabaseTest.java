package p01_Database;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private static final Integer[] NUMBERS = {4, 16, 3, 78, 8};
    private static final int SIZE_NUMBERS = 5;

    private Database database;


    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(NUMBERS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWhenElementsAreMoreThan16() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[17];
        new Database(numbers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWhenElementsAreLessThan1() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        new Database(numbers);
    }

    @Test
    public void testConstructorShouldCreateDatabaseWithCapacityOf16Elements() {
        assertEquals(NUMBERS.length, database.getElements().length);
        assertArrayEquals(NUMBERS, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowWhenNullIsAdded() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddShouldAddElementAtTheNextFreeCell() throws OperationNotSupportedException {
        int elementToAdd = 15;
        database.add(elementToAdd);
        assertEquals(SIZE_NUMBERS + 1, database.getElements().length);
        assertEquals(Integer.valueOf(elementToAdd), database.getElements()[database.getElements().length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveElementFromEmptyDatabaseShouldThrow() throws OperationNotSupportedException {
        Database database = new Database(1);
        database.remove();
        database.remove();
    }

    @Test
    public void testRemoveShouldDeleteElementAtTheLastIndex() throws OperationNotSupportedException {
        database.remove();
        assertEquals(SIZE_NUMBERS - 1, database.getElements().length);
        assertEquals(NUMBERS[NUMBERS.length - 2], database.getElements()[database.getElements().length - 1]);
    }

}