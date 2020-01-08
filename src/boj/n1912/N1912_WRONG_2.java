package boj.n1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1912_WRONG_2 {

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

            int sum = 0;
            int left = Integer.MIN_VALUE;
            for (int i = middle; i >= 0; i--) {
                sum += arr[i];
                left = Math.max(sum, left);
            }

            sum = 0;
            int right = Integer.MIN_VALUE;

            for (int i = middle + 1; i <= end; i++) {
                sum += arr[i];
                right = Math.max(sum, right);
            }

            int max = left + right;
            int divide = Math.max(check(arr, start, middle), check(arr, middle + 1, end));

            return Math.max(max, divide);


        } else {

            return arr[start];

        }
    }
}
