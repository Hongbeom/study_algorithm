package boj.n13904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N13904 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] > o2[1]) {
                return -1;
            } else if (o1[1] < o2[1]) {
                return 1;
            }
            return 0;
        });

        List<int[]> assignments = new ArrayList<>();
        StringTokenizer st;
        int maxDay = 0;
        int minDay = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            maxDay = Math.max(maxDay, d);
            minDay = Math.min(minDay, d);
            assignments.add(new int[]{d, s});
        }

        assignments.sort((o1, o2) -> {
            if (o1[0] > o2[0]) {
                return -1;
            } else if (o1[0] < o2[0]) {
                return 1;
            }
            return 0;
        });

        int bid = 0;
        int answer = 0;
        int beforeDay = maxDay;

        while (beforeDay > 0) {

            if (bid < assignments.size() && beforeDay == assignments.get(bid)[0]) {
                for (int i = bid; i < assignments.size(); i++) {
                    if (assignments.get(i)[0] != beforeDay) {
                        bid = i;
                        break;
                    }
                    if (i == assignments.size() - 1) {
                        bid = assignments.size();
                    }
                    pq.add(assignments.get(i));
                }
            }

            if (!pq.isEmpty()) {
                answer += pq.poll()[1];
            }

            beforeDay--;
        }

        System.out.println(answer);


    }
}
