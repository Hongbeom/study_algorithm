package boj.n7507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N7507 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= TC; tc++) {
            int m = Integer.parseInt(br.readLine());

            List<int[]> matches = new ArrayList<>();
            int count = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int d = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                matches.add(new int[]{d, s, e});
            }

            matches.sort((o1, o2) -> {
                if (o1[0] > o2[0]) {
                    return 1;
                } else if (o1[0] < o2[0]) {
                    return -1;
                } else {
                    if (o1[1] > o2[1]) {
                        return 1;
                    } else if (o1[1] < o2[1]) {
                        return -1;
                    }
                }
                return 0;
            });


            int d = matches.get(0)[0] - 1;
            int beforeStart = 0;
            int beforeEnd = 0;

            for (int[] match : matches) {

                if (d != match[0]) {
                    d = match[0];
                    beforeEnd = 0;
                    beforeStart = 0;
                }

                if (beforeStart == 0 && beforeEnd == 0) {
                    count++;
                    beforeStart = match[1];
                    beforeEnd = match[2];
                } else {
                    if (beforeEnd <= match[1]) {
                        count++;
                        beforeStart = match[1];
                        beforeEnd = match[2];
                    } else if (match[2] < beforeEnd) {
                        beforeStart = match[1];
                        beforeEnd = match[2];
                    }
                }
            }
            if(tc != TC){
                System.out.println("Scenario #" + tc + ":" + "\n" + count + "\n");
            }else{
                System.out.println("Scenario #" + tc + ":" + "\n" + count);
            }

        }
    }

}