package boj.n13547;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N13547 {

    static int[] CNT = new int[1000001];
    static int[] IN;
    static int ANS = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        IN = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            IN[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        List<int[]> queries = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            queries.add(new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, i});
        }
        int[] answer = new int[m];
        queries.sort((o1, o2) -> {
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


        add(0);
        int preL = 0;
        int preR = 0;
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            // l1 > l2 인 경우, 구간이 늘어났으므로 노드 추가
            for (int i = preL - 1; i >= l; i--) add(i);

            // r1 < r2 인 경우, 구간이 늘어났으므로 노드 추가
            for (int i = preR + 1; i <= r; i++) add(i);

            // l1 < l2 인 경우, 구간이 줄었으므로 노드 삭제
            for (int i = preL; i < l; i++) erase(i);

            for (int i = preR; i > r; i--) erase(i);

            preL = l;
            preR = r;
            answer[query[2]] = ANS;
        }

        for (int a : answer) {
            System.out.println(a);
        }
    }


    static void add(int id) {
        if (CNT[IN[id]]++ == 0) {
            ANS++;
        }
    }

    static void erase(int id) {
        if (--CNT[IN[id]] == 0) {
            ANS--;
        }
    }
}
