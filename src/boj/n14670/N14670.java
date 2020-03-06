package boj.n14670;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N14670 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] drug = new int[101];
        Arrays.fill(drug, -1);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int effect = Integer.parseInt(st.nextToken());
            int name = Integer.parseInt(st.nextToken());
            drug[effect] = name;
        }

        int r = Integer.parseInt(br.readLine());
        effects:
        for (int i = 0; i < r; i++) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            for (int j = 0; j < l; j++) {
                int effect = Integer.parseInt(st.nextToken());
                if (drug[effect] == -1) {
                    System.out.println("YOU DIED");
                    continue effects;
                } else {
                    sb.append(drug[effect]);
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());
        }
    }
}
