package boj.n2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2467 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] solution = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int mid = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
            int tmp = Math.abs(solution[i]);
            if (tmp < min) {
                min = tmp;
                mid = i;
            }
        }
        if (solution[0] >= 0) {
            System.out.println(solution[0] + " " + solution[1]);
            return;
        }

        if (solution[n - 1] <= 0) {
            System.out.println(solution[n - 2] + " " + solution[n - 1]);
            return;
        }

        int l;
        int r;
        if (solution[mid] == 0) {
            if (Math.abs(solution[mid] - solution[mid - 1]) > Math.abs(solution[mid] - solution[mid + 1])) {
                l = mid - 1;
                r = mid;
            } else {
                l = mid;
                r = mid + 1;
            }
        } else if (solution[mid] > 0) {
            l = mid - 1;
            r = mid;
        } else {
            l = mid;
            r = mid + 1;
        }
        int compare = Math.abs(solution[l] + solution[r]);
        int p1 = solution[l];
        int p2 = solution[r];
        while (l > 0 && r < n - 1) {
            int left = Math.abs(solution[l - 1] + solution[r]);
            int right = Math.abs(solution[l] + solution[r + 1]);
            int tmp;
            if (left < right) {
                l--;
                tmp = left;
            } else {
                r++;
                tmp = right;
            }

            if (tmp < compare) {
                compare = tmp;
                p1 = solution[l];
                p2 = solution[r];
            }
        }

        while (r < n - 1) {
            r++;
            int tmp = Math.abs(solution[l] + solution[r]);
            if (tmp < compare) {
                compare = tmp;
                p1 = solution[l];
                p2 = solution[r];
            }
        }

        while (l > 0) {
            l--;
            int tmp = Math.abs(solution[l] + solution[r]);
            if (tmp < compare) {
                compare = tmp;
                p1 = solution[l];
                p2 = solution[r];
            }
        }
        System.out.println(p1 + " " + p2);
    }
}
