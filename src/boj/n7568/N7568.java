package boj.n7568;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N7568 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] bodies = new int[n][2];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            bodies[i][0] = Integer.parseInt(st.nextToken());
            bodies[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            int[] p1 = bodies[i];
            for (int j = i + 1; j < n; j++) {
                int[] p2 = bodies[j];
                int c = compare(p1, p2);
                if (c == -1) {
                    rank[j]++;
                } else if (c == 1) {
                    rank[i]++;
                }
            }
        }

        for (int r : rank) {
            System.out.print((r + 1) + " ");
        }
    }

    static int compare(int[] p1, int[] p2) {
        if (p1[0] > p2[0] && p1[1] > p2[1]) {
            return -1;
        } else if (p1[0] < p2[0] && p1[1] < p2[1]) {
            return 1;
        }
        return 0;
    }
}
