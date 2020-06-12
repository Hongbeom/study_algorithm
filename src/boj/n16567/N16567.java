package boj.n16567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N16567 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int flip = 0;
        boolean continual = false;
        boolean[] way = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            if (Integer.parseInt(st.nextToken()) == 1) {
                way[i] = true;
                if (!continual) {
                    flip++;
                    continual = true;
                }
            } else {
                if (continual) {
                    continual = false;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            if (q == 0) {
                sb.append(flip);
                sb.append('\n');
            } else {
                int id = Integer.parseInt(st.nextToken()) - 1;
                if (!way[id]) {
                    way[id] = true;
                    if (id == 0) {
                        if (!way[id + 1]) {
                            flip++;
                        }
                    } else if (id == way.length - 1) {
                        if (!way[id - 1]) {
                            flip++;
                        }
                    } else {
                        if (!way[id - 1] && !way[id + 1]) {
                            flip++;
                        } else if (way[id - 1] && way[id + 1]) {
                            flip--;
                        }
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }

}
