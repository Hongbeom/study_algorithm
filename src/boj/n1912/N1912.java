package boj.n1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1912 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int memo = 0;
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (i == 0) {
                memo = number;
            } else {
                if (memo + number > number) {
                    memo = memo + number;
                } else {
                    memo = number;
                }
            }
            answer = Math.max(answer, memo);
        }
        System.out.println(answer);

    }


}
