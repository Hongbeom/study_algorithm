package boj.n14458;

public class MergeSort {

    // practice about merge sort and inversion counting.

    static int[] TMP;
    static int INVERSION = 0;

    public static void main(String[] args) {

//        int[] test = {3, 4, 0, 1, 2};
        int[] test = {7, 9, 2, 1, 4, 6, 5, 3};
        TMP = new int[test.length];
        mergeSort(test, 0, test.length - 1);

        for (int a : test) {
            System.out.print(a + " ");
        }

        System.out.println();
        System.out.println(INVERSION);

    }

    static void mergeSort(int[] arr, int start, int end) {

        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(arr, start, middle);
            mergeSort(arr, middle + 1, end);
            merge(arr, start, middle, end);
        }
    }

    static void merge(int[] arr, int start, int middle, int end) {
        int i = start;
        int k = start;
        int j = middle + 1;

        while (i <= middle && j <= end) {
            if (arr[i] < arr[j]) {
                TMP[k++] = arr[i++];
            } else {
                TMP[k++] = arr[j++];
                INVERSION += middle - i + 1;
            }
        }


        while (i <= middle) {
            TMP[k++] = arr[i++];
        }

        while (j <= end) {
            TMP[k++] = arr[j++];
        }

        for (int id = start; id <= end; id++) {
            arr[id] = TMP[id];
        }

    }


}
