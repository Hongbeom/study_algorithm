package boj.n2212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N2212 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] sensors = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (k >= n) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensors);
        int[] dist = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            dist[i] = sensors[i + 1] - sensors[i];
        }
        Arrays.sort(dist);
        for (int i = 0; i < k - 1; i++) {
            dist[dist.length - 1 - i] = 0;
        }
        int answer = 0;
        for (int i = 0; i < n - 1; i++) {
            answer += dist[i];
        }
        System.out.println(answer);
    }
}
