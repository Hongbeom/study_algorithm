package boj.n5557;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N5557 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        long[][] memo = new long[21][numbers.length - 1];
        memo[numbers[0]][0] = 1;
        for (int j = 1; j < memo[0].length; j++) {
            int number = numbers[j];
            for (int i = 0; i < memo.length; i++) {
                long plusCnt = 0;
                long minusCnt = 0;
                if(i - number >= 0){
                    plusCnt = memo[i - number][j - 1];
                }
                if(i + number <= 20){
                    minusCnt = memo[i + number][j - 1];
                }
                memo[i][j] = plusCnt + minusCnt;
            }
        }

        System.out.println(memo[numbers[n - 1]][numbers.length - 2]);
    }
}
