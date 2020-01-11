package boj.n1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class N1208 {

    static long ANS = 0;
    static int TARGET;
    static HashMap<Integer, Integer> MEMO = new HashMap<>();


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

        checkLeft(numbers, 0, 0, n / 2);
        checkRight(numbers, 0, n / 2, n);


        if (TARGET == 0) {
            ANS -= 1;
        }

        System.out.println(ANS);

    }


    static void checkLeft(int[] numbers, int current, int start, int end) {

        if (start == end) {
            Integer cnt = MEMO.getOrDefault(current, null);
            if (cnt == null) {
                MEMO.put(current, 1);
            } else {
                MEMO.put(current, cnt + 1);
            }
            return;
        }

        checkLeft(numbers, current + numbers[start], start + 1, end);
        checkLeft(numbers, current, start + 1, end);


    }

    static void checkRight(int[] numbers, int current, int start, int end) {

        if (start == end) {
            ANS += MEMO.getOrDefault(TARGET - current, 0);
            return;
        }

        checkRight(numbers, current + numbers[start], start + 1, end);
        checkRight(numbers, current, start + 1, end);

    }


}
