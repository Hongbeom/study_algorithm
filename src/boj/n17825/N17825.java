package boj.n17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N17825 {


    static final int[] MAP = new int[]

            // 0 ~ 4
            {0, 2, 4, 6, 8,

                    // 5, 6, 7, 8
                    10, 13, 16, 19,

                    // 9 ~ 12
                    12, 14, 16, 18,

                    // 13, 14, 15
                    20, 22, 24,

                    // 16 ~ 19
                    22, 24, 26, 28,

                    // 20, 21, 22, 23
                    30, 28, 27, 26,

                    // 24, 25, 26
                    25, 30, 35,

                    // 27 ~ 31
                    32, 34, 36, 38, 40,

                    // last 32
                    0};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] move = new int[10];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            move[i] = Integer.parseInt(st.nextToken());
        }

        int[] status = new int[6];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(status);
        int answer = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int sum = current[4];
            int t = current[5];

            if (t >= move.length) {
                answer = Math.max(answer, sum);
                continue;
            }

            int m = move[t];

            for (int i = 0; i < 4; i++) {

                if (current[i] == 32) {
                    continue;
                }

                int v = move(current[i], m);

                if (check(current, i, v)) {
                    continue;
                }


                int[] next = current.clone();
                next[i] = v;
                next[4] += MAP[v];
                next[5] = t + 1;
                queue.add(next);
            }
        }

        System.out.println(answer);
    }

    static boolean check(int[] status, int id, int value) {

        if (value == 32) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (i == id || status[i] == 32) {
                continue;
            }
            if (status[i] == value) {
                return true;
            }
        }

        return false;
    }


    static int move(int i, int m) {
        int next = i + m;

        if (i < 5) {
            if (next > 5) {
                m = m - (5 - i) - 1;
                i = 9;
                return move(i, m);
            }
        } else if (5 <= i && i <= 8) {
            if (next > 8) {
                m = m - (8 - i) - 1;
                i = 24;
                return move(i, m);
            }
        } else if (9 <= i && i <= 12) {
            if (next > 13) {
                m = m - (13 - i) - 1;
                i = 16;
                return move(i, m);
            }
        } else if (13 <= i && i <= 15) {
            if (next > 15) {
                m = m - (15 - i) - 1;
                i = 24;
                return move(i, m);
            }
        } else if (16 <= i && i <= 19) {
            if (next > 20) {
                m = m - (20 - i) - 1;
                i = 27;
                return move(i, m);
            }
        } else if (20 <= i && i <= 23) {
            if (next > 23) {
                m = m - (23 - i) - 1;
                i = 24;
                return move(i, m);
            }
        } else if (24 <= i && i <= 26) {
            if (next > 27) {
                return 32;
            } else if (next == 27) {
                return 31;
            }
        } else if (27 <= i) {
            if (next > 31) {
                return 32;
            }
        }
        return next;
    }
}
