package boj.n2232;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class N2232 {

    static int BOOM = 0;
    static boolean[] BOOMED;
    static int[] MINE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        MINE = new int[n];
        BOOMED = new boolean[n];
        PriorityQueue<Mine> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            MINE[i] = Integer.parseInt(br.readLine());
            pq.add(new Mine(MINE[i], i));
        }
        List<Integer> ans = new ArrayList<>();
        while (BOOM != n) {
            Mine m = pq.poll();
            if (BOOMED[m.id]) {
                continue;
            }
            ans.add(m.id);
            boom(m.id);
        }
        Collections.sort(ans);
        for (int id : ans) {
            System.out.println(id + 1);
        }

    }

    static void boom(int id) {
        BOOMED[id] = true;
        BOOM++;

        boolean left = true;
        boolean right = true;
        int lid = id - 1;
        int rid = id + 1;

        while (left || right) {
            if (lid < 0 || BOOMED[lid]) {
                left = false;
            }
            if (rid >= BOOMED.length || BOOMED[rid]) {
                right = false;
            }

            if (left) {
                int bid = lid + 1;
                if (MINE[bid] > MINE[lid]) {
                    BOOMED[lid] = true;
                    BOOM++;
                    lid--;
                } else {
                    left = false;
                }

            }

            if (right) {
                int bid = rid - 1;
                if (MINE[bid] > MINE[rid]) {
                    BOOMED[rid] = true;
                    BOOM++;
                    rid++;
                } else {
                    right = false;
                }
            }

        }
    }

    static class Mine implements Comparable<Mine> {
        int p;
        int id;

        public Mine(int p, int id) {
            this.p = p;
            this.id = id;
        }

        @Override
        public int compareTo(Mine o) {
            if (this.p > o.p) {
                return -1;
            } else if (this.p < o.p) {
                return 1;
            }
            return 0;
        }
    }
}


