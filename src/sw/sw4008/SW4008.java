package sw.sw4008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW4008 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] numbers = new int[n];
            int[] operands = new int[4];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                operands[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            List<int[]> candidates = new ArrayList<>();
            createCase(candidates, new int[n - 1], operands, 0);

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for (int[] candidate : candidates) {
                int value = numbers[0];

                for (int i = 0; i < candidate.length; i++) {
                    if (candidate[i] == 0) {
                        value += numbers[i + 1];
                    } else if (candidate[i] == 1) {
                        value -= numbers[i + 1];
                    } else if (candidate[i] == 2) {
                        value *= numbers[i + 1];
                    } else {
                        value /= numbers[i + 1];
                    }
                }

                max = Math.max(value, max);
                min = Math.min(value, min);
            }

            System.out.println("#" + tc + " " + (max - min));
        }
    }


    static void createCase(List<int[]> candidates, int[] tm, int[] operands, int step) {

        if (step == tm.length) {
            candidates.add(tm);
            return;
        }

        for (int i = 0; i < operands.length; i++) {
            if (operands[i] > 0) {
                tm[step] = i;
                operands[i]--;
                createCase(candidates, tm.clone(), operands.clone(), step + 1);
                operands[i]++;
            }
        }
    }
}

