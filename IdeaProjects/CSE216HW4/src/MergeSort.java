import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MergeSort {

    private static final Random RNG    = new Random(10982755L);
    private static final int    LENGTH = 1024000;

    public static void main(String... args) {
        int[] arr = randomIntArray();
        long start = System.currentTimeMillis();
        concurrentMergeSort(arr);
        long end = System.currentTimeMillis();
        if (!sorted(arr)) {
            System.err.println("The final array is not sorted");
            System.exit(0);
        }
        System.out.printf("%10d numbers: %6d ms%n", LENGTH, end - start);

    }

    private static int[] randomIntArray() {
        int[] arr = new int[LENGTH];
        for (int i = 0; i < arr.length; i++)
            arr[i] = RNG.nextInt(LENGTH * 10);
        return arr;
    }

    public static boolean sorted(int[] arr) {
        for(int i = 1; i < arr.length; i++)
        {
            if(arr[i - 1] > arr[i])
                return false;
        }
        return true;
    }

    public static void concurrentMergeSort(int[] array)
    {
        //int threadCount = Runtime.getRuntime().availableProcessors();
        concurrentMergeSort(array, 11);
    }

    public static void concurrentMergeSort(int[] array, int threadCount)
    {
        if(threadCount <= 1)
        {
            mergeSort(array);
        }
        else
        {
            if(array.length >= 2)
            {
                int middle = array.length / 2;
                int[] left = new int[array.length / 2];
                System.arraycopy(array, 0, left, 0, middle);
                int[] right = new int[array.length / 2];
                System.arraycopy(array, middle, right, 0, middle);

                Thread leftSort = new Thread(new Sorting(left, threadCount / 2));
                Thread rightSort = new Thread(new Sorting(right, threadCount / 2));

                leftSort.start();
                rightSort.start();

                try
                {
                    leftSort.join();
                    rightSort.join();
                }
                catch(InterruptedException ex)
                {
                    System.out.println(ex.getMessage());
                }

                merge(left, right, array);
            }
        }
    }

    public static void mergeSort(int[] array)
    {
        if(array.length >= 2)
        {
            int middle = array.length / 2;
            int[] left = new int[middle];
            System.arraycopy(array, 0, left, 0, middle);
            if(array.length % 2 != 0)
                middle++;
            int[] right = new int[middle];
            System.arraycopy(array, array.length / 2, right, 0, middle);
            mergeSort(left);
            mergeSort(right);
            merge(left, right, array);
        }
        return;
    }

    public static void merge(int[] left, int[] right, int[] result)
    {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while(leftIndex < left.length && rightIndex < right.length)
        {
            if(left[leftIndex] < right[rightIndex])
            {
                result[resultIndex] = left[leftIndex];
                resultIndex++;
                leftIndex++;
            }
            else if(left[leftIndex] > right[rightIndex])
            {
                result[resultIndex] = right[rightIndex];
                resultIndex++;
                rightIndex++;
            }
            else
            {
                result[resultIndex] = right[rightIndex];
                resultIndex++;
                rightIndex++;
                result[resultIndex] = left[leftIndex];
                resultIndex++;
                leftIndex++;
            }
        }

        while (leftIndex < left.length)
        {
            result[resultIndex] = left[leftIndex];
            resultIndex++;
            leftIndex++;
        }

        while (rightIndex < right.length)
        {
            result[resultIndex] = right[rightIndex];
            resultIndex++;
            rightIndex++;
        }
    }
}