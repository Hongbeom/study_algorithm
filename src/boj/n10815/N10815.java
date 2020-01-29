package boj.n10815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N10815 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] card = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(card);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int q = Integer.parseInt(st.nextToken());
            if (q == binarySearch(card, 0, card.length - 1, q)) {
                answer.append("1 ");
            } else {
                answer.append("0 ");
            }
        }
        System.out.println(answer.toString());

    }

    static int binarySearch(int[] card, int start, int end, int target) {

        int mid = (start + end) / 2;

        int value = card[mid];

        if (value >= target) {
            if (mid == 0 || card[mid - 1] < target) {
                return value;
            }
            return binarySearch(card, start, mid, target);
        } else {
            if (mid == card.length - 1) {
                return card[card.length - 1];
            }

            return binarySearch(card, mid + 1, end, target);
        }

    }
}
