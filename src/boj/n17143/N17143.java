package boj.n17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N17143 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[r][c];

        Shark[] sharks = new Shark[m + 1];

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(h, w, s, d, z);
            sharks[i] = shark;
            map[h][w] = i;
        }

        int answer = 0;

        for (int j = 0; j < map[0].length; j++) {

            // 상어 먹는거 체크
            for (int i = 0; i < map.length; i++) {
                if (map[i][j] != 0) {
                    int id = map[i][j];
                    sharks[id].eaten();
                    answer += sharks[id].z;
                    break;
                }
            }

            if (j == map[0].length - 1) {
                break;
            }

            map = new int[r][c];

            for (int id = 1; id < sharks.length; id++) {

                if (sharks[id].eaten) {
                    continue;
                }

                int[] loc = sharks[id].move(r, c);
                int a = loc[0];
                int b = loc[1];

                if (map[a][b] == 0) {
                    map[a][b] = id;
                } else {
                    int lid = map[a][b];

                    if (sharks[lid].z > sharks[id].z) {
                        sharks[id].eaten();
                    } else {
                        map[a][b] = id;
                        sharks[lid].eaten();
                    }

                }
            }

        }
        System.out.println(answer);
    }

    static class Shark {

        int h;
        int w;
        int s;
        int d;
        int z;
        boolean eaten;

        Shark(int h, int w, int s, int d, int z) {
            this.h = h;
            this.w = w;
            this.s = s;
            this.d = d;
            this.z = z;
            this.eaten = false;
        }

        void eaten() {
            this.eaten = true;
        }

        int[] move(int r, int c) {
            int target = 0;

            if (this.d == 0) {
                target = this.h - s;
                if (target < 0) {
                    int divide = target / (r - 1);
                    int left = Math.abs(target % (r - 1));
                    if (divide % 2 == 0) {
                        this.d = 1;
                        this.h = left;
                    } else {
                        this.h = r - 1 - left;
                    }
                } else {
                    this.h = target;
                }

            } else if (this.d == 1) {
                target = this.h + s;

                if (target >= r) {
                    int divide = target / (r - 1);
                    int left = Math.abs(target % (r - 1));

                    if (divide % 2 == 1) {
                        this.d = 0;
                        this.h = r - 1 - left;
                    } else {
                        this.h = left;
                    }
                } else {
                    this.h = target;
                }


            } else if (this.d == 2) {
                target = this.w + s;

                if (target >= c) {
                    int divide = target / (c - 1);
                    int left = Math.abs(target % (c - 1));

                    if (divide % 2 == 1) {
                        this.d = 3;
                        this.w = c - 1 - left;
                    } else {
                        this.w = left;
                    }
                } else {
                    this.w = target;
                }

            } else {
                target = this.w - s;

                if (target < 0) {
                    int divide = target / (c - 1);
                    int left = Math.abs(target % (c - 1));

                    if (divide % 2 == 0) {
                        this.d = 2;
                        this.w = left;
                    } else {
                        this.w = c - 1 - left;
                    }
                } else {
                    this.w = target;
                }
            }


            return new int[]{this.h, this.w};

        }

    }

}

