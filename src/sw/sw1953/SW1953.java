package sw.sw1953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW1953 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            int[][] map = new int[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[n][m];
            visited[r][c] = true;

            queue.add(new int[]{r, c, 1});

            int answer = 0;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                int a = current[0];
                int b = current[1];
                int t = current[2];

                answer++;

                if (t == l) {
                    continue;
                }

                int[] direction;

                switch (map[a][b]) {
                    case 1:
                        direction = new int[]{0, 1, 2, 3};
                        break;
                    case 2:
                        direction = new int[]{0, 1};
                        break;
                    case 3:
                        direction = new int[]{2, 3};
                        break;
                    case 4:
                        direction = new int[]{0, 3};
                        break;
                    case 5:
                        direction = new int[]{1, 3};
                        break;
                    case 6:
                        direction = new int[]{1, 2};
                        break;
                    default:
                        direction = new int[]{0, 2};
                        break;
                }


                for (int d : direction) {

                    int na = a + H[d];
                    int nb = b + W[d];
                    int nt = t + 1;

                    if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                        continue;
                    }

                    if (map[na][nb] == 0) {
                        continue;
                    }

                    if (d == 0) {
                        if (!(map[na][nb] == 1 ||
                                map[na][nb] == 2 ||
                                map[na][nb] == 5 ||
                                map[na][nb] == 6)) {
                            continue;
                        }
                    } else if (d == 1) {
                        if (!(map[na][nb] == 1 ||
                                map[na][nb] == 2 ||
                                map[na][nb] == 4 ||
                                map[na][nb] == 7)) {
                            continue;
                        }
                    } else if (d == 2) {
                        if (!(map[na][nb] == 1 ||
                                map[na][nb] == 3 ||
                                map[na][nb] == 4 ||
                                map[na][nb] == 5)) {
                            continue;
                        }
                    } else {
                        if (!(map[na][nb] == 1 ||
                                map[na][nb] == 3 ||
                                map[na][nb] == 6 ||
                                map[na][nb] == 7)) {
                            continue;
                        }
                    }

                    if (visited[na][nb]) {
                        continue;
                    }

                    visited[na][nb] = true;
                    queue.add(new int[]{na, nb, nt});
                }
            }

            System.out.println("#" + tc + " " + answer);

        }
    }
}
