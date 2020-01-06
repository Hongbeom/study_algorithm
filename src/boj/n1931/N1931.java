package boj.n1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N1931 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] meetings = new int[n][2];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meetings, (o1, o2) -> {
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] < o2[0]) {
                return -1;
            }
            return 0;
        });

        int answer = 0;
        int compare = 0;
        for (int[] meeting : meetings) {
            if (compare == 0) {
                compare = meeting[1];
                answer++;
            } else {
                if (compare > meeting[0]) {
                    if(compare > meeting[1]){
                        compare = meeting[1];
                    }
                } else {
                    compare = meeting[1];
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
