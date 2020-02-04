package boj.n13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N13460 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] board = new char[n][m];

        Queue<int[]> queue = new LinkedList<>();
        int[] init = new int[5];
        int[] target = new int[2];
        for (int i = 0; i < n; i++) {
            char[] tm = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = tm[j];
                if (tm[j] == 'R') {
                    init[0] = i;
                    init[1] = j;
                } else if (tm[j] == 'B') {
                    init[2] = i;
                    init[3] = j;
                } else if (tm[j] == 'O') {
                    target[0] = i;
                    target[1] = j;
                }
            }
        }
        queue.add(init);
        boolean[][][][] visited = new boolean[n][m][n][m];
        visited[init[0]][init[1]][init[2]][init[3]] = true;
        int answer = -1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int rh = current[0];
            int rw = current[1];
            int bh = current[2];
            int bw = current[3];
            int step = current[4];

            if (step > 10) {
                continue;
            }

            if ((rh == target[0] && rw == target[1]) &&
                    (bh != target[0] || bw != target[1])) {
                answer = step;
                break;
            }
            // 왼쪽으로 기울이기
            int nrw = rw;
            int nbw = bw;
            if (rw < bw) {
                // 빨간구슬 이동.
                while (nrw > 0) {
                    nrw--;
                    if (board[rh][nrw] == 'O') {
                        break;
                    }
                    if (board[rh][nrw] == '#') {
                        nrw++;
                        break;
                    }
                }
                // 파란구슬 이동
                while (nbw > 0) {
                    nbw--;

                    if (board[bh][nbw] == 'O') {
                        break;
                    }

                    if (board[bh][nbw] == '#' || check(rh, nrw, bh, nbw)) {
                        nbw++;
                        break;
                    }
                }

            } else {
                // 파란구슬 이동
                while (nbw > 0) {
                    nbw--;
                    if (board[bh][nbw] == 'O') {
                        break;
                    }

                    if (board[bh][nbw] == '#') {
                        nbw++;
                        break;
                    }

                }

                // 빨간구슬 이동
                while (nrw > 0) {
                    nrw--;
                    if (board[rh][nrw] == 'O') {
                        break;
                    }

                    if (board[rh][nrw] == '#' || check(rh, nrw, bh, nbw)) {
                        nrw++;
                        break;
                    }


                }

            }

            // 파란 구슬이 타겟 X, visited. 왼
            if (board[bh][nbw] != 'O' && !visited[rh][nrw][bh][nbw]) {
                visited[rh][nrw][bh][nbw] = true;
                queue.add(new int[]{rh, nrw, bh, nbw, step + 1});

            }


            // 오른쪽으로 기울이기
            nrw = rw;
            nbw = bw;
            if (rw > bw) {
                // 빨간구슬 이동.
                while (nrw < m - 1) {
                    nrw++;
                    if (board[rh][nrw] == 'O') {
                        break;
                    }

                    if (board[rh][nrw] == '#') {
                        nrw--;
                        break;
                    }

                }
                // 파란구슬 이동
                while (nbw < m - 1) {
                    nbw++;

                    if (board[bh][nbw] == 'O') {
                        break;
                    }

                    if (board[bh][nbw] == '#' || check(rh, nrw, bh, nbw)) {
                        nbw--;
                        break;
                    }

                }

            } else {
                // 파란구슬 이동
                while (nbw < m - 1) {
                    nbw++;

                    if (board[bh][nbw] == 'O') {
                        break;
                    }

                    if (board[bh][nbw] == '#') {
                        nbw--;
                        break;
                    }

                }

                // 빨간구슬 이동
                while (nrw < m - 1) {
                    nrw++;

                    if (board[rh][nrw] == 'O') {
                        break;
                    }

                    if (board[rh][nrw] == '#' || check(rh, nrw, bh, nbw)) {
                        nrw--;
                        break;
                    }


                }

            }

            // 파란 구슬이 타겟 X, visited.쪽 오른
            if (board[bh][nbw] != 'O' && !visited[rh][nrw][bh][nbw]) {
                visited[rh][nrw][bh][nbw] = true;
                queue.add(new int[]{rh, nrw, bh, nbw, step + 1});

            }


            // 위로 기울이기
            int nrh = rh;
            int nbh = bh;
            if (rh < bh) {
                // 빨간구슬 이동.
                while (nrh > 0) {
                    nrh--;
                    if (board[nrh][rw] == 'O') {
                        break;
                    }

                    if (board[nrh][rw] == '#') {
                        nrh++;
                        break;
                    }

                }
                // 파란구슬 이동
                while (nbh > 0) {
                    nbh--;

                    if (board[nbh][bw] == 'O') {
                        break;
                    }

                    if (board[nbh][bw] == '#' || check(nrh, rw, nbh, bw)) {
                        nbh++;
                        break;
                    }
                }

            } else {
                // 파란구슬 이동
                while (nbh > 0) {
                    nbh--;
                    if (board[nbh][bw] == 'O') {
                        break;
                    }
                    if (board[nbh][bw] == '#') {
                        nbh++;
                        break;
                    }

                }

                // 빨간구슬 이동
                while (nrh > 0) {
                    nrh--;
                    if (board[nrh][rw] == 'O') {
                        break;
                    }
                    if (board[nrh][rw] == '#' || check(nrh, rw, nbh, bw)) {
                        nrh++;
                        break;
                    }

                }

            }

            // 파란 구슬이 타겟 X, visited. 위
            if (board[nbh][bw] != 'O' && !visited[nrh][rw][nbh][bw]) {
                visited[nrh][rw][nbh][bw] = true;
                queue.add(new int[]{nrh, rw, nbh, bw, step + 1});

            }

            // 아래로 기울이기

            nrh = rh;
            nbh = bh;
            if (rh > bh) {
                // 빨간구슬 이동.
                while (nrh < n - 1) {
                    nrh++;

                    if (board[nrh][rw] == 'O') {
                        break;
                    }

                    if (board[nrh][rw] == '#') {
                        nrh--;
                        break;
                    }

                }
                // 파란구슬 이동
                while (nbh < n - 1) {
                    nbh++;
                    if (board[nbh][bw] == 'O') {
                        break;
                    }
                    if (board[nbh][bw] == '#' || check(nrh, rw, nbh, bw)) {
                        nbh--;
                        break;
                    }
                }

            } else {
                // 파란구슬 이동
                while (nbh < n - 1) {
                    nbh++;
                    if (board[nbh][bw] == 'O') {
                        break;
                    }
                    if (board[nbh][bw] == '#') {
                        nbh--;
                        break;
                    }
                }

                // 빨간구슬 이동
                while (nrh < n - 1) {
                    nrh++;
                    if (board[nrh][rw] == 'O') {
                        break;
                    }
                    if (board[nrh][rw] == '#' || check(nrh, rw, nbh, bw)) {
                        nrh--;
                        break;
                    }
                }

            }

            // 파란 구슬이 타겟 X, visited. 아래
            if (board[nbh][bw] != 'O' && !visited[nrh][rw][nbh][bw]) {
                visited[nrh][rw][nbh][bw] = true;
                queue.add(new int[]{nrh, rw, nbh, bw, step + 1});

            }

        }

        System.out.println(answer);

    }

    static boolean check(int a, int b, int c, int d) {
        return a == c && b == d;
    }
}
