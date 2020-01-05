package boj.n5625;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N5625_WRONG {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] width = new int[1000001];
        int[] height = new int[1000001];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {

            int minX = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;

            int minY = Integer.MAX_VALUE;
            int maxY = Integer.MIN_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                if (j % 2 == 0) {
                    int x = Integer.parseInt(st.nextToken());
                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                } else {
                    int y = Integer.parseInt(st.nextToken());
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }

            for (int k = minX + 1; k < maxX; k++) {
                width[k]++;
            }

            for (int k = minY + 1; k < maxY; k++) {
                height[k]++;
            }

        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {

            st = new StringTokenizer(br.readLine(), " = ");
            char question = st.nextToken().charAt(0);
            int v = Integer.parseInt(st.nextToken());

            if (question == 'x') {

                System.out.println(width[v]);
            } else {
                System.out.println(height[v]);
            }

        }
    }
}
