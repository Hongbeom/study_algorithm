package sw.sw5648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW5648 {

    static int[] H = new int[]{-1, 1, 0, 0};
    static int[] W = new int[]{0, 0, -1, 1};
    static int ADJ = 2000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            List<Atom> atomList = new ArrayList<>();
            short[][] map = new short[4001][4001];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int d = Integer.parseInt(st.nextToken());
                short k = Short.parseShort(st.nextToken());

                int h = ADJ - y;
                int w = ADJ + x;

                map[h][w] = k;

                atomList.add(new Atom(h, w, d, k));
            }

            int answer = 0;
            boolean[] checked = new boolean[n];
            int checkCnt = 0;

            root:
            while (true) {

                for (int i = 0; i < n; i++) {
                    if (checkCnt == n) {
                        break root;
                    }

                    if (checked[i]) {
                        continue;
                    }

                    Atom current = atomList.get(i);
                    int a = current.h;
                    int b = current.w;

                    if (map[a][b] < 0) {
                        checked[i] = true;
                        checkCnt++;
                        map[a][b] = 0;
                        continue;
                    }

                    map[a][b] = 0;


                    int[] nextPosition = current.move();

                    int na = nextPosition[0];
                    int nb = nextPosition[1];

                    if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                        checked[i] = true;
                        checkCnt++;
                        continue;
                    }

                    if (map[na][nb] < 0) {
                        answer += current.k;
                        checked[i] = true;
                        checkCnt++;
                    } else if (map[na][nb] > 0) {
                        answer += map[na][nb] + current.k;
                        checked[i] = true;
                        checkCnt++;
                        map[na][nb] = -1;
                    } else {
                        map[na][nb] = current.k;
                    }

                }

            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    static class Atom {
        int h;
        int w;
        int d;
        short k;
        int step;

        Atom(int h, int w, int d, short k) {
            this.h = h;
            this.w = w;
            this.d = d;
            this.k = k;
            this.step = 0;
        }

        int[] move() {
            this.h += H[d];
            this.w += W[d];
            this.step++;

            return new int[]{this.h, this.w};
        }

    }

}
