package boj.n1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N1062 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<char[]> words = new ArrayList<>();


        boolean[] alpha = new boolean[26];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char[] word = br.readLine().toCharArray();
            words.add(word);
            for (char c : word) {

                if (c == 'a' || c == 'n' || c == 'i' || c == 't' || c == 'c') {
                    continue;
                }
                int id = (int) c - 97;
                if (!alpha[id]) {
                    cnt++;
                    alpha[id] = true;
                }

            }
        }

        List<boolean[]> candidates = new ArrayList<>();

        if (k - 5 < 0) {
            System.out.println(0);
            return;
        } else if (cnt > k - 5) {
            getCandidates(candidates, new boolean[26], alpha.clone(), 0, k - 5);
        } else {
            System.out.println(n);
            return;
        }

        int max = 0;

        for (boolean[] can : candidates) {
            int count = 0;

            loop1:
            for (char[] word : words) {

                for (char c : word) {
                    if (c == 'a' || c == 'n' || c == 't' || c == 'i' || c == 'c') {
                        continue;
                    }
                    if (!can[(int) c - 97]) {
                        continue loop1;
                    }
                }

                count++;
            }

            max = Math.max(max, count);
        }

        System.out.println(max);


    }

    static void getCandidates(List<boolean[]> list, boolean[] tm, boolean[] alpha, int start, int end) {

        if (start == end) {
            list.add(tm);
            return;
        }

        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i]) {
                alpha[i] = false;
                tm[i] = true;
                getCandidates(list, tm.clone(), alpha.clone(), start + 1, end);
                tm[i] = false;
            }

        }

    }
}
