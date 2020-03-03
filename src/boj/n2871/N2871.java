package boj.n2871;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class N2871 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        boolean[] selected = new boolean[n];
        char[] word = br.readLine().toCharArray();
        PriorityQueue<Word> pq = new PriorityQueue<>();
        for (int i = 0; i < word.length; i++) {
            pq.add(new Word(word[i], i));
        }

        StringBuilder ssb = new StringBuilder();
        StringBuilder hsb = new StringBuilder();

        int sid = n - 1;
        int wid = 0;
        while (wid < n / 2) {
            // first, sang
            while (selected[sid]) {
                sid--;
            }
            ssb.append(word[sid]);
            selected[sid] = true;
            sid--;

            // second, hee
            while (!pq.isEmpty()) {
                Word w = pq.poll();
                if (!selected[w.index]) {
                    hsb.append(w.w);
                    selected[w.index] = true;
                    break;
                }
            }
            wid++;
        }


        String sang = ssb.toString();
        String hee = hsb.toString();

        if (sang.compareTo(hee) > 0) {
            System.out.println("DA");
        } else {
            System.out.println("NE");
        }

        System.out.println(hee);
    }

    static class Word implements Comparable<Word> {
        char w;
        int index;

        public Word(char w, int index) {
            this.w = w;
            this.index = index;
        }

        @Override
        public int compareTo(Word o) {
            if (this.w > o.w) {
                return 1;
            } else if (this.w < o.w) {
                return -1;
            } else {
                if (this.index > o.index) {
                    return -1;
                } else if (this.index < o.index) {
                    return 1;
                }
            }
            return 0;
        }
    }
}
