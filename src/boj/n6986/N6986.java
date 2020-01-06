package boj.n6986;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N6986 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        double[] numbers = new double[n];
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            numbers[i] = Double.parseDouble(br.readLine());
            min = Math.min(min, numbers[i]);
        }

        Arrays.sort(numbers);
        double sum = 0d;

        for (int i = k; i < numbers.length - k; i++) {
            sum += numbers[i];
        }

        System.out.println(String.format("%.2f", sum / (n - 2 * k)));
        System.out.println(String.format("%.2f", (sum + numbers[k] * k + numbers[n - k - 1] * k) / n));

    }
}
