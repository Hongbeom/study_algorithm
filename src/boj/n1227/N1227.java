package boj.n1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N1227 {

    static int ADJ = 1000;

    static int N = ADJ * 2 + 1;
    static int M = ADJ * 2 + 1;

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int b = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());


        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + ADJ;
            int y = Integer.parseInt(st.nextToken()) + ADJ;
            visited[x][y] = true;
        }

        int[] init = new int[]{ADJ, ADJ, 0};
        long sanguen = 1;
        long jungin = 0;
        long cnt = 0;

        visited[ADJ][ADJ] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(init);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];
            int step = current[2];

            int check = step % 2;

            if (step > 0 && step <= s) {
                if (check == 0) {
                    sanguen++;
                } else {
                    jungin++;
                }

                if (step == s) {
                    continue;
                }
            }


            if (x == 0 || x == N - 1 || y == M - 1 || y == 0) {

                int plus = s - step;


                if ((x == N - 1 && y == 0) || (x == 0 && y == M - 1) || (x == 0 && y == 0) || (x == N - 1 && y == M - 1)) {

                    if (plus % 2 == 0) {
                        sanguen += (plus / 2) * 2;
                        jungin += (plus / 2) * 2;
                    } else {
                        if (check == 0) {
                            sanguen += (plus / 2) * 2;
                            jungin += (plus / 2 + 1) * 2;
                        } else {
                            sanguen += (plus / 2 + 1) * 2;
                            jungin += (plus / 2) * 2;
                        }
                    }

                    for (int i = 1; i < plus; i++) {
                        if (i % 2 == 1) {
                            sanguen += i;
                        } else {
                            jungin += i;
                        }
                    }

                } else {

                    if (plus % 2 == 0) {
                        sanguen += plus / 2;
                        jungin += plus / 2;
                    } else {
                        if (check == 0) {
                            sanguen += plus / 2;
                            jungin += plus / 2 + 1;
                        } else {
                            sanguen += plus / 2 + 1;
                            jungin += plus / 2;
                        }
                    }


                }
            }

            for (int i = 0; i < 4; i++) {

                int nx = x + H[i];
                int ny = y + W[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny, step + 1});
            }
        }

        System.out.println(sanguen + " " + jungin);
    }
}
