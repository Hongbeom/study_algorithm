package boj.n2594;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N2594 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<int[]> time = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            time.add(new int[]{subTime(startTime, 10), addTime(endTime, 10)});
        }

        time.sort((o1, o2) -> {
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
                return 0;
            }
        });

        int rest = 0;
        int beforeEnd = 1000;

        for (int[] t : time) {
            if (t[0] > beforeEnd) {
                rest = Math.max(rest, getRest(beforeEnd, t[0]));
            }
            beforeEnd = Math.max(beforeEnd, t[1]);
        }

        rest = Math.max(rest, getRest(beforeEnd, 2200));

        System.out.println(rest);

    }

    static int addTime(int time, int add) {

        int hour = time / 100;
        int minute = (time - hour * 100) + add;

        if (minute >= 60) {
            hour += minute / 60;
            minute %= 60;
        }

        return hour * 100 + minute;
    }

    static int subTime(int time, int sub) {

        int hour = time / 100;
        int minute = time - (hour * 100);
        if (sub >= 60) {
            hour -= sub / 60;
            sub %= 60;
        }

        minute -= sub;

        if (minute < 0) {
            hour--;
            minute += 60;
        }

        return hour * 100 + minute;
    }

    static int getRest(int t1, int t2) {
        if (t1 > t2) {
            return 0;
        }

        int h1 = t1 / 100;
        int h2 = t2 / 100;

        int m1 = t1 - h1 * 100;
        int m2 = t2 - h2 * 100;

        int h = h2 - h1;
        int m = m2 - m1;

        if (m < 0) {
            h--;
            m += 60;
        }

        return m + h * 60;
    }
}