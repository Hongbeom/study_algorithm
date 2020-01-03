package boj.n14458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N14458 {

    static int[] TMP;
    static long INVERSION = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TMP = new int[n];
        int[] order = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            int r = Integer.parseInt(br.readLine()) - 1;
            order[r] = i;
        }

        for (int i = 0; i < n; i++) {
            int l = Integer.parseInt(br.readLine()) - 1;
            left[order[l]] = i;
            right[i] = order[l];
        }

        mergeCount(left.clone(), 0, left.length - 1);
        long leftInter = INVERSION;
        INVERSION = 0;
        long answer = leftInter;

        for (int k = n - 1; k > 0; k--) {
            int p = left[k] + 1;
            leftInter += p - 1 - (n - p);
            answer = Math.min(answer, leftInter);
        }

        mergeCount(right.clone(), 0, right.length - 1);
        long rightInter = INVERSION;

        for (int k = n - 1; k > 0; k--) {
            int p = right[k] + 1;
            rightInter += p - 1 - (n - p);
            answer = Math.min(answer, rightInter);
        }

        System.out.println(answer);
    }

    static void mergeCount(int[] arr, int start, int end) {

        if (start < end) {
            int middle = (start + end) / 2;

            mergeCount(arr, start, middle);
            mergeCount(arr, middle + 1, end);
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