package boj.n9278;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N9278 {

    static int ADJ = 1000000;
    static int MAX = 1000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in;

        while ((in = br.readLine()) != null) {

            char[] line = in.toCharArray();

            if (line[0] == ')') {
                System.out.println(0);
                continue;
            }

            System.out.println(calculate(line));
        }
    }

    static int calculate(char[] line) {

        int[] num = new int[MAX * 2 + 1];
        int[] num2 = new int[MAX * 2 + 1];
        int[] change;

        int start = MAX;
        int end = MAX;

        num[start] = 1;

        for (char c : line) {

            if (c == '(') {
                num[--start] = 0;
            } else if (c == ')') {
                start++;
            } else {
                num2[end + 1] = num[end];

                if (start < end) {
                    num2[start] = num[start + 1];
                    num2[end] = num[end - 1];
                } else {
                    num2[start] = 0;
                    num2[end] = 0;
                }

                for (int i = start + 1; i < end; i++) {
                    num2[i] = (num[i - 1] + num[i + 1]) % ADJ;
                }
                end++;
                change = num;
                num = num2;
                num2 = change;
            }

            if (start > end) {
                num[start] = 0;
                break;
            }
        }

        return num[start];
    }


}

