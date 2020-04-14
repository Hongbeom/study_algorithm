package boj.n2757;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N2757 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            char[] rc = br.readLine().toCharArray();
            int cid = -1;
            int r = 0;
            int c = 0;
            for (int i = 2; i < rc.length; i++) {
                if (rc[i] == 'C') {
                    cid = i;
                    break;
                }
            }
            for (int i = 1; i < cid; i++) {
                r += (rc[i] - '0') * Math.pow(10, cid - i - 1);
            }
            int ex = 0;
            for (int i = rc.length - 1; i > cid; i--) {
                c += (rc[i] - '0') * Math.pow(10, ex);
                ex++;
            }

            if (r == 0 && c == 0) {
                return;
            }
            System.out.println(make(c).toString() + r);
        }
    }

    static StringBuilder make(int c) {
        StringBuilder sb = new StringBuilder();
        c -= 1;
        while (c >= 0) {
            sb.append((char) ((c % 26) + 65));
            c /= 26;
            c -= 1;
        }
        return sb.reverse();
    }
}
