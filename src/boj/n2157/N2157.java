package boj.n2157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2157 {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] memo = new int[n + 1][m];
        int[][] taste = new int[n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (b > a) {
                taste[a][b] = Math.max(taste[a][b], Integer.parseInt(st.nextToken()));
            }
        }


        int answer = 0;
        for (int city = 1; city <= n; city++) {
            for (int count = 1; count < m; count++) {
                if (count >= city) {
                    continue;
                }
                if (count == 1) {
                    if (taste[1][city] != 0) {
                        memo[city][count] = taste[1][city];
                    }
                } else {
                    for (int middle = 2; middle < city; middle++) {
                        if (memo[middle][count - 1] != 0 && taste[middle][city] != 0) {
                            memo[city][count] = Math.max(memo[city][count], memo[middle][count - 1] + taste[middle][city]);
                        }
                    }
                }

                if (city == n) {
                    answer = Math.max(answer, memo[city][count]);
                }
            }
        }


        System.out.println(answer);
    }

}
