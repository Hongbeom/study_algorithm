package boj.n6502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N6502 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int n = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());

            double r = Double.parseDouble(st.nextToken());
            if (r == 0) {
                break;
            }
            double w = Double.parseDouble(st.nextToken());
            double l = Double.parseDouble(st.nextToken());

            if (r < getRadius(w, l)) {
                no(n);
            } else {
                yes(n);
            }
            n++;
        }
    }


    static double getRadius(double w, double l) {
        return Math.sqrt(Math.pow(w / 2, 2) + Math.pow(l / 2, 2));
    }

    static void yes(int n) {
        System.out.println("Pizza " + n + " fits on the table.");
    }

    static void no(int n) {
        System.out.println("Pizza " + n + " does not fit on the table.");
    }
}
