package boj.n17822;

import java.io.*;
import java.util.*;

public class N17822 {

    static int[] H = new int[] { -1, 1, 0, 0 };
    static int[] W = new int[] { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");

        int n = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        int t = Integer.parseInt(tmp[2]);

        int[][] circles = new int[n + 1][m];
        int[] top = new int[n + 1];

        int sum = 0;
        int cnt = n * m;

        for (int i = 1; i <= n; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                circles[i][j] = Integer.parseInt(tmp[j]);
                sum += circles[i][j];
            }
        }

        for (int o = 0; o < t; o++) {

            tmp = br.readLine().split(" ");
            int c = Integer.parseInt(tmp[0]);
            int d = Integer.parseInt(tmp[1]);
            int k = Integer.parseInt(tmp[2]);

            // 먼저 회전시키기
            rotate(c, d, k, top, n, m);

            // 인접한 수 제거 (원판이니 양 끝에도 인접한것)
            int deleteCnt = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < m; j++) {
                    int w = (j + (m - top[i])) % m;
                    if (circles[i][w] == 0) {
                        continue;
                    }
                    Queue<int[]> queue = new LinkedList<>();

                    queue.add(new int[] { i, w, circles[i][w] });
                    while (!queue.isEmpty()) {
                        int[] tm = queue.poll();
                        int a = tm[0];
                        int b = tm[1];
                        int cv = tm[2];

                        for (int di = 0; di < 4; di++) {
                            int na = a + H[di];

                            if (na < 1 || na > n) {
                                continue;
                            }
                            int nb;
                            if (H[di] == 0) {
                                nb = b + W[di];
                                if (nb < 0) {
                                    nb = m - 1;
                                } else if (nb >= m) {
                                    nb = 0;
                                }
                            } else {
                                int adj = top[na] - top[a];
                                if (adj > 0) {
                                    nb = (b + adj) % m;
                                } else if (adj < 0) {
                                    nb = (b + (m + adj)) % m;
                                } else {
                                    nb = b;
                                }

                            }

                            if (circles[na][nb] == 0) {
                                continue;
                            }

                            if (circles[na][nb] == cv) {
                                if (circles[a][b] != 0) {
                                    deleteCnt++;
                                    cnt--;
                                    sum -= circles[a][b];
                                    circles[a][b] = 0;
                                }
                                deleteCnt++;
                                cnt--;
                                sum -= circles[na][nb];
                                circles[na][nb] = 0;
                                queue.add(new int[] { na, nb, cv });
                            }

                        }
                    }
                }
            }

            // 보정 프로세스
            if (deleteCnt == 0) {
                double average = (double)sum / (double) cnt;
                for (int i = 1; i <= n; i++) {
                    for (int j = 0; j < m; j++) {
                        if(circles[i][j] == 0) {
                            continue;
                        }
                        if ((double) circles[i][j] > average) {
                            circles[i][j]--;
                            sum--;
                        } else if ((double) circles[i][j] < average) {
                            circles[i][j]++;
                            sum++;
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }

    static void rotate(int c, int d, int k, int[] top, int n, int m) {
        k = k % m;

        if (d == 0) {
            // 시계방향
            int p = c;
            while (p <= n) {
                top[p] = (top[p] + (m - k)) % m;
                p += c;
            }
        } else {
            // 반시계 방향
            int p = c;
            while (p <= n) {
                top[p] = (top[p] + k) % m;
                p += c;
            }
        }

    }
}
