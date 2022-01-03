package p02_ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;


import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private static final Person[] PEOPLE =
            {new Person(1, "one"),
                    new Person(2, "two"),
                    new Person(3, "three")};

    private Database database;


    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(PEOPLE);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWhenElementsAreMoreThan16() throws OperationNotSupportedException {
        new Database(new Person[17]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWhenElementsAreLessThan1() throws OperationNotSupportedException {
        new Database();
    }

    @Test
    public void testConstructorShouldCreateDatabaseWithCapacityOf16Elements() {
        assertEquals(PEOPLE.length, database.getElements().length);
        assertArrayEquals(PEOPLE, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowWhenNullIsAdded() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddShouldAddPersonAtTheNextFreeCell() throws OperationNotSupportedException {
        Person personToAdd = new Person(4, "four");
        database.add(personToAdd);
        assertEquals(PEOPLE.length + 1, database.getElements().length);
        assertEquals(personToAdd, database.getElements()[database.getElements().length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemovePersonFromEmptyDatabaseShouldThrow() throws OperationNotSupportedException {
        for (Person person : database.getElements()) {
            database.remove();
        }

        database.remove();
    }

    @Test
    public void testRemoveShouldDeletePersonAtTheLastIndex() throws OperationNotSupportedException {
        database.remove();
        assertEquals(PEOPLE.length - 1, database.getElements().length);
        assertEquals(PEOPLE[PEOPLE.length - 2], database.getElements()[database.getElements().length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameShouldThrowWhenNullIsPassed() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameShouldThrowWhenNoUserIsPresentByThisUsername() throws OperationNotSupportedException {
        database.findByUsername("four");
    }

    @Test
    public void testFindByUsernameShouldReturnThePersonWithTheGivenUsername() throws OperationNotSupportedException {
        Person byUsername = database.findByUsername(PEOPLE[1].getUsername());
        assertEquals(PEOPLE[1], byUsername);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdShouldThrowWhenNoUserIsPresentByThisId() throws OperationNotSupportedException {
        database.findById(45);
    }

    @Test
    public void testFindByIdShouldReturnThePersonWithTheGivenId() throws OperationNotSupportedException {
        Person byId = database.findById(PEOPLE[1].getId());
        assertEquals(PEOPLE[1], byId);
    }




}