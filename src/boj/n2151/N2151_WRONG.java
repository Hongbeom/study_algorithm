package boj.n2151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class N2151_WRONG {

    static int[] H = new int[]{-1, 1, 0, 0};
    static int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] home = new char[n][n];
        Light start = null;
        int[] end = new int[2];
        for (int i = 0; i < n; i++) {
            home[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (home[i][j] == '#') {
                    if (start == null) {
                        int d;
                        if (i == 0) {
                            d = 1;
                        } else if (i == n - 1) {
                            d = 0;
                        } else if (j == 0) {
                            d = 3;
                        } else {
                            d = 2;
                        }
                        start = new Light(i, j, d, 0, new int[n][n]);
                    } else {
                        end[0] = i;
                        end[1] = j;
                    }
                }
            }
        }
        System.out.println(bfs(home, start, end));
    }

    static int bfs(char[][] home, Light start, int[] end) {
        Queue<Light> queue = new LinkedList<>();
        queue.add(start);
        int mirror = 0;
        while (!queue.isEmpty()) {
            Light current = queue.poll();
            int a = current.h;
            int b = current.w;

            int d = current.d;
            int m = current.m;

            int na = a + H[d];
            int nb = b + W[d];

            if (na < 0 || nb < 0 || na >= home.length || nb >= home[0].length) {
                continue;
            }

            if (home[na][nb] == '*') {
                continue;
            }

            if (na == end[0] && nb == end[1]) {
                mirror = m;
                break;
            }

            current.h = na;
            current.w = nb;

            if (current.mirrors[na][nb] != 0) {
                int c = current.mirrors[na][nb] % 2 != 0 ? 1 : 2;
                if (current.mirrors[na][nb] == getCheck(d, c)) {
                    continue;
                }
                current.d = convert(d, current.mirrors[na][nb]);
            } else if (home[na][nb] == '!') {
                int[][] oneMirrors = copy(current.mirrors);
                oneMirrors[na][nb] = getCheck(d, 1);
                queue.add(new Light(na, nb, convert(d, 1), m + 1, oneMirrors));

                int[][] twoMirrors = copy(current.mirrors);
                twoMirrors[na][nb] = getCheck(d, 2);
                queue.add(new Light(na, nb, convert(d, 2), m + 1, twoMirrors));
            }
            queue.add(current);
        }

        return mirror;
    }

    static class Light {
        int h;
        int w;
        int d;
        int m;
        int[][] mirrors;


        public Light(int h, int w, int d, int m, int[][] mirrors) {
            this.h = h;
            this.w = w;
            this.d = d;
            this.m = m;
            this.mirrors = mirrors;
        }
    }

    static int[][] copy(int[][] input) {
        int[][] re = new int[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            System.arraycopy(input[i], 0, re[i], 0, input[i].length);
        }
        return re;
    }

    static int convert(int d, int md) {
        int nd = 0;
        switch (d) {
            case 0:
                nd = md == 1 ? 2 : 3;
                break;
            case 1:
                nd = md == 1 ? 3 : 2;
                break;
            case 2:
                nd = md == 1 ? 0 : 1;
                break;
            case 3:
                nd = md == 1 ? 1 : 0;
                break;
        }
        return nd;
    }

    static int getCheck(int d, int md) {
        int check = 0;
        switch (d) {
            case 0:
                check = md == 1 ? 1 : 2;
                break;
            case 1:
                check = md == 1 ? 3 : 4;
                break;
            case 2:
                check = md == 1 ? 5 : 6;
                break;
            case 3:
                check = md == 1 ? 7 : 8;
                break;
        }
        return check;
    }
}
