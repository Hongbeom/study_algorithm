package boj.n8394;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N8394_WRONG {

    static int ANS = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        check(0, n - 1);

        System.out.println(ANS);
    }


    static void check(int start, int end) {
        if (start >= end) {
            ANS++;
            return;
        }
        // 오른쪽 사람과 악수를 하는 경우
        check(start + 2, end);
        // 으론쪽 사람과 악수를 하지 않는 경우
        check(start + 1, end);
    }
}
