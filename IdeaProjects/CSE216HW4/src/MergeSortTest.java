import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    public void testSorted()
    {
        // Testing arrays
        int[] test1 = {2,5,7,3,24,6,8};
        int[] test2 = {3,2};
        int[] test3 = {3};
        int[] test4 = {1,2,3,4,5,6};
        int[] test5 = {};
        int[] test6 = {3,3,3,3,3,3};
        int[] test7 = {-2,-5,-7,-3,-24,-6,-8};
        int[] test8 = {-54,-45,-6,-3,-3,-1,0,};
        int[] test9 = {-54,-45,1,2,3,10,21};
        int[] test10 = {-3,-3,-3,-3,-3,-3};
        int[] test11 = {0,0,0,0,0,0};
        assertAll(
                () -> assertEquals(false, MergeSort.sorted(test1),
                        String.format("%s || %b", Arrays.toString(test1), true)),
                () -> assertEquals(false, MergeSort.sorted(test2),
                        String.format("%s || %b", Arrays.toString(test2), true)),
                () -> assertEquals(true, MergeSort.sorted(test3),
                        String.format("%s || %b", Arrays.toString(test3), true)),
                () -> assertEquals(true, MergeSort.sorted(test4),
                        String.format("%s || %b", Arrays.toString(test4), true)),
                () -> assertEquals(true, MergeSort.sorted(test5),
                        String.format("%s || %b", Arrays.toString(test5), true)),
                () -> assertEquals(true, MergeSort.sorted(test6),
                        String.format("%s || %b", Arrays.toString(test6), true)),
                () -> assertEquals(false, MergeSort.sorted(test7),
                        String.format("%s || %b", Arrays.toString(test7), true)),
                () -> assertEquals(true, MergeSort.sorted(test8),
                        String.format("%s || %b", Arrays.toString(test8), true)),
                () -> assertEquals(true, MergeSort.sorted(test9),
                        String.format("%s || %b", Arrays.toString(test9), true)),
                () -> assertEquals(true, MergeSort.sorted(test10),
                        String.format("%s || %b", Arrays.toString(test10), true)),
                () -> assertEquals(true, MergeSort.sorted(test11),
                        String.format("%s || %b", Arrays.toString(test11), true))
        );
    }
}