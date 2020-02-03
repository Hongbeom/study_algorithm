package boj.n13548;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N13548 {

    static int[] COUNT = new int[100001];
    static int[] TABLE = new int[100001];
    static int MAX = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] input = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            queries[i][0] = Integer.parseInt(st.nextToken()) - 1;
            queries[i][1] = Integer.parseInt(st.nextToken()) - 1;
            queries[i][2] = i;
        }

        Arrays.sort(queries, (o1, o2) -> {
            int size = (int) Math.sqrt(n);
            if (o1[0] / size > o2[0] / size) {
                return 1;
            } else if (o1[0] / size < o2[0] / size) {
                return -1;
            } else {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                }
            }
            return 0;
        });

        int[] answer = new int[m];

        int preL = 0;
        int preR = 0;
        add(input[0]);
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            // 왼쪽 증가
            for (int i = preL - 1; i >= l; i--) add(input[i]);

            // 오른쪽 증가
            for (int i = preR + 1; i <= r; i++) add(input[i]);

            // 왼쪽 감소
            for (int i = preL; i < l; i++) erase(input[i]);

            // 오른쪽 감소
            for (int i = preR; i > r; i--) erase(input[i]);

            preL = l;
            preR = r;
            answer[query[2]] = MAX;

        }

        for (int a : answer) {
            System.out.println(a);
        }

    }

    static void add(int a) {

        if (TABLE[COUNT[a]] != 0) TABLE[COUNT[a]]--;
        COUNT[a]++;
        TABLE[COUNT[a]]++;

        MAX = Math.max(MAX, COUNT[a]);
    }

    static void erase(int a) {

        TABLE[COUNT[a]]--;
        if (COUNT[a] == MAX && TABLE[COUNT[a]] == 0) {
            MAX--;
        }
        COUNT[a]--;
        TABLE[COUNT[a]]++;

    }
}
