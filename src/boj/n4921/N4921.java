package boj.n4921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N4921 {

    static boolean[][] CHECK = new boolean[][]{
            // 1
            {
                    false, false, false, true, true, false, false, false
            },
            // 2
            {
                    false, false, false, false, false, false, false, false
            },
            // 3
            {
                    false, false, false, true, true, false, false, false
            },
            // 4
            {
                    false, true, true, false, false, false, false, false
            },
            // 5
            {
                    false, false, false, false, false, false, false, true
            },
            // 6
            {
                    false, true, true, false, false, false, false, false
            },
            // 7
            {
                    false, false, false, false, false, false, false, true
            },
            // 8
            {
                    false, false, false, false, false, true, true, false
            }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 0;
        TestCase:
        while (true) {
            tc++;
            char[] blocks = br.readLine().toCharArray();
            if (blocks.length == 1 && blocks[0] == '0') {
                return;
            }

            if (blocks[0] - '0' != 1 || blocks[blocks.length - 1] - '0' != 2) {
                System.out.println(tc + ". NOT");
                continue;
            }

            for (int i = 0; i < blocks.length - 1; i++) {
                int current = blocks[i] - '0';
                int back = blocks[i + 1] - '0';

                if (!CHECK[current - 1][back - 1]) {
                    System.out.println(tc + ". NOT");
                    continue TestCase;
                }
            }

            System.out.println(tc + ". VALID");
        }
    }
}
