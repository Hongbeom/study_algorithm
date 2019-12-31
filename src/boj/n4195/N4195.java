package boj.n4195;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class N4195 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        StringTokenizer names;

        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());

            HashMap<String, Integer> nameMap = new HashMap<>();

            int[] parent = new int[200000];
            int[] count = new int[200000];

            Arrays.fill(count, 1);
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }

            int nameId = 0;

            for (int i = 0; i < n; i++) {
                names = new StringTokenizer(br.readLine());

                String f1 = names.nextToken();
                String f2 = names.nextToken();

                int f1Id = nameMap.getOrDefault(f1, -1);
                int f2Id = nameMap.getOrDefault(f2, -1);

                if (f1Id == -1) {
                    f1Id = nameId;
                    nameMap.put(f1, nameId++);
                }

                if (f2Id == -1) {
                    f2Id = nameId;
                    nameMap.put(f2, nameId++);
                }

                System.out.println(union(parent, f1Id, f2Id, count));
            }

        }
    }

    static int getParent(int[] parent, int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = getParent(parent, parent[a]);
    }

    static int union(int[] parent, int a, int b, int[] count) {
        a = getParent(parent, a);
        b = getParent(parent, b);

        if (a < b) {
            parent[b] = a;
            count[a] += count[b];
            return count[a];
        } else if (a > b) {
            parent[a] = b;
            count[b] += count[a];
            return count[b];
        } else {
            return count[a];
        }
    }
}
