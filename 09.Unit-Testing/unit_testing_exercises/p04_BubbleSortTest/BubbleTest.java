package p04_BubbleSortTest;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BubbleTest {
    @Test
    public void testSort() {
        int[] numbers = {3, 14, 38, 5, 54, -2, 15};
        int[] expectedSortedArray = Arrays.stream(numbers).sorted().toArray();
        Bubble.sort(numbers);
        assertEquals(expectedSortedArray.length, numbers.length);
        assertArrayEquals(expectedSortedArray, numbers);
    }

}