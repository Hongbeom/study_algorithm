package boj.n1440;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N1440 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ":");
        int[][] time = new int[3][2];
        List<Integer> hour = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            time[i][0] = Integer.parseInt(st.nextToken());
            if (time[i][0] >= 1 && time[i][0] <= 12) {
                hour.add(i);
            }
            if (time[i][0] >= 0 && time[i][0] <= 59) {
                time[i][1] = 1;
            }
        }

        if (hour.isEmpty()) {
            System.out.println(0);
            return;
        }

        int answer = 0;
        origin: for (int h : hour) {
            for (int i = 0; i < 3; i++) {
                if (i == h) {
                    continue;
                }
                if(time[i][1] != 1){
                    continue origin;
                }
            }
            answer += 2;
        }
        System.out.println(answer);

    }
}
