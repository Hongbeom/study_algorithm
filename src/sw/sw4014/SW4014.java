package sw.sw4014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW4014 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            int[][] height = new int[n][n];

            for (int i = 0; i < height.length; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    height[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + tc + " " + check(height, x));

        }
    }


    static int check(int[][] height, int x) {
        int cnt = 0;

        vertical:
        for (int i = 0; i < height.length; i++) {

            int id = 1;
            int bh = height[i][0];
            int sameLength = 1;
            int lastSlopeEnd = -1;
            while (id < height[0].length) {
                int h = height[i][id];
                int diff = Math.abs(bh - h);
                if (diff >= 2) {
                    continue vertical;
                }

                if (h - bh > 0) {
                    if (sameLength < x || id - sameLength <= lastSlopeEnd) {
                        continue vertical;
                    }

                    bh = h;
                    sameLength = 1;
                } else if (h - bh < 0) {
                    if (id + x > height[0].length) {
                        continue vertical;
                    }
                    for (int j = id + 1; j < id + x; j++) {
                        if (height[i][j] != h) {
                            continue vertical;
                        }
                    }
                    lastSlopeEnd = id + x - 1;
                    bh = h;
                    id += x - 1;
                    sameLength = 0;
                } else {
                    sameLength++;
                }

                id++;
            }
            cnt++;
        }

        horizontal:
        for (int j = 0; j < height[0].length; j++) {

            int id = 1;
            int bh = height[0][j];
            int sameLength = 1;
            int lastSlopeEnd = -1;
            while (id < height.length) {
                int h = height[id][j];
                int diff = Math.abs(bh - h);

                if (diff >= 2) {
                    continue horizontal;
                }

                if (h - bh > 0) {
                    if (sameLength < x || id - sameLength <= lastSlopeEnd) {
                        continue horizontal;
                    }
                    bh = h;
                    sameLength = 1;
                } else if (h - bh < 0) {
                    if (id + x > height.length) {
                        continue horizontal;
                    }
                    for (int i = id + 1; i < id + x; i++) {
                        if (height[i][j] != h) {
                            continue horizontal;
                        }
                    }
                    lastSlopeEnd = id + x - 1;
                    bh = h;
                    id += x - 1;
                    sameLength = 0;
                } else {
                    sameLength++;
                }

                id++;
            }
            cnt++;
        }

        return cnt;
    }
}
