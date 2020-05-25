package boj.n14868;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N14868 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] visited = new int[n][n];
        int[] parent = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            parent[i] = i;
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()) - 1;
            int h = n - Integer.parseInt(st.nextToken());
            visited[h][w] = i;
            queue.add(new int[]{h, w, i, 0, 0});
        }

        if (k == 1) {
            System.out.println(0);
            return;
        }

        int answer = 0;

        root:
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];
            int v = current[2];
            int y = current[3];
            int c = current[4];

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= visited.length || nb >= visited[0].length) {
                    continue;
                }

                if (c == 0) {

                    if (visited[na][nb] != 0) {
                        int p = union(parent, v, visited[na][nb]);
                        if (p != -1) {
                            if (--k == 1) {
                                answer = y;
                                break root;
                            }
                            visited[na][nb] = p;
                            visited[a][b] = p;
                            v = p;
                        }
                    }
                    queue.add(new int[]{a, b, v, y, 1});

                } else {

                    if (visited[na][nb] == 0) {
                        visited[na][nb] = v;
                        queue.add(new int[]{na, nb, v, y + 1, 0});
                    } else {
                        int p = union(parent, v, visited[na][nb]);
                        if (p != -1) {
                            if (--k == 1) {
                                answer = y + 1;
                                break root;
                            }
                            visited[na][nb] = p;
                        }

                    }
                }

            }
        }

        System.out.println(answer);


    }

    static int getParent(int[] parent, int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = getParent(parent, parent[a]);
    }

    static int union(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);

        if (a == b) {
            return -1;
        } else if (a < b) {
            parent[b] = a;
            return a;
        } else {
            parent[a] = b;
            return b;
        }

    }


}

