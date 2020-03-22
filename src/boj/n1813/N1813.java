package boj.n1813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1813 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int ans = -1;
        int[] check = new int[100001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int c = Integer.parseInt(st.nextToken());
            check[c]++;
        }

        for (int i = 0; i < check.length; i++) {
            if (i == check[i]) {
                ans = Math.max(ans, i);
            }
        }

        System.out.println(ans);
    }
}
