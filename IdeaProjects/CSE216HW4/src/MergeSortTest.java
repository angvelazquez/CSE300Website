import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void testSorted()
    {
        // Testing arrays
        int[] test1 = {2,5,7,3,24,6,8};
        int[] test2 = {3,2};
        int[] test3 = {3};
        int[] test4 = {1,2,3,4,5,6};
        int[] test5 = {};
        int[] test6 = {3,3,3,3,3,3};
        // Answer arrays
        boolean ans1 = false;
        boolean ans2 = false;
        boolean ans3 = true;
        boolean ans4 = true;
        boolean ans5 = true;
        boolean ans6 = true;
        assertAll(
                () -> assertEquals(ans1, MergeSort.sorted(test1),
                        String.format("%s || %b", Arrays.toString(test1), true)),
                () -> assertEquals(ans2, MergeSort.sorted(test2),
                        String.format("%s || %b", Arrays.toString(test2), true)),
                () -> assertEquals(ans3, MergeSort.sorted(test3),
                        String.format("%s || %b", Arrays.toString(test3), true)),
                () -> assertEquals(ans4, MergeSort.sorted(test4),
                        String.format("%s || %b", Arrays.toString(test4), true)),
                () -> assertEquals(ans5, MergeSort.sorted(test5),
                        String.format("%s || %b", Arrays.toString(test5), true)),
                () -> assertEquals(ans6, MergeSort.sorted(test6),
                        String.format("%s || %b", Arrays.toString(test6), true))
        );
    }
}