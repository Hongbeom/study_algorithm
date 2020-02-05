package boj.n1517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1517 {

    static int[] arr;
    static int[] tmp;
    static long ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        tmp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, n - 1);
        System.out.println(ans);

    }


    static void merge(int left, int right, int mid) {

        int lid = left;
        int tid = left;
        int rid = mid + 1;

        while (lid <= mid && rid <= right) {
            if (arr[lid] <= arr[rid]) {
                tmp[tid++] = arr[lid++];
            } else {
                ans += (mid - lid) + 1;
                tmp[tid++] = arr[rid++];
            }
        }

        while (lid <= mid) {
            tmp[tid++] = arr[lid++];
        }


        while (rid <= right) {
            tmp[tid++] = arr[rid++];
        }

        for (int i = left; i <= right; i++) {
            arr[i] = tmp[i];
        }

    }

    static void mergeSort(int left, int right) {

        if (left != right) {
            int mid = (left + right) / 2;

            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, right, mid);

        }

    }
}
