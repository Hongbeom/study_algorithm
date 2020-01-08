package boj.n1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1912_WRONG_1 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(check(numbers, 0, numbers.length - 1));


    }

    static int check(int[] arr, int start, int end) {

        if (start < end) {
            int middle = (start + end) / 2;

            int max = Math.max(check(arr, start, middle), check(arr, middle + 1, end));

            int sum = arr[middle];
            max = Math.max(sum, max);

            int lid = middle - 1;
            int rid = middle + 1;

            while (lid >= start && rid <= end) {
                if (arr[lid] > arr[rid]) {
                    sum += arr[lid--];
                } else {
                    sum += arr[rid++];
                }
                max = Math.max(sum, max);
            }

            while (lid >= start) {
                sum += arr[lid--];
                max = Math.max(sum, max);
            }

            while (rid <= end) {
                sum += arr[rid++];
                max = Math.max(sum, max);
            }

            return max;

        } else {
            return arr[start];
        }
    }
}
