package boj.n9274;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N9274 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in;
        StringTokenizer st;
        while ((in = br.readLine()) != null) {
            st = new StringTokenizer(in);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] map = new char[n + h][m + w];

            // 배열의 크기를 확장시켜 준다.
            for (int i = 0; i < n; i++) {
                char[] tm = br.readLine().toCharArray();
                for (int j = 0; j < m; j++) {
                    map[i + h][j + w] = tm[j];
                }
            }

            // 빈 공간을 배열에 표시해 준다. - 왼쪽 위 기준의 페이지.
            for (int i = 0; i < n + h; i++) {
                int cnt = w;
                for (int j = m + w - 1; j >= 0; j--) {
                    if (map[i][j] != 'X') {
                        if (++cnt >= w) {
                            map[i][j] = '1';
                        }
                    } else {
                        cnt = 0;
                    }
                }
            }

            for (int j = 0; j < m + w; j++) {
                int cnt = h;
                for (int i = n + h - 1; i >= 0; i--) {
                    if (map[i][j] == '1') {
                        if (++cnt >= h) {
                            map[i][j] = '0';
                        }
                    } else {
                        cnt = 0;
                    }
                }
            }

            int answer = Integer.MAX_VALUE;

            for (int hs = 0; hs < h; hs++) {
                for (int ws = 0; ws < w; ws++) {
                    int count = 0;

                    for (int i = hs; i < n + h; i += h) {
                        for (int j = ws; j < m + w; j += w) {
                            if (map[i][j] != '0') {
                                count++;
                            }
                        }
                    }
                    answer = Math.min(answer, count);

                }
            }

            System.out.println(answer);
        }
    }


}
