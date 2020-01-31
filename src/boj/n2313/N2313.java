package boj.n2313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N2313 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total = 0;

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Case> list = new ArrayList<>();
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            Case max = new Case(Integer.MIN_VALUE, 0, 0);
            Case memo = new Case(Integer.MIN_VALUE, 0, 0);
            for (int i = 0; i < n; i++) {
                int value = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    memo.value = value;
                } else {
                    if (memo.value + value > value) {
                        memo.value = memo.value + value;
                        memo.end = i;
                    } else {
                        memo.value = value;
                        memo.start = i;
                        memo.end = i;
                    }
                }

                if (max.compareTo(memo) < 0) {
                    max.value = memo.value;
                    max.start = memo.start;
                    max.end = memo.end;
                }
            }

            total += max.value;
            list.add(max);
        }

        System.out.println(total);
        for (Case c : list) {
            System.out.println((c.start + 1) + " " + (c.end + 1));
        }


    }

    static class Case implements Comparable<Case> {
        int value;
        int start;
        int end;

        Case(int value, int start, int end) {
            this.value = value;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Case o) {
            if (this.value > o.value) {
                return 1;
            } else if (this.value < o.value) {
                return -1;
            } else {
                if (this.end - this.start > o.end - o.start) {
                    return -1;
                } else if (this.end - this.start < o.end - o.start) {
                    return 1;
                } else {
                    if (this.start > o.start) {
                        return -1;
                    } else if (this.start < o.start) {
                        return 1;
                    }
                }
            }
            return 0;
        }
    }
}
