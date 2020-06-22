package boj.n14908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N14908 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] taskInfos = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            taskInfos[i][0] = Integer.parseInt(st.nextToken());
            taskInfos[i][1] = Integer.parseInt(st.nextToken());
            taskInfos[i][2] = i + 1;
        }
        Arrays.sort(taskInfos, (o1, o2) -> {
            if (o1[0] * o2[1] < o2[0] * o1[1]) {
                return -1;
            } else if (o1[0] * o2[1] > o2[0] * o1[1]) {
                return 1;
            } else {
                if (o1[2] < o2[2]) {
                    return -1;
                } else if (o1[2] > o2[2]) {
                    return 1;
                }
            }
            return 0;
        });

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < taskInfos.length; i++) {
            ans.append(taskInfos[i][2]);
            if (i != taskInfos.length - 1) {
                ans.append(' ');
            }
        }
        System.out.println(ans.toString());
    }
}
