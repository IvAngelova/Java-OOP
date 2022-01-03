package bankSafe;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class BankVaultTest {
    BankVault bankVault;

    @Before
    public void setUp() {
        this.bankVault = new BankVault();
    }

    @Test
    public void testConstructorShouldCreateTheCorrectObject() {
        assertEquals(12, bankVault.getVaultCells().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemShouldFailWhenTheGivenCellDoesNotExist() throws OperationNotSupportedException {
        bankVault.addItem("1", new Item("test_owner", "1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemShouldFailWhenTheGivenCellIsTaken() throws OperationNotSupportedException {
        bankVault.addItem("A1", new Item("test_owner", "1"));
        bankVault.addItem("A1", new Item("test_owner1", "11"));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddItemShouldFailWhenTheItemIsAlreadyAdded() throws OperationNotSupportedException {
        Item item = new Item("test_owner", "1");
        bankVault.addItem("A1", item);
        bankVault.addItem("A2", item);
    }

    @Test
    public void testAddItemShouldAddTheItemCorrectly() throws OperationNotSupportedException {
        Item item = new Item("test_owner", "1");
        String expected = "Item:1 saved successfully!";
        assertEquals(expected, bankVault.addItem("A1", item));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemShouldFailWhenTheGivenCellDoesNotExist() {
        bankVault.removeItem("1", new Item("test_owner", "1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemShouldFailWhenTheItemInTheCellDoesNotExist() throws OperationNotSupportedException {
        Item item = new Item("test_owner", "1");
        bankVault.addItem("A1", item);
        bankVault.removeItem("A1", new Item("test_owner1", "11"));
    }

    @Test
    public void testRemoveItemShouldDeleteTheItemCorrectly() throws OperationNotSupportedException {
        Item item = new Item("test_owner", "1");
        bankVault.addItem("A2", item);
        String expected = "Remove item:1 successfully!";
        assertEquals(expected, bankVault.removeItem("A2", item));
    }

    @Test
    public void testRemoveItemShouldFreeTheCurrentCell() throws OperationNotSupportedException {
        Item item = new Item("test_owner", "1");
        bankVault.addItem("A2", item);
        bankVault.removeItem("A2", item);
        assertNull(bankVault.getVaultCells().get("A2"));
    }



}