package boj.n5625;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N5625 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] wLeft = new int[1000000];
        int[] wRightMost = new int[1000000];
        int[] wRight = new int[1000000];
        int[] wLeftMost = new int[1000000];

        int[] hLeft = new int[1000000];
        int[] hRightMost = new int[1000000];
        int[] hRight = new int[1000000];
        int[] hLeftMost = new int[1000000];


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

            // for width
            wLeftMost[minX]++;
            wRightMost[maxX]++;

            // for height;
            hLeftMost[minY]++;
            hRightMost[maxY]++;

        }

        // for width
        for (int i = 1; i < wLeft.length; i++) {
            wLeft[i] = wLeft[i - 1] + wRightMost[i];
        }

        for (int i = wRightMost.length - 2; i >= 0; i--) {
            wRight[i] = wRight[i + 1] + wLeftMost[i];
        }

        // for height;
        for (int i = 1; i < hLeft.length; i++) {
            hLeft[i] = hLeft[i - 1] + hRightMost[i];
        }

        for (int i = hRightMost.length - 2; i >= 0; i--) {
            hRight[i] = hRight[i + 1] + hLeftMost[i];
        }

        int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {

            st = new StringTokenizer(br.readLine(), " = ");
            char question = st.nextToken().charAt(0);
            int v = Integer.parseInt(st.nextToken());

            if (question == 'x') {

                System.out.println(n - wLeft[v] - wRight[v]);
            } else {

                System.out.println(n - hLeft[v] - hRight[v]);
            }

        }
    }

}

