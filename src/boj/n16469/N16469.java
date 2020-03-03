package boj.n16469;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N16469 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    static char[][] map;
    static int[][][] time;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        time = new int[r][c][2];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        st = new StringTokenizer(br.readLine());
        int[] nucksal = new int[3];
        nucksal[0] = Integer.parseInt(st.nextToken()) - 1;
        nucksal[1] = Integer.parseInt(st.nextToken()) - 1;
        bfs(nucksal);

        st = new StringTokenizer(br.readLine());
        int[] swings = new int[3];
        swings[0] = Integer.parseInt(st.nextToken()) - 1;
        swings[1] = Integer.parseInt(st.nextToken()) - 1;
        bfs(swings);

        st = new StringTokenizer(br.readLine());
        int[] changmo = new int[3];
        changmo[0] = Integer.parseInt(st.nextToken()) - 1;
        changmo[1] = Integer.parseInt(st.nextToken()) - 1;

        int[] answer = bfs(changmo);

        if(answer == null){
            System.out.println(-1);
        }else{
            System.out.println(answer[0]);
            System.out.println(answer[1]);
        }
    }

    static int[] bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[start[0]][start[1]] = true;
        queue.add(start);

        int minT = Integer.MAX_VALUE;
        int count = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];
            int t = current[2];

            time[a][b][0] += 1;
            time[a][b][1] = Math.max(time[a][b][1], t);

            if (time[a][b][0] == 3) {

                if (time[a][b][1] < minT) {
                    minT = time[a][b][1];
                    count = 1;
                }else if(time[a][b][1] == minT){
                    count++;
                }
            }

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                    continue;
                }

                if (visited[na][nb]) {
                    continue;
                }

                if (map[na][nb] == '0') {
                    visited[na][nb] = true;
                    queue.add(new int[]{na, nb, t + 1});
                }

            }
        }

        if(minT == Integer.MAX_VALUE){
            return null;
        }else{
            return new int[]{minT, count};
        }
    }

}
