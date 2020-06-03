package boj.n1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N1194 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        boolean[][][] visited = new boolean[n][m][64];
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < map.length; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < map[0].length; j++) {

                if (map[i][j] == '0') {
                    queue.add(new Node(i, j, 0, 0, new boolean[6]));
                    visited[i][j][0] = true;
                }
            }
        }

        int answer = -1;
        root:
        while (!queue.isEmpty()) {

            Node current = queue.poll();
            int a = current.h;
            int b = current.w;
            int currentTime = current.t;
            int currentKeyInt = current.keyInt;
            boolean[] currentKeys = current.keys;

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                    continue;
                }

                // 출구일 경우.
                if (map[na][nb] == '1') {
                    answer = currentTime + 1;
                    break root;
                }

                // 벽일 경우.
                if (map[na][nb] == '#') {
                    continue;
                }

                // 방문 체크.
                if (visited[na][nb][currentKeyInt]) {
                    continue;
                }
                visited[na][nb][currentKeyInt] = true;

                if (97 <= map[na][nb] && map[na][nb] <= 102) {
                    // 열쇠인 경우
                    int key = map[na][nb] - 'a';
                    if (currentKeys[key]) {
                        // key가 이미 있는 경우.
                        queue.add(new Node(na, nb, currentTime + 1, currentKeyInt, currentKeys.clone()));
                    } else {
                        // key를 추가하는 경우.
                        boolean[] nextKeys = currentKeys.clone();
                        nextKeys[key] = true;
                        int nextKeyInt = currentKeyInt + (int) Math.pow(2, key);
                        queue.add(new Node(na, nb, currentTime + 1, nextKeyInt, nextKeys));
                    }

                } else if (65 <= map[na][nb] && map[na][nb] <= 70) {
                    // 문인 경우
                    int door = map[na][nb] - 'A';

                    // 맞는 열쇠가 없는 경우
                    if (!currentKeys[door]) {
                        continue;
                    }
                    queue.add(new Node(na, nb, currentTime + 1, currentKeyInt, currentKeys.clone()));
                } else {
                    queue.add(new Node(na, nb, currentTime + 1, currentKeyInt, currentKeys.clone()));
                }

            }
        }

        System.out.println(answer);
    }

    static class Node {
        int h;
        int w;
        int t;
        int keyInt;
        boolean[] keys;

        Node(int h, int w, int t, int keyInt, boolean[] keys) {
            this.h = h;
            this.w = w;
            this.t = t;
            this.keyInt = keyInt;
            this.keys = keys;
        }
    }
}

