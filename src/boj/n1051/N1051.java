package boj.n1051;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1051 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        char[][] map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int limit = Math.min(n - 1 - i, m - 1 - j);
                for (int l = 1; l <= limit; l++) {
                    if (map[i][j] == map[i + l][j] &&
                            map[i + l][j] == map[i + l][j + l] &&
                            map[i + l][j + l] == map[i][j + l]) {
                        answer = Math.max(answer, l + 1);
                    }
                }
            }
        }

        System.out.println(answer * answer);
    }
}
