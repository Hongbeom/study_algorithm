package boj.n15728;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N15728 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] share = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            share[i] = Integer.parseInt(st.nextToken());
        }

        int[] team = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            team[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[n];
        for (int i = 0; i < team.length; i++) {
            int t = team[i];
            int sMax = Integer.MIN_VALUE;
            for (int s : share) {
                sMax = Math.max(sMax, s * t);
            }
            result[i] = sMax;
        }
        Arrays.sort(result);
        System.out.println(result[result.length - 1 - k]);
    }
}
