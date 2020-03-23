package boj.n6996;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N6996 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        tc:
        for (int t = 0; t < T; t++) {
            int cnt = 0;
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            if (a.equals(b)) {
                ans(a, b, false);
                continue;

            }
            int[] aChar = new int[26];
            for (char c : a.toCharArray()) {
                aChar[c - 'a']++;
            }

            for (char c : b.toCharArray()) {
                int id = c - 'a';
                if (aChar[id] == 0) {
                    ans(a, b, false);
                    continue tc;
                }
                aChar[id]--;
                cnt++;
            }
            if (cnt == a.length()) {
                ans(a, b, true);
            } else {
                ans(a, b, false);
            }
        }
    }

    static void ans(String a, String b, boolean check) {
        if (check) {
            System.out.println(a + " & " + b + " are anagrams.");
        } else {
            System.out.println(a + " & " + b + " are NOT anagrams.");
        }
    }
}
