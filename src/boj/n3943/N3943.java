package boj.n3943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N3943 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            long start = Long.parseLong(br.readLine());
            long answer = start;
            while (start != 1) {
                if (start % 2 == 0) {
                    start /= 2;
                } else {
                    start = start * 3 + 1;
                }
                answer = Math.max(answer, start);
            }
            System.out.println(answer);
        }
    }
}
