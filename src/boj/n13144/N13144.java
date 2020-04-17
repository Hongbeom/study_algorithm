package boj.n13144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class N13144 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long answer = 0;
        int[] check = new int[100001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int current = 0;
        int lastId = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());
            if (check[v] == 0 || check[v] - 1 < lastId) {
                current++;
                check[v] = i + 1;
            } else {
                map.put(current, map.getOrDefault(current, 0) + 1);
                int id = check[v] - 1;
                check[v] = i + 1;
                current = i - id;
                answer -= cal(current - 1);
                lastId = id;
            }

            if (i == n - 1) {
                map.put(current, map.getOrDefault(current, 0) + 1);
            }
        }

        for (int key : map.keySet()) {
            long h = map.get(key);
            answer += h * cal(key);
        }
        System.out.println(answer);
    }

    static long cal(int a) {
        long n = a;
        return n * (n + 1) / 2;
    }
}
