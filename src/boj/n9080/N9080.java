package boj.n9080;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N9080 {

    static final int HOUR_MINUTE = 60;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            String[] startTime = st.nextToken().split(":");
            int currentHour = Integer.parseInt(startTime[0]);
            int currentMinute = Integer.parseInt(startTime[1]);

            int useTime = Integer.parseInt(st.nextToken());
            List<int[]> timeList = new ArrayList<>();

            while (useTime > 0) {
                int minute = 0;

                if (8 <= currentHour && currentHour < 22) {

                    if (currentMinute == 0) {
                        minute += (22 - currentHour) * HOUR_MINUTE;
                    } else {
                        minute += (21 - currentHour) * HOUR_MINUTE + HOUR_MINUTE - currentMinute;
                    }
                    if (minute >= useTime) {
                        timeList.add(new int[]{0, useTime});
                    } else {
                        timeList.add(new int[]{0, minute});
                    }

                    currentHour = 22;

                } else {

                    if (currentHour >= 22) {
                        if (currentMinute == 0) {
                            minute += (24 - currentHour) * HOUR_MINUTE;
                        } else {
                            minute += (23 - currentHour) * HOUR_MINUTE + HOUR_MINUTE - currentMinute;
                        }
                        currentHour = 0;
                        currentMinute = 0;
                    }

                    if (currentMinute == 0) {
                        minute += (8 - currentHour) * HOUR_MINUTE;
                    } else {
                        minute += (7 - currentHour) * HOUR_MINUTE + HOUR_MINUTE - currentMinute;
                    }

                    if (minute >= useTime) {
                        timeList.add(new int[]{1, useTime});
                    } else {
                        timeList.add(new int[]{1, minute});
                    }

                    currentHour = 8;
                }
                currentMinute = 0;

                useTime -= minute;
            }


            System.out.println(dfs(timeList, 0, 0, 0));
        }
    }

    static int dfs(List<int[]> timeList, int aheadMinute, int price, int id) {
        if (id >= timeList.size()) {


            if (aheadMinute % HOUR_MINUTE == 0) {
                price += (aheadMinute / HOUR_MINUTE) * 1000;
            } else {
                price += (aheadMinute / HOUR_MINUTE + 1) * 1000;
            }

            return price;
        }
        int min = Integer.MAX_VALUE;

        int[] time = timeList.get(id);
        min = Math.min(min, dfs(timeList, aheadMinute + time[1], price, id + 1));

        if (time[0] == 1) {
            int nextPrice = 5000;
            if (aheadMinute % HOUR_MINUTE == 0) {
                nextPrice += price + (aheadMinute / HOUR_MINUTE) * 1000;
            } else {
                nextPrice += price + (aheadMinute / HOUR_MINUTE + 1) * 1000;
            }
            min = Math.min(min, dfs(timeList, 0, nextPrice, id + 1));
        }
        return min;
    }
}

