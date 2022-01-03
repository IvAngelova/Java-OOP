package farmville;

import org.junit.Test;

import static org.junit.Assert.*;

public class FarmvilleTests {

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWhenNameIsEmpty() {
        Farm farm = new Farm("   ", 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWhenNameIsNull() {
        Farm farm = new Farm(null, 10);
    }

    @Test
    public void testSetNameShouldSetTheCorrectName() {
        Farm farm = new Farm("test_farm", 10);
        assertEquals("test_farm", farm.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldThrowWhenArgIsLessThanZero() {
        Farm farm = new Farm("test_farm", -4);
    }

    @Test
    public void testSetCapacityShouldSetTheCorrectCapacity() {
        Farm farm = new Farm("test_farm", 6);
        assertEquals(6, farm.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowWhenCapacityIsReached() {
        Farm farm = new Farm("test_farm", 1);
        farm.add(new Animal("type_test", 4.5));
        farm.add(new Animal("type_test1", 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowWhenTheAnimalAlreadyExists() {
        Farm farm = new Farm("test_farm", 6);
        farm.add(new Animal("type_test", 4.5));
        farm.add(new Animal("type_test", 4.5));
    }

    @Test
    public void testAddShouldAddTheCorrectAnimal() {
        Farm farm = new Farm("test_farm", 6);
        farm.add(new Animal("type_test", 4.5));
        assertEquals(1, farm.getCount());
    }

    @Test
    public void testRemoveShouldRemoveTheCorrectAnimal(){
        Farm farm = new Farm("test_farm", 6);
        farm.add(new Animal("type_test", 4.5));
        assertFalse(farm.remove("test"));
        assertTrue(farm.remove("type_test"));

    }
}
