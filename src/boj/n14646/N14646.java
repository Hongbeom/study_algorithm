package boj.n14646;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14646 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        int ans = 0;
        boolean[] pan = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            int p = Integer.parseInt(st.nextToken()) - 1;

            if (pan[p]) {
                cnt--;
            } else {
                pan[p] = true;
                cnt++;
                ans = Math.max(cnt, ans);
            }
        }

        System.out.println(ans);
    }
}
