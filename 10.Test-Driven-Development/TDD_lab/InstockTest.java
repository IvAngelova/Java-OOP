package TDD_lab;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {

    private static final String LABEL = "test_label";
    private static final double PRICE = 9.99;
    private static final int QUANTITY = 1;

    private ProductStock stock;
    private Product product;


    @Before
    public void setUp() {
        this.stock = new Instock();
        this.product = new Product(LABEL, PRICE, QUANTITY);
    }

    @Test
    public void testAddShouldAddTheGivenProductInStock() {
        stock.add(product);
        assertEquals(1, stock.getCount());
        assertTrue(stock.contains(product));
    }

    @Test
    public void testContainsShouldReturnFalseWhenProductNotPresentAndThenTrueAfterAdded() {
        assertFalse(stock.contains(product));
        stock.add(product);
        assertTrue(stock.contains(product));
    }

    @Test
    public void testAddProductShouldNotAddTheSameProductASecondTime() {
        stock.add(product);
        stock.add(product);
        assertEquals(1, stock.getCount());
    }

    @Test
    public void testCountShouldReturnTheCorrectNumberOfProducts() {
        // Asserts zero when empty
        assertEquals(0, stock.getCount());

        stock.add(product);
        assertEquals(1, stock.getCount());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldThrowWhenIndexIsNegative() {
        stock.add(product);
        stock.find(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldThrowWhenIndexIsEqualOrGreaterThanTheCountOfProductsInStock() {
        stock.add(product);
        stock.find(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindByIndexShouldFailWhenStockIsEmpty() {
        stock.find(0);
    }

    @Test
    public void testFindShouldReturnTheProductOnTheGivenIndex() {
        stock.add(product);
        Product productToFind = stock.find(0);
        assertNotNull(productToFind);
        assertEquals(product.getLabel(), productToFind.getLabel());
    }

    @Test
    public void testChangeQuantityShouldUpdateTheCorrectProductWithTheCorrectAmount() {
        stubProducts();
        stock.add(product);
        stock.changeQuantity(product.getLabel(), 13);
        assertEquals(13, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldThrowWhenTheProductIsNotPresent() {
        stubProducts();
        stock.changeQuantity(product.getLabel(), 13);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelShouldThrowWhenTheProductIsNotPresent() {
        stubProducts();
        stock.findByLabel(product.getLabel());
    }

    @Test
    public void testFindByLabelShouldReturnTheProductWithTheGivenLabel() {
        stubProducts();
        stock.add(product);
        Product byLabel = stock.findByLabel(product.getLabel());
        assertNotNull(byLabel);
        assertEquals(product.getLabel(), byLabel.getLabel());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollectionWhenArgumentIsTooLarge() {
        stubProducts();
        Iterable<Product> firstByAlphabeticalOrder = stock.findFirstByAlphabeticalOrder(11);
        List<Product> list = createListFromIterable(firstByAlphabeticalOrder);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollectionWhenStockIsEmpty() {
        Iterable<Product> firstByAlphabeticalOrder = stock.findFirstByAlphabeticalOrder(5);
        List<Product> list = createListFromIterable(firstByAlphabeticalOrder);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnTheCorrectNumberOfProducts() {
        stubProducts();
        Iterable<Product> firstByAlphabeticalOrder = stock.findFirstByAlphabeticalOrder(5);
        List<Product> list = createListFromIterable(firstByAlphabeticalOrder);
        assertEquals(5, list.size());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnTheProductsSortedByLabel() {
        stubProducts();
        Iterable<Product> firstByAlphabeticalOrder = stock.findFirstByAlphabeticalOrder(5);
        List<Product> list = createListFromIterable(firstByAlphabeticalOrder);
        Set<String> expectedLabels = list.stream()
                .map(Product::getLabel)
                .collect(Collectors.toCollection(TreeSet::new));

        int i = 0;
        for (String expectedLabel : expectedLabels) {
            assertEquals(expectedLabel, list.get(i++).getLabel());
        }
    }

    @Test
    public void testFindAllInRangeShouldReturnEmptyCollectionIfNoSuchRangePresent() {
        stubProducts();
        List<Product> products = createListFromIterable(stock.findAllInRange(99999, 9999999));
        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindAllInRangeShouldReturnTheCorrectRange() {
        stubProducts();
        List<Product> products = createListFromIterable(stock.findAllInRange(10.00, 30.00));
        assertEquals(6, products.size());
        for (Product product : products) {
            assertTrue(product.getPrice() > 10.00 && product.getPrice() <= 30.00);
        }
    }

    @Test
    public void testFindAllInRangeShouldReturnProductsOrderedByPriceDescending() {
        stubProducts();
        List<Product> products = createListFromIterable(stock.findAllInRange(10.00, 30.00));

        List<Product> expected = products
                .stream()
                .sorted((f, s) -> Double.compare(s.getPrice(), f.getPrice()))
                .collect(Collectors.toList());

        assertEquals(expected, products);
    }

    @Test
    public void testFindAllByPriceShouldReturnEmptyCollectionWhenNoProductsWithGivenPriceExist() {
        stubProducts();
        Iterable<Product> allByPrice = stock.findAllByPrice(50.00);
        List<Product> list = createListFromIterable(allByPrice);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testFindAllByPriceShouldReturnAllProductsWithTheGivenPrice() {
        stubProducts();
        List<Product> list = createListFromIterable(stock.findAllByPrice(10.00));
        assertEquals(4, list.size());
        for (Product product : list) {
            assertEquals(10.00, product.getPrice(), 0.0);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstMostExpensiveProductsShouldThrowWhenTheCountIsGreaterThanTheTotalNumberOfProducts() {
        stubProducts();
        stock.findFirstMostExpensiveProducts(stock.getCount() + 1);
    }

    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnTheCorrectNumberOfProductsSorted() {
        List<Product> expected = stubProducts()
                .stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(5)
                .collect(Collectors.toList());
        List<Product> list = createListFromIterable(stock.findFirstMostExpensiveProducts(5));
        assertEquals(5, list.size());
        assertEquals(expected, list);

    }

    @Test
    public void testFindAllByQuantityShouldReturnEmptyCollectionWhenNoProductsWithGivenQuantityExist() {
        stubProducts();
        Iterable<Product> allByQuantity = stock.findAllByQuantity(7);
        List<Product> list = createListFromIterable(allByQuantity);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testFindAllByQuantityShouldReturnAllProductsWithTheGivenQuantity() {
        stubProducts();
        List<Product> list = createListFromIterable(stock.findAllByQuantity(2));
        assertEquals(5, list.size());
        for (Product product : list) {
            assertEquals(2, product.getQuantity());
        }
    }


    @Test
    public void testIteratorShouldReturnAllProductsInStock() {
        List<Product> expected = stubProducts();

        Iterator<Product> iterator = stock.iterator();
        assertNotNull(iterator);

        int index = 0;
        while (iterator.hasNext()) {
            Product product = iterator.next();
            assertEquals(expected.get(index++).getLabel(), product.getLabel());
        }
    }

    //Helper Methods
    private List<Product> createListFromIterable(Iterable<Product> iterable) {
        assertNotNull(iterable);
        List<Product> products = new ArrayList<>();
        iterable.forEach(products::add);
        return products;
    }


    private List<Product> stubProducts() {
        List<Product> products = Arrays.asList(
                new Product("test_label_1", 10.00, 2),
                new Product("test_label_2", 20.00, 10),
                new Product("test_label_3", 30.00, 10),
                new Product("test_label_4", 15.00, 10),
                new Product("test_label_5", 17.00, 2),
                new Product("test_label_6", 14.99, 2),
                new Product("test_label_7", 10.01, 10),
                new Product("test_label_8", 10.00, 2),
                new Product("test_label_9", 10.00, 10),
                new Product("test_label_10", 10.00, 2)
        );

        for (Product product : products) {
            this.stock.add(product);
        }

        return products;
    }

}
