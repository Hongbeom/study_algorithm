package sw.sw8500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW8500 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            int answer = 0;
            int max = Integer.MIN_VALUE;
            st = new StringTokenizer(br.readLine());
            for (int i = n - 1; i >= 0; i--) {
                int empty = Integer.parseInt(st.nextToken());
                answer += empty + 1;
                max = Math.max(max, empty);
            }
            System.out.println("#" + (tc + 1) + " " + (answer + max));
        }
    }
}
