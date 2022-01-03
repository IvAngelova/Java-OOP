package p05_CustomLinkedList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {
    private CustomLinkedList<String> customLinkedList;

    @Before
    public void setUp() {
        this.customLinkedList = new CustomLinkedList<>();
        this.customLinkedList.add("X");
    }

    @Test
    public void testGetShouldReturnTheElementAtTheSpecifiedPosition() {
        assertEquals("X", customLinkedList.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowWhenIndexIsNegative() {
        customLinkedList.get(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowWhenIndexIsGreaterOrEqualTheCountOfTheElementsInTheList() {
        customLinkedList.get(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowWhenIndexIsNegative() {
        customLinkedList.get(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowWhenIndexIsGreaterOrEqualTheCountOfTheElementsInTheList() {
        customLinkedList.set(10, "A");
    }

    @Test
    public void testSetShouldSetTheElementAtTheSpecifiedPosition() {
        customLinkedList.set(0, "A");
        assertEquals("A", customLinkedList.get(0));
    }

    @Test
    public void testAddShouldAddElementAtTheEndOfTheList(){
        customLinkedList.add("A");
        assertEquals("A", customLinkedList.get(1));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtShouldThrowWhenIndexIsNegative() {
        customLinkedList.removeAt(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtShouldThrowWhenIndexIsGreaterOrEqualTheCountOfTheElementsInTheList() {
        customLinkedList.removeAt(10);
    }

    @Test
    public void testRemoveAtShouldRemoveAndReturnTheElementOnTheSpecifiedIndex(){
        String removeAt = customLinkedList.removeAt(0);
        assertEquals("X", removeAt);
    }

    @Test
    public void testRemoveShouldReturnTheIndexOfRemovedElement(){
        assertEquals(0, customLinkedList.remove("X"));
    }

    @Test
    public void testRemoveWhenTheElementIsNotFoundInTheList(){
        assertEquals(-1, customLinkedList.remove("A"));
    }

    @Test
    public void testIndexOfShouldReturnTheIndexOfGivenElement(){
        assertEquals(0, customLinkedList.indexOf("X"));
    }

    @Test
    public void testIndexOfWhenTheElementIsNotFoundInTheList(){
        assertEquals(-1, customLinkedList.indexOf("A"));
    }

    @Test
    public void testContains(){
        assertTrue(customLinkedList.contains("X"));
        assertFalse(customLinkedList.contains("A"));
    }









}