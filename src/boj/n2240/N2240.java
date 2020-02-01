package boj.n2240;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2240 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][][] memo = new int[3][t + 1][w + 2];

        int[] plum = new int[t + 1];

        for (int i = 1; i <= t; i++) {
            plum[i] = Integer.parseInt(br.readLine());
        }


        // 자두가 떨어지는것에 대한 루프.
        for (int i = 1; i <= t; i++) {

            // 몇번 움직였나에 대한 루프.
            for (int j = 1; j <= w + 1; j++) {

                // 자두가 1의 자리에 떨어질 경우.
                if (plum[i] == 1) {

                    // 자두가 1의 자리에 떨어졌을경우. 이전에 1의 자리에 있었을때와 2의자리에서 움직여서 먹었을때의 최대값으로 갱신한다.
                    memo[1][i][j] = Math.max(memo[1][i - 1][j], memo[2][i - 1][j - 1]) + 1;

                    // 2의 자리의 값을 이전에서 자두가 움직였을때와 그대로 있었을때 중의 최대값으로 갱신해 준다.
                    memo[2][i][j] = Math.max(memo[1][i - 1][j - 1], memo[2][i - 1][j]);

                } else { // 자두가 2의 자리에 떨어질 경우.
                    if (i == 1 && j == 1) {
                        // 첫 시작이 2일 경우.
                        // j 가 1이라는 의미는 움직임이 0임을 뜻한다. -> 무조건 움직여서 받아야 하기 때문에 못먹는 상황.
                        continue;
                    }

                    // 자두가 2의 자리에 떨어졌을경우. 이전에 2의 자리에 있었을때와 1의자리에서 움직여서 먹었을때의 최대값으로 갱신한다.
                    memo[2][i][j] = Math.max(memo[2][i - 1][j], memo[1][i - 1][j - 1]) + 1;

                    // 1의 자리의 값을 이전에서 자두가 움직였을때와 그대로 있었을때 중의 최대값으로 갱신해 준다.
                    memo[1][i][j] = Math.max(memo[2][i - 1][j - 1], memo[1][i - 1][j]);
                }
            }
        }
        int answer = Integer.MIN_VALUE;

        // 끝에서 몇번 움직였는가에 대해 최대값을 뽑는다.
        for (int i = 1; i <= w + 1; i++) {
            answer = Math.max(answer, Math.max(memo[1][t][i], memo[2][t][i]));
        }

        System.out.println(answer);
    }
}
