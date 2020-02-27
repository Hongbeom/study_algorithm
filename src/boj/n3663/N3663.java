package boj.n3663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class N3663 {


    static int finded;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            int changed = 0;
            char[] name = br.readLine().toCharArray();
            finded = Integer.MAX_VALUE;
            List<Integer> change = new ArrayList<>();
            change.add(0);
            for (int i = 0; i < name.length; i++) {
                char c = name[i];
                if (c != 'A') {
                    changed += Math.min(c - 65, 26 - c + 65);
                    if (i != 0) {
                        change.add(i);
                    }
                }
            }
            findPath(0, name.length, 0, 0, change, new boolean[change.size()]);
            System.out.println(changed + finded);
        }
    }

    static void findPath(int now, int length, int distance, int count, List<Integer> change, boolean[] visited) {
        visited[now] = true;

        if (count == visited.length - 1) {
            finded = Math.min(finded, distance);
            return;
        }

        int to = -1;
        int dist = Integer.MAX_VALUE;
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < change.size(); i++) {
            int next = change.get(i);

            if (visited[i]) {
                continue;
            }

            int diff = Math.abs(next - change.get(now));
            int tmDist = Math.min(diff, length - diff);

            if (tmDist < dist) {
                to = i;
                dist = tmDist;
                list.clear();
            } else if (tmDist == dist) {
                list.add(new int[]{i, tmDist});
            }
        }
        findPath(to, length, distance + dist, count + 1, change, visited.clone());
        for (int[] l : list) {
            findPath(l[0], length, distance + l[1], count + 1, change, visited.clone());
        }
    }

}
