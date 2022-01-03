package computers;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ComputerManagerTests {
    ComputerManager computerManager;

    @Before
    public void setUp() {
        this.computerManager = new ComputerManager();
    }

    @Test
    public void testConstructorShouldCreateTheCorrectObject() {
        assertEquals(0, this.computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerShouldFailWhenTheComputerIsNull() {
        this.computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerShouldFailWhenTheComputerIsAlreadyAdded() {
        Computer computer = new Computer("manufacturer", "model", 1.1);
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer);
    }

    @Test
    public void testAddComputerShouldAddTheComputerCorrectly() {
        Computer computer = new Computer("manufacturer", "model", 1.1);
        this.computerManager.addComputer(computer);
        assertEquals(1, this.computerManager.getCount());
        assertEquals(computer, this.computerManager.getComputers().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerShouldFailWhenTheManufacturerIsNull() {
        Computer computer = new Computer("manufacturer", "model", 1.1);
        this.computerManager.addComputer(computer);
        this.computerManager.getComputer(null, "model");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerShouldFailWhenTheModelIsNull() {
        Computer computer = new Computer("manufacturer", "model", 1.1);
        this.computerManager.addComputer(computer);
        this.computerManager.getComputer("manufacturer", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerShouldFailWhenTheComputerDoesNotExist() {
        Computer computer = new Computer("manufacturer", "model", 1.1);
        this.computerManager.addComputer(computer);
        this.computerManager.getComputer("manufacturer1", "model4");
    }

    @Test
    public void testGetComputerShouldReturnTheCorrectComputer() {
        Computer computer = new Computer("manufacturer", "model", 1.1);
        this.computerManager.addComputer(computer);
        assertEquals(computer, this.computerManager.getComputer("manufacturer", "model"));
    }

    @Test
    public void testRemoveComputerShouldRemoveTheComputerCorrectly() {
        Computer computer = new Computer("manufacturer", "model", 1.1);
        this.computerManager.addComputer(computer);
        assertEquals(computer, this.computerManager.removeComputer("manufacturer", "model"));
        assertEquals(0, this.computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputersByManufacturerShouldFailWhenManufacturerIsNull() {
        Computer computer = new Computer("manufacturer", "model", 1.1);
        this.computerManager.addComputer(computer);
        this.computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void testGetComputersByManufacturerShouldReturnTheCorrectCollection() {
        Computer computer = new Computer("manufacturer", "model", 1.1);
        Computer computer1 = new Computer("manufacturer", "model1", 1.2);
        List<Computer> expected = List.of(computer, computer1);
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer1);
        assertEquals(expected, this.computerManager.getComputersByManufacturer("manufacturer"));
    }
}