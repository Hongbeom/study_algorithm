package boj.n2104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2104 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] numbers = new long[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(check(numbers, 0, numbers.length - 1));

    }


    static long check(long[] arr, int start, int end) {

        if (start < end) {

            int middle = (start + end) / 2;
            long left = check(arr, start, middle);
            long right = check(arr, middle + 1, end);

            long max = Math.max(left, right);

            long min = arr[middle];
            long sum = min;

            int lid = middle - 1;
            int rid = middle + 1;

            while (lid >= start && rid <= end) {
                if (arr[lid] > arr[rid]) {
                    min = Math.min(min, arr[lid]);
                    sum += arr[lid--];
                } else {
                    min = Math.min(min, arr[rid]);
                    sum += arr[rid++];
                }
                max = Math.max(max, sum * min);
            }

            while (lid >= start) {
                min = Math.min(min, arr[lid]);
                sum += arr[lid--];
                max = Math.max(max, sum * min);
            }

            while (rid <= end) {
                min = Math.min(min, arr[rid]);
                sum += arr[rid++];
                max = Math.max(max, sum * min);
            }

            return max;

        } else {

            return arr[start] * arr[start];

        }


    }

}