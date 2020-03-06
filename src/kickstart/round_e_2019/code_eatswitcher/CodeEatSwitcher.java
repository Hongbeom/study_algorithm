package kickstart.round_e_2019.code_eatswitcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CodeEatSwitcher {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            int[][] slots = new int[s][4];
            for (int i = 0; i < s; i++) {
                st = new StringTokenizer(br.readLine());
                slots[i][0] = Integer.parseInt(st.nextToken());
                slots[i][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(slots, (o1, o2) -> {
                if (o1[0] / (double) o1[1] < o2[0] / (double) o2[1]) {
                    return -1;
                } else if (o1[0] / (double) o1[1] > o2[0] / (double) o2[1]) {
                    return 1;
                }
                return 0;
            });

            for (int i = 0; i < s; i++) {
                if (i == 0) {
                    slots[i][3] = slots[i][1];
                    slots[s - 1 - i][2] = slots[s - 1 - i][0];
                } else {
                    slots[i][3] = slots[i][1] + slots[i - 1][3];
                    slots[s - 1 - i][2] = slots[s - 1 - i][0] + slots[s - i][2];
                }
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int coding = Integer.parseInt(st.nextToken());
                int eating = Integer.parseInt(st.nextToken());

                if (eating > slots[s - 1][3] || coding > slots[0][2]) {
                    sb.append("N");
                    continue;
                } else if (eating == slots[s - 1][3]) {
                    if (coding == 0) {
                        sb.append("Y");
                    } else {
                        sb.append("N");
                    }
                    continue;
                }

                int id;

                if (eating < slots[0][3]) {
                    id = 0;
                } else if (eating == slots[0][3]) {
                    id = 1;
                    eating = 0;
                } else {
                    id = bs(slots, 0, s - 1, eating);
                    eating -= slots[id - 1][3];
                }

                double f;
                if (eating != 0) {
                    f = 1d - (eating / (double) slots[id][1]);
                } else {
                    f = 1;
                }

                if (id == s - 1) {
                    if (f * slots[id][0] >= coding) {
                        sb.append("Y");
                    } else {
                        sb.append("N");
                    }

                } else {
                    if (f * slots[id][0] + slots[id + 1][2] >= coding) {
                        sb.append("Y");
                    } else {
                        sb.append("N");
                    }
                }
            }
            ans(tc, sb.toString());

        }
    }

    static int bs(int[][] slots, int left, int right, int target) {

        int mid = (left + right) / 2;
        int compare = slots[mid][3];

        if (compare > target) {
            if (mid == 0 || slots[mid - 1][3] <= target) {
                return mid;
            }
            return bs(slots, left, mid, target);
        } else {
            return bs(slots, mid + 1, right, target);
        }
    }

    static void ans(int tc, String answer) {
        System.out.println("Case #" + tc + ": " + answer);
    }

}

