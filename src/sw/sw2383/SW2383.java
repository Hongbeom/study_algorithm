package sw.sw2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW2383 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= TC; tc++) {
            int n = Integer.parseInt(br.readLine());

            int[] stairA = null;
            int[] stairB = null;

            List<int[]> people = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int m = Integer.parseInt(st.nextToken());
                    if (m > 1) {
                        if (stairA == null) {
                            stairA = new int[3];
                            stairA[0] = i;
                            stairA[1] = j;
                            stairA[2] = m;
                        } else {
                            stairB = new int[3];
                            stairB[0] = i;
                            stairB[1] = j;
                            stairB[2] = m;
                        }
                    } else if (m == 1) {
                        people.add(new int[]{i, j});
                    }
                }
            }

            List<List<Integer>> candidates = new ArrayList<>();
            for (int i = 0; i <= people.size(); i++) {
                createCase(candidates, new ArrayList<>(), 0, i, people.size());
            }


            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < candidates.size() / 2; i++) {
                int aId = i;
                int bId = candidates.size() - 1 - i;


                Queue<Integer> aQueue = new LinkedList<>();
                Queue<Integer> bQueue = new LinkedList<>();

                Queue<Integer> aQueue1 = new LinkedList<>();
                Queue<Integer> bQueue1 = new LinkedList<>();


                for (int id : candidates.get(aId)) {
                    int[] p = people.get(id);
                    aQueue.add(Math.abs(stairA[0] - p[0]) + Math.abs(stairA[1] - p[1]));
                    bQueue1.add(Math.abs(stairB[0] - p[0]) + Math.abs(stairB[1] - p[1]));
                }

                for (int id : candidates.get(bId)) {
                    int[] p = people.get(id);
                    bQueue.add(Math.abs(stairB[0] - p[0]) + Math.abs(stairB[1] - p[1]));
                    aQueue1.add(Math.abs(stairA[0] - p[0]) + Math.abs(stairA[1] - p[1]));
                }


                answer = Math.min(answer, Math.max(getTime(aQueue, stairA[2]), getTime(bQueue, stairB[2])));
                answer = Math.min(answer, Math.max(getTime(aQueue1, stairA[2]), getTime(bQueue1, stairB[2])));


            }

            System.out.println("#" + tc + " " + answer);


        }
    }

    static void createCase(List<List<Integer>> candidates, List<Integer> tm, int step, int target, int n) {

        if (tm.size() == target) {
            candidates.add(tm);
            return;
        }

        if (step == n) {
            return;
        }

        createCase(candidates, new ArrayList<>(tm), step + 1, target, n);
        tm.add(step);
        createCase(candidates, tm, step + 1, target, n);
    }

    static int getTime(Queue<Integer> moving, int k) {
        int time = 0;

        Queue<Integer> decending = new LinkedList<>();
        Queue<Integer> waiting = new LinkedList<>();


        while (true) {

            int dl = decending.size();

            for (int i = 0; i < dl; i++) {
                int left = decending.poll();
                left--;
                if (left > 0) {
                    decending.add(left);
                } else {
                    if (!waiting.isEmpty()) {
                        decending.add(waiting.poll());
                    }
                }
            }

            int ml = moving.size();

            for (int i = 0; i < ml; i++) {
                int left = moving.poll();
                left--;
                if (left > 0) {
                    moving.add(left);
                } else {
                    if (decending.size() < 3) {
                        decending.add(k + 1);
                    } else {
                        waiting.add(k);
                    }
                }
            }

            time++;
            if (decending.isEmpty() && moving.isEmpty() && waiting.isEmpty()) {
                break;
            }

        }

        return time;
    }


}
