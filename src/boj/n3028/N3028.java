package boj.n3028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N3028 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] mix = br.readLine().toCharArray();

        int position = 1;

        for (char c : mix) {
            switch (c) {
                case 'A':
                    if (position == 1) {
                        position = 2;
                    } else if (position == 2) {
                        position = 1;
                    }
                    break;
                case 'B':
                    if (position == 2) {
                        position = 3;
                    } else if (position == 3) {
                        position = 2;
                    }
                    break;
                case 'C':
                    if (position == 1) {
                        position = 3;
                    } else if (position == 3) {
                        position = 1;
                    }
                    break;
            }
        }
        System.out.println(position);


    }
}
