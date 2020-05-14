package sw.sw5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW5656 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            int[][] blocks = new int[h][w];
            int blocksCnt = 0;
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    blocks[i][j] = Integer.parseInt(st.nextToken());
                    if (blocks[i][j] != 0) {
                        blocksCnt++;
                    }
                }
            }
            List<int[]> candidates = new ArrayList<>();
            createCase(candidates, new int[n], 0, w);

            int answer = blocksCnt;

            for (int[] can : candidates) {
                int tmp = blocksCnt;
                int[][] tmBlocks = clone(blocks);
                for (int c : can) {
                    tmp -= destroy(tmBlocks, c);
                }
                answer = Math.min(answer, tmp);
            }

            System.out.println("#" + tc + " " + answer);

        }
    }


    static int destroy(int[][] blocks, int w) {

        int h = -1;
        int cnt = 0;
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i][w] != 0) {
                h = i;
                break;
            }
        }

        if (h == -1) {
            return 0;
        }

        if (blocks[h][w] == 1) {
            blocks[h][w] = 0;
            return 1;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{h, w, blocks[h][w]});
        blocks[h][w] = 0;
        cnt++;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];
            int p = current[2];

            for (int i = 1; i < p; i++) {
                for (int d = 0; d < 4; d++) {
                    int na = a + i * H[d];
                    int nb = b + i * W[d];

                    if (na < 0 || nb < 0 || na >= blocks.length || nb >= blocks[0].length) {
                        continue;
                    }

                    if (blocks[na][nb] != 0) {
                        if (blocks[na][nb] > 1) {
                            queue.add(new int[]{na, nb, blocks[na][nb]});
                        }
                        blocks[na][nb] = 0;
                        cnt++;
                    }
                }
            }
        }

        refresh(blocks);

        return cnt;
    }


    static void refresh(int[][] blocks) {


        for (int j = 0; j < blocks[0].length; j++) {
            int cid = -1;
            for (int i = blocks.length - 1; i >= 0; i--) {
                if (blocks[i][j] == 0) {
                    cid = Math.max(cid, i);
                } else {
                    if (cid != -1) {
                        blocks[cid][j] = blocks[i][j];
                        blocks[i][j] = 0;
                        cid--;
                    }
                }
            }
        }
    }

    static int[][] clone(int[][] src) {
        int[][] dest = new int[src.length][src[0].length];
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
        }
        return dest;
    }

    static void createCase(List<int[]> candidates, int[] tm, int step, int w) {

        if (step == tm.length) {
            candidates.add(tm);
            return;
        }

        for (int i = 0; i < w; i++) {
            tm[step] = i;
            createCase(candidates, tm.clone(), step + 1, w);
        }
    }
}
