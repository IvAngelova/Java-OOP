package aquarium;

import org.junit.Test;

import static org.junit.Assert.*;

public class AquariumTests {

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWhenNameIsNull() {
        Aquarium aquarium = new Aquarium(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWhenNameIsEmpty() {
        Aquarium aquarium = new Aquarium("    ", 5);
    }

    @Test
    public void testSetNameShouldSetTheCorrectName() {
        Aquarium aquarium = new Aquarium("test", 5);
        assertEquals("test", aquarium.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldThrowWhenCapacityIsNegative() {
        Aquarium aquarium = new Aquarium("test", -5);
    }

    @Test
    public void testSetCapacityShouldSetTheCorrectCapacity() {
        Aquarium aquarium = new Aquarium("test", 5);
        assertEquals(5, aquarium.getCapacity());
    }

    @Test
    public void testConstructorShouldCreateTheCorrectObject() {
        Aquarium aquarium = new Aquarium("test", 5);
        assertEquals("test", aquarium.getName());
        assertEquals(5, aquarium.getCapacity());
        assertEquals(0, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowWhenCapacityIsReached() {
        Aquarium aquarium = new Aquarium("test", 0);
        aquarium.add(new Fish("fish1"));
    }

    @Test
    public void testAddShouldAddTheFish() {
        Aquarium aquarium = new Aquarium("test", 5);
        aquarium.add(new Fish("fish1"));
        assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowWhenTheFishDoesNotExistInAquarium() {
        Aquarium aquarium = new Aquarium("test", 5);
        aquarium.add(new Fish("fish1"));
        aquarium.remove("fish");
    }

    @Test
    public void testRemoveShouldRemoveTheCorrectFish() {
        Aquarium aquarium = new Aquarium("test", 5);
        aquarium.add(new Fish("fish1"));
        aquarium.remove("fish1");
        assertEquals(0, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFishShouldThrowWhenTheFishDoesNotExistInAquarium() {
        Aquarium aquarium = new Aquarium("test", 5);
        aquarium.add(new Fish("fish1"));
        aquarium.sellFish("fish");
    }

    @Test
    public void testSellFishShouldReturnTheCorrectFish() {
        Aquarium aquarium = new Aquarium("test", 5);
        Fish fish = new Fish("fish1");
        aquarium.add(fish);
        Fish returnedFish = aquarium.sellFish("fish1");
        assertEquals(fish, returnedFish);
        assertFalse(returnedFish.isAvailable());
    }

    @Test
    public void testReportShouldGiveTheCorrectInformation() {
        Aquarium aquarium = new Aquarium("test", 5);
        aquarium.add(new Fish("fish1"));
        aquarium.add(new Fish("fish2"));
        String expected = "Fish available at test: fish1, fish2";
        assertEquals(expected, aquarium.report());
    }
}

