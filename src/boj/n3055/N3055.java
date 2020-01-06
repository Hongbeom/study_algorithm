package boj.n3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class N3055 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] dochi = null;

        char[][] map = new char[n][m];
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (char c : br.readLine().toCharArray()) {
                map[i][cnt] = c;
                if (c == '*') {
                    queue.add(new int[]{i, cnt, -1});
                } else if (c == 'S') {
                    dochi = new int[]{i, cnt, 0};
                }
                cnt++;
            }
        }
        queue.add(dochi);

        boolean[][] visited = new boolean[n][m];
        visited[dochi[0]][dochi[1]] = true;
        int answer = -1;

        root:
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int a = current[0];
            int b = current[1];
            int check = current[2];

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                    continue;
                }

                if (check == -1) {
                    if (map[na][nb] == '.' || map[na][nb] == 'S') {
                        map[na][nb] = '*';
                        queue.add(new int[]{na, nb, -1});
                    }
                } else {

                    if (visited[na][nb]) {
                        continue;
                    }

                    if (map[na][nb] == '.') {
                        visited[na][nb] = true;
                        queue.add(new int[]{na, nb, check + 1});
                    } else if (map[na][nb] == 'D') {
                        answer = check + 1;
                        break root;
                    }

                }
            }
        }

        if (answer == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }


    }
}
