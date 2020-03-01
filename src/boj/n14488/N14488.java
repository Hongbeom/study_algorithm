package boj.n14488;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14488 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        double t = Double.parseDouble(st.nextToken());

        double left = Double.MIN_VALUE;
        double right = Double.MAX_VALUE;

        double[] loc = new double[n];
        double[] vec = new double[n];

        st = new StringTokenizer(br.readLine());
        StringTokenizer vst = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            loc[i] = Double.parseDouble(st.nextToken());
            vec[i] = Double.parseDouble(vst.nextToken());

            left = Math.max(left, Math.round((loc[i] - t * vec[i]) * 10000) / 10000.0);
            right = Math.min(right, loc[i] + t * vec[i]);

        }

        if (left > right) {
            System.out.println(0);
            return;
        }

        boolean answer = true;

        for (int i = 0; i < n; i++) {
            if (loc[i] > right) {
                if (Math.round((loc[i] - t * vec[i]) * 10000) / 10000.0 > right) {
                    answer = false;
                    break;
                }
            }
            if (loc[i] < left) {
                if (loc[i] + t * vec[i] < left) {
                    answer = false;
                    break;
                }
            }
        }

        if (answer) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }


    }
}
