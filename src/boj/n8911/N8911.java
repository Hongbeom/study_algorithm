package boj.n8911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N8911 {

    static final int[] H = new int[]{1, 0, -1, 0};
    static final int[] W = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            int d = 0;
            int[] spot = new int[2];
            int maxH = 0;
            int minH = 0;
            int maxW = 0;
            int minW = 0;
            char[] moves = br.readLine().toCharArray();

            for (char move : moves) {
                switch (move) {
                    case 'F':
                        spot[0] += H[d];
                        spot[1] += W[d];
                        break;
                    case 'B':
                        spot[0] += H[(d + 2) % 4];
                        spot[1] += W[(d + 2) % 4];
                        break;
                    case 'L':
                        d = (d + 3) % 4;
                        break;
                    case 'R':
                        d = (d + 1) % 4;
                        break;
                }
                maxH = Math.max(spot[0], maxH);
                minH = Math.min(spot[0], minH);

                maxW = Math.max(spot[1], maxW);
                minW = Math.min(spot[1], minW);
            }
            System.out.println((maxH - minH) * (maxW - minW));
        }
    }

}
