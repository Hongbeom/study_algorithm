package boj.n14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N14502 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[n][m];

        List<int[]> virus = new ArrayList<>();
        List<int[]> candidates = new ArrayList<>();

        int safe = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int in = Integer.parseInt(st.nextToken());

                if (in == 1) {
                    visited[i][j] = true;
                } else if (in == 2) {
                    visited[i][j] = true;
                    virus.add(new int[]{i, j});
                } else {
                    safe++;
                    candidates.add(new int[]{i, j});
                }
            }
        }

        safe -= 3;

        int answer = 0;

        for (int i = 0; i < candidates.size() - 2; i++) {
            for (int j = i + 1; j < candidates.size() - 1; j++) {
                for (int k = j + 1; k < candidates.size(); k++) {
                    boolean[][] nv = clone(visited);

                    nv[candidates.get(i)[0]][candidates.get(i)[1]] = true;
                    nv[candidates.get(j)[0]][candidates.get(j)[1]] = true;
                    nv[candidates.get(k)[0]][candidates.get(k)[1]] = true;

                    answer = Math.max(answer, bfs(virus, nv, safe));
                }
            }
        }

        System.out.println(answer);

    }

    static boolean[][] clone(boolean[][] input) {

        boolean[][] re = new boolean[input.length][input[0].length];

        for (int i = 0; i < input.length; i++) {
            System.arraycopy(input[i], 0, re[i], 0, input[i].length);
        }

        return re;
    }

    static int bfs(List<int[]> virus, boolean[][] visited, int safe) {

        Queue<int[]> queue = new LinkedList<>(virus);

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= visited.length || nb >= visited[0].length) {
                    continue;
                }

                if (visited[na][nb]) {
                    continue;
                }

                safe--;
                visited[na][nb] = true;
                queue.add(new int[]{na, nb});
            }

        }

        return safe;
    }
}
