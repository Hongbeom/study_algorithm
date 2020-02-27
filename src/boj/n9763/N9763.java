package boj.n9763;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N9763 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] towns = new int[n][3];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            towns[i][0] = Integer.parseInt(st.nextToken());
            towns[i][1] = Integer.parseInt(st.nextToken());
            towns[i][2] = Integer.parseInt(st.nextToken());
        }

        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {

                int distance = Math.abs(towns[i][0] - towns[j][0]) + Math.abs(towns[i][1] - towns[j][1]) + Math.abs(towns[i][2] - towns[j][2]);

                if (minDist[i] != Integer.MAX_VALUE || minDist[j] != Integer.MAX_VALUE) {
                    answer = Math.min(answer, Math.min(minDist[i], minDist[j]) + distance);
                }

                minDist[i] = Math.min(minDist[i], distance);
                minDist[j] = Math.min(minDist[j], distance);
            }
        }
        System.out.println(answer);
    }
}
