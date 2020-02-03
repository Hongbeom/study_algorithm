package boj.n12999;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N12999 {

    static int[] COUNT = new int[200001];
    static int MAX = Integer.MIN_VALUE;
    static int[] TABLE = new int[100001];
    static int ADJ = 100000;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] paint = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            paint[i] = Integer.parseInt(st.nextToken());
        }

        int[][] queries = new int[q][3];
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i][0] = Integer.parseInt(st.nextToken()) - 1;
            queries[i][1] = Integer.parseInt(st.nextToken()) - 1;
            queries[i][2] = i;
        }

        int[] answer = new int[q];

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


        int preL = 0;
        int preR = 0;
        add(paint[0] + ADJ);
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            // 왼쪽 추가
            for (int i = preL - 1; i >= l; i--) add(paint[i] + ADJ);

            // 오른쪽 추가
            for (int i = preR + 1; i <= r; i++) add(paint[i] + ADJ);

            // 왼쪽 삭제
            for (int i = preL; i < l; i++) erase(paint[i] + ADJ);

            // 오른쪽 삭제
            for (int i = preR; i > r; i--) erase(paint[i] + ADJ);

            preL = l;
            preR = r;
            answer[query[2]] = MAX;
        }

        for (int a : answer) {
            System.out.println(a);
        }


    }


    static void add(int p) {

        if(COUNT[p] != 0){
            TABLE[COUNT[p]]--;
        }
        COUNT[p]++;
        TABLE[COUNT[p]]++;
        MAX = Math.max(MAX, COUNT[p]);

    }

    static void erase(int p) {
        TABLE[COUNT[p]]--;

        if (COUNT[p] == MAX && TABLE[COUNT[p]] == 0) {
            MAX--;
        }

        COUNT[p]--;

        TABLE[COUNT[p]]++;

    }


}