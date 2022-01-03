package shopAndGoods;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;


import static org.junit.Assert.*;

public class ShopTest {
    Shop shop;

    @Before
    public void setUp() {
        this.shop = new Shop();
    }

    @Test
    public void testConstructorShouldCreateTheCorrectObject() {
        assertEquals(12, shop.getShelves().size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShouldThrowWhenTheGivenShelfDoesNotExist() throws OperationNotSupportedException {
        shop.addGoods("Shelves13", new Goods("test", "123"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShouldThrowWhenTheGivenShelfIsAlreadyTaken() throws OperationNotSupportedException {
        shop.addGoods("Shelves1", new Goods("test", "1"));
        shop.addGoods("Shelves1", new Goods("test1", "2"));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddGoodsShouldThrowWhenTheGoodIsAlreadyAddedToTheShop() throws OperationNotSupportedException {
        Goods good = new Goods("test", "1");
        shop.addGoods("Shelves1", good);
        shop.addGoods("Shelves5", good);
    }

    @Test
    public void testAddGoodsShouldAddTheCorrectGood() throws OperationNotSupportedException {
        Goods good = new Goods("test", "1");
        String result = "Goods: 1 is placed successfully!";
        assertEquals(result, shop.addGoods("Shelves1", good));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldThrowWhenTheGivenShelfDoesNotExist() {
        shop.removeGoods("Shelves13", new Goods("test", "123"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldThrowWhenTheGivenShelfIsEmpty() {
        shop.removeGoods("Shelves5", new Goods("test", "123"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldThrowWhenOnTheGivenShelfAreOtherGoods() throws OperationNotSupportedException {
        shop.addGoods("Shelves5", new Goods("goods", "1"));
        shop.removeGoods("Shelves5", new Goods("test", "123"));
    }

    @Test
    public void testRemoveGoodsShouldRemoveTheCorrectGood() throws OperationNotSupportedException {
        Goods good = new Goods("test", "1");
        shop.addGoods("Shelves5", good);
        String expected = "Goods: 1 is removed successfully!";
        assertEquals(expected, shop.removeGoods("Shelves5", good));
    }

    @Test
    public void testRemoveGoodsShouldFreeTheCurrentShelves() throws OperationNotSupportedException {
        Goods good = new Goods("test", "1");
        shop.addGoods("Shelves5", good);
        shop.removeGoods("Shelves5", good);
        assertNull(shop.getShelves().get("Shelves5"));
    }


}