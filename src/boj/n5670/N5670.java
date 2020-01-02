package boj.n5670;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class N5670 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number;

        while ((number = br.readLine()) != null) {
            int n = Integer.parseInt(number);
            Trie root = new Trie('*', true);
            List<char[]> inputs = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                char[] word = br.readLine().toCharArray();
                root.addWord(word);
                inputs.add(word);
            }

            double answer = 0;

            for (char[] word : inputs) {
                answer += root.check(word);
            }

            System.out.println(String.format("%.2f", answer / n));
        }

    }

    static class Trie {

        char c;
        boolean isRoot;
        int childKind;

        Trie[] children;


        Trie(char c, boolean isRoot) {
            this.c = c;
            this.isRoot = isRoot;
            this.childKind = 0;
            children = new Trie[26];
        }

        void addWord(char[] word) {
            Trie t = this;

            for (int i = 0; i < word.length; i++) {
                int id = ((int) word[i]) - 97;

                if (t.children[id] == null) {
                    t.childKind++;
                    t.children[id] = new Trie(c, false);
                }

                t = t.children[id];

                if (i == word.length - 1) {
                    t.childKind++;
                }
            }
        }

        int check(char[] word) {
            int cnt = 1;
            Trie t = this.children[((int) word[0]) - 97];

            for (int i = 1; i < word.length; i++) {

                int id = ((int) word[i]) - 97;

                if (t.childKind != 1) {
                    cnt++;
                }
                t = t.children[id];
            }

            return cnt;
        }
    }
}
