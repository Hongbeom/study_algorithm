package boj.n12869;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N12869 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] scv = new int[4];

        for (int i = 0; i < n; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        ArrayList<int[]> candidate = new ArrayList<>();

        candidate(candidate, new int[3], new int[10], 0);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(scv);
        boolean[][][] visited = new boolean[61][61][61];
        int sc = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int s1 = current[0];
            int s2 = current[1];
            int s3 = current[2];
            int step = current[3];

            if (sc + 1 == step) {
                sc++;
                visited = new boolean[61][61][61];
            }

            if (s1 <= 0 && s2 <= 0 && s3 <= 0) {
                System.out.println(step);
                return;
            }

            for (int[] can : candidate) {

                int ns1 = s1 - can[0] <= 0 ? 0 : s1 - can[0];
                int ns2 = s2 - can[1] <= 0 ? 0 : s2 - can[1];
                int ns3 = s3 - can[2] <= 0 ? 0 : s3 - can[2];

                if (visited[ns1][ns2][ns3]) {
                    continue;
                }
                visited[ns1][ns2][ns3] = true;
                queue.add(new int[]{ns1, ns2, ns3, step + 1});
            }
        }
    }


    static void candidate(List<int[]> list, int[] tm, int[] check, int start) {
        if (start == 3) {
            list.add(tm.clone());
            return;
        }
        for (int i = 1; i <= 9; i *= 3) {
            if (check[i] == 0) {
                check[i]--;
                tm[start] = i;
                candidate(list, tm.clone(), check.clone(), start + 1);
                check[i]++;
            }
        }
    }
}
