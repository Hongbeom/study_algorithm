package boj.n1501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class N1501_WRONG {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Word>[][] dic = new ArrayList[52][52];


        for (int i = 0; i < n; i++) {
            char[] init = br.readLine().toCharArray();

            int a = (int) init[0];
            int b = (int) init[init.length - 1];

            if (a <= 90) {
                a -= 65;
            } else {
                a = a - 97 + 26;
            }

            if (b <= 90) {
                b -= 65;
            } else {
                b = b - 97 + 26;
            }

            if (dic[a][b] == null) {
                dic[a][b] = new ArrayList<>();
            }
            dic[a][b].add(new Word(init));
        }

        int m = Integer.parseInt(br.readLine());


        for (int i = 0; i < m; i++) {
            String[] words = br.readLine().split(" ");
            int answer = 1;
            for (String w : words) {
                char[] compare = w.toCharArray();

                int a = (int) compare[0];
                int b = (int) compare[compare.length - 1];

                if (a <= 90) {
                    a -= 65;
                } else {
                    a = a - 97 + 26;
                }

                if (b <= 90) {
                    b -= 65;
                } else {
                    b = b - 97 + 26;
                }

                if (dic[a][b] == null) {
                    // 해석할 경우가 없는 경우.
                    System.out.println(0);
                    continue;
                }
                int ca = 0;
                for (Word word : dic[a][b]) {
                    if (word.check(convert(compare), compare.length - 2)) {
                        ca++;
                    }
                }
                answer *= ca;
            }
            System.out.println(answer);
        }
    }


    static class Word {
        int size;
        int[] count;

        Word(char[] init) {
            this.size = init.length - 2;
            this.count = convert(init);
        }


        boolean check(int[] compare, int size) {

            if (this.size != size) {
                return false;
            }

            for (int i = 0; i < this.count.length; i++) {
                if (this.count[i] - compare[i] != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    static int[] convert(char[] word) {

        int[] count = new int[52];
        for (int i = 1; i < word.length - 1; i++) {
            char c = word[i];
            int id = (int) c;

            if (id <= 90) {
                id -= 65;
            } else {
                id = id - 97 + 26;
            }
            count[id]++;
        }

        return count;
    }
}
