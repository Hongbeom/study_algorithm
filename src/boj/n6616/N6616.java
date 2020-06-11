package boj.n6616;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N6616 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        StringTokenizer st;
        while((n = Integer.parseInt(br.readLine())) != 0){
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                sb.append(st.nextToken());
            }
            char[] message = sb.toString().toCharArray();
            char[] cipherText = new char[message.length];

            int sid = 0;
            int cid = sid;
            for (char c : message) {
                char m = c;
                if (m >= 97) {
                    m -= 32;
                }
                cipherText[cid] = m;
                cid += n;
                if (cid >= message.length) {
                    cid = ++sid;
                }
            }
            System.out.println(new String(cipherText));
        }

    }
}
