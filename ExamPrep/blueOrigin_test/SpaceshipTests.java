package blueOrigin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWhenNameIsNull() {
        Spaceship spaceship = new Spaceship(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWhenNameIsEmpty() {
        Spaceship spaceship = new Spaceship("    ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCapacityShouldFailWhenCapacityIsNegative() {
        Spaceship spaceship = new Spaceship("test_name", -1);
    }

    @Test
    public void testConstructorShouldCreateTheCorrectObject() {
        Spaceship spaceship = new Spaceship("test_name", 10);
        assertEquals(10, spaceship.getCapacity());
        assertEquals("test_name", spaceship.getName());
        assertEquals(0, spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldFailWhenCapacityIsReached() {
        Spaceship spaceship = new Spaceship("test_name", 1);
        Astronaut astronaut = new Astronaut("Java", 3.5);
        spaceship.add(astronaut);
        spaceship.add(new Astronaut("J", 2.3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldFailWhenTheAstronautIsAlreadyAdded() {
        Spaceship spaceship = new Spaceship("test_name", 10);
        Astronaut astronaut = new Astronaut("Java", 3.5);
        spaceship.add(astronaut);
        spaceship.add(astronaut);
    }

    @Test
    public void testAddShouldAddTheAstronautCorrectly() {
        Spaceship spaceship = new Spaceship("test_name", 10);
        Astronaut astronaut = new Astronaut("Java", 3.5);
        spaceship.add(astronaut);
        assertEquals(1, spaceship.getCount());
    }

    @Test
    public void testRemoveShouldReturnTheCorrectBoolean(){
        Spaceship spaceship = new Spaceship("test_name", 10);
        assertFalse(spaceship.remove("Java"));
        Astronaut astronaut = new Astronaut("Java", 3.5);
        spaceship.add(astronaut);
        assertTrue(spaceship.remove("Java"));
    }

}
