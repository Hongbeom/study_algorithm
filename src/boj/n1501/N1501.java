package boj.n1501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N1501 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<char[]>[][] dic = new ArrayList[52][52];


        for (int i = 0; i < n; i++) {
            char[] init = br.readLine().toCharArray();

            int a = (int) init[0];
            int b = (int) init[init.length - 1];

            init[0] = (char) 64;
            init[init.length - 1] = (char) 64;

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
            Arrays.sort(init);
            dic[a][b].add(init);
        }

        int m = Integer.parseInt(br.readLine());


        origin:
        for (int i = 0; i < m; i++) {
            String[] words = br.readLine().split(" ");
            int answer = 1;
            for (String w : words) {
                char[] compare = w.toCharArray();

                int a = (int) compare[0];
                int b = (int) compare[compare.length - 1];

                compare[0] = (char) 64;
                compare[compare.length - 1] = (char) 64;

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
                    continue origin;
                }
                int ca = 0;
                Arrays.sort(compare);
                for (char[] word : dic[a][b]) {
                    if (word.length != compare.length) {
                        continue;
                    }
                    if (Arrays.equals(word, compare)) {
                        ca++;
                    }
                }
                answer *= ca;
            }
            System.out.println(answer);
        }
    }
}
