package cats;

import org.junit.Test;
import static org.junit.Assert.*;

public class HouseTests {
    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWhenNameIsNull() {
        House house = new House(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWhenNameIsEmpty() {
        House house = new House("     ", 10);
    }

    @Test
    public void testSetNameShouldSetTheNameCorrectly() {
        House house = new House("PetHouse", 10);
        assertEquals("PetHouse", house.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldThrowWhenCapacityIsNegative(){
        House house = new House("PetHouse", -10);
    }


    @Test
    public void testSetCapacityShouldSetTheCapacityCorrectly(){
        House house = new House("PetHouse", 10);
        assertEquals(10, house.getCapacity());
    }

    @Test
    public void testConstructorShouldCreateTheCorrectObject(){
        House house = new House("PetHouse", 10);
        assertEquals(10, house.getCapacity());
        assertEquals("PetHouse", house.getName());
        assertEquals(0, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatShouldThrowWhenCapacityIsReached(){
        House house = new House("PetHouse", 1);
        house.addCat(new Cat("test1"));
        house.addCat(new Cat("test2"));
    }

    @Test
    public void testAddCatShouldAddTheCat(){
        House house = new House("PetHouse", 10);
        house.addCat(new Cat("test1"));
        house.addCat(new Cat("test2"));
        assertEquals(2, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatShouldThrowWhenCatIsNotExisting(){
        House house = new House("PetHouse", 10);
        house.addCat(new Cat("test1"));
        house.removeCat("test2");
    }

    @Test
    public void testRemoveCatShouldRemoveTheCat(){
        House house = new House("PetHouse", 10);
        house.addCat(new Cat("test2"));
        house.removeCat("test2");
        assertEquals(0, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleShouldFailWhenTheCatDoesNotExist(){
        House house = new House("PetHouse", 10);
        house.addCat(new Cat("test2"));
        house.catForSale("test1");
    }

    @Test
    public void testCatForSaleShouldReturnTheCorrectCat(){
        House house = new House("PetHouse", 10);
        Cat cat = new Cat("test1");
        house.addCat(cat);
        assertEquals(cat, house.catForSale("test1"));
        assertFalse(house.catForSale("test1").isHungry());
    }

    @Test
    public void testStatistics(){
        House house = new House("PetHouse", 10);
        house.addCat(new Cat("test1"));
        house.addCat(new Cat("test2"));
        String expected = "The cat test1, test2 is in the house PetHouse!";
        assertEquals(expected, house.statistics());
    }
}
