package boj.n5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N5052 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        root:
        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            HTrie root = new HTrie('r', true);
            boolean check = true;
            for (int i = 0; i < n; i++) {
                char[] input = br.readLine().toCharArray();
                if (check) {
                    if (!root.insert(input)) {
                        check = false;
                    }
                }
            }
            if (check) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }


    static class HTrie {
        char number;
        boolean isRoot;
        boolean isEnd;
        HTrie[] children;

        HTrie(char number, boolean isRoot) {
            this.number = number;
            this.isRoot = isRoot;
            children = new HTrie[10];
        }

        boolean insert(char[] numbers) {
            HTrie t = this;

            for (int i = 0; i < numbers.length; i++) {
                char c = numbers[i];
                int id = c - '0';

                if (t.children[id] == null) {
                    t.children[id] = new HTrie(c, false);
                } else {
                    if (t.children[id].isEnd && i != numbers.length - 1) {
                        return false;
                    }

                    if (i == numbers.length - 1) {
                        return false;
                    }
                }

                t = t.children[id];

                if (i == numbers.length - 1) {
                    t.isEnd = true;
                }
            }

            return true;
        }
    }
}
