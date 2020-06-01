package boj.n2878;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N2878 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] wants = new int[n];

        long sum = 0;
        for (int i = 0; i < wants.length; i++) {
            wants[i] = Integer.parseInt(br.readLine());
            sum += wants[i];
        }
        sum -= m;
        Arrays.sort(wants);
        long answer = 0;

        for(int i = 0; i < wants.length; i++){
            long w = Math.min(wants[i], sum / (wants.length - i));
            answer += w * w;
            sum -= w;
        }

        System.out.println(answer);
    }
}