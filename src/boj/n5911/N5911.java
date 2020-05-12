package boj.n5911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N5911 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        int[] p = new int[n];
        int[] s = new int[n];
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
            sum[i] = p[i] + s[i];
        }
        int ans = -1;

        for (int i = 0; i < n; i++) {
            int[] ts = sum.clone();
            ts[i] = p[i] / 2 + s[i];
            Arrays.sort(ts);
            int tCnt = 0;
            long tSum = 0;
            for (int ss : ts) {
                if (tSum + ss > b) {
                    break;
                }
                tSum += ss;
                tCnt++;
            }
            ans = Math.max(ans, tCnt);
        }
        System.out.println(ans);
    }
}
