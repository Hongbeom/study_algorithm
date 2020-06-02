package boj.n10906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N10906 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] first = new int[n][2];
        int[][] second = new int[n][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < first.length; i++) {
            first[i][0] = Integer.parseInt(st.nextToken());
            first[i][1] = Integer.parseInt(st.nextToken());

        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < second.length; i++) {
            second[i][0] = Integer.parseInt(st.nextToken());
            second[i][1] = Integer.parseInt(st.nextToken());
        }

        int fullCnt = 0;
        for (int i = 0; i < n; i++) {
            int common = getCommon(first[i][0], first[i][1], second[i][0], second[i][1]);

            if (common == -1) {
                System.out.println(-1);
                return;
            } else if (common > 0) {
                fullCnt++;
            }
        }

        System.out.println(fullCnt);

    }

    static int getCommon(int a, int b, int c, int d) {

        if (d < b) {
            int tmpA = a;
            a = c;
            b = d;
            c = tmpA;
        }

        if (c > b) {
            return -1;
        } else if (c <= a) {
            return b - a;
        } else {
            return b - c;
        }
    }
}
