package boj.n1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1182 {

    static int ANS = 0;
    static int TARGET;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        TARGET = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        check(numbers, 0, 0);

        if(TARGET == 0){
            ANS--;
        }
        System.out.println(ANS);

    }

    static void check(int[] numbers, int current, int start) {
        if (start == numbers.length) {
            if (current == TARGET) {
                ANS++;
            }
            return;
        }

        check(numbers, current + numbers[start], start + 1);
        check(numbers, current, start + 1);

    }
}
