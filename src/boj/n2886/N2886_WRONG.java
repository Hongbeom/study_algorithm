package boj.n2886;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N2886_WRONG {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    static final int[] DH = new int[]{-1, 1, -1, 1};
    static final int[] DW = new int[]{-1, -1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] train = new char[r][c];

        List<int[]> seats = new ArrayList<>();
        List<int[]> people = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                train[i][j] = tmp[j];

                if (train[i][j] == 'L') {
                    seats.add(new int[]{i, j});
                } else if (train[i][j] == 'X') {
                    people.add(new int[]{i, j});
                }
            }
        }
        int answer = 0;
//        for (int[] seat : seats) {
//            if (BFS(train, seat)) {
//                answer++;
//            }
//        }
//        System.out.println(answer);

        for (int[] seat : seats) {
            double min = Double.MAX_VALUE;
            int minCnt = 0;
            for (int[] person : people) {
                double distance = getDistance(seat, person);

                if (distance < min) {
                    min = distance;
                    minCnt = 1;
                } else if (distance == min) {
                    minCnt++;
                }
            }
            if (minCnt > 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static int getDistance(int a, int b, int[] init) {
        return (int) (Math.pow(a - init[0], 2) + Math.pow(b - init[1], 2));
    }

    static double getDistance(int[] seat, int[] person) {
        return  Math.pow(seat[0] - person[0], 2) + Math.pow(seat[1] - person[1], 2);
    }


    static boolean BFS(char[][] train, int[] init) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] > o2[2]) {
                return 1;
            } else if (o1[2] < o2[2]) {
                return -1;
            }
            return 0;
        });
        boolean[][] visited = new boolean[train.length][train[0].length];
        queue.add(init);
        visited[init[0]][init[1]] = true;
        int currentD = 0;
        int same = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];
            int distance = current[2];

            if (currentD < distance) {
                // 새로운 스텝이 시작됬다는 뜻. 모든것을 초기화 해준다.
                currentD = distance;
                same = 0;
            }

            if (train[a][b] == 'X') {
                if (++same > 1) {
                    return true;
                }
            }

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= train.length || nb >= train[0].length) {
                    continue;
                }

                if (visited[na][nb]) {
                    continue;
                }

                visited[na][nb] = true;
                queue.add(new int[]{na, nb, getDistance(na, nb, init)});
            }

            for (int i = 0; i < 4; i++) {
                int na = a + DH[i];
                int nb = b + DW[i];

                if (na < 0 || nb < 0 || na >= train.length || nb >= train[0].length) {
                    continue;
                }

                if (visited[na][nb]) {
                    continue;
                }

                visited[na][nb] = true;
                queue.add(new int[]{na, nb, getDistance(na, nb, init)});
            }
        }

        return false;

    }
}
