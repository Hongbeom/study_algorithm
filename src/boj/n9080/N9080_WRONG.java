package boj.n9080;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N9080_WRONG {

    static final int HOUR_MINUTE = 60;
    static final int HOUR_10_MINUTE = HOUR_MINUTE * 10;
    static final int DAY_MINUTE = HOUR_MINUTE * 24;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            String[] startTime = st.nextToken().split(":");
            int startHour = Integer.parseInt(startTime[0]);
            int startMinute = Integer.parseInt(startTime[1]);

            int useTime = Integer.parseInt(st.nextToken());
            int useHour = useTime / HOUR_MINUTE;
            int useMinute = useTime % HOUR_MINUTE;

            int minPrice = 1000 * useHour;
            if (useMinute != 0) {
                minPrice += 1000;
            }

            // 22시를 시작 기준으로 잡는다.
            // 이 전의 사용은 모두 시간당 1000원으로 잡는다.
            int tmp = 0;
//            int beforeRestMinute = 0;
            if (startHour >= 8 && startHour < 22) {
                // 22시 전에 게임을 시작하는 경우.
                if (startMinute == 0) {
                    useTime -= (22 - startHour) * 60;
                } else {
                    useTime -= (60 - startMinute) + (22 - startHour - 1) * 60;
                }
                tmp += (22 - startHour) * 1000;
            } else {

                int dcTime = 0;

                if (startHour >= 22) {
                    if (startMinute == 0) {
                        dcTime = (24 - startHour + 8) * HOUR_MINUTE;
                    } else {
                        dcTime = (23 - startHour + 8) * HOUR_MINUTE + 60 - startMinute;
                    }
                } else {
                    if (startMinute == 0) {
                        dcTime = (8 - startHour) * HOUR_MINUTE;
                    } else {
                        dcTime = (7 - startHour) * HOUR_MINUTE + 60 - startMinute;
                    }
                }

                // dcTime과 5000 원 누가 더 적은지 선택.
                if (dcTime % HOUR_MINUTE == 0) {
                    tmp += Math.min(5000, 60 * dcTime / HOUR_MINUTE * 1000);
                } else {
                    tmp += Math.min(5000, (60 * dcTime / HOUR_MINUTE + 1) * 1000);
                }

                if (useTime < dcTime) {
                    // 하루의 야간 시간보다 사용시간이 적은 경우.
                    useTime = 0;
                } else if (useTime - dcTime >= HOUR_MINUTE * 14) {
                    // 모든 dcTime을 소비해도 하루가 넘어가는 경우.
                    tmp += 14000;
                    useTime -= dcTime;
                } else {
                    // dcTime은 넘는데 다음날 까지의 야간까지 안가는 경우.
                    int rest = useTime - dcTime;
                    useTime = 0;

                    // dcTime과 5000 원 누가 더 적은지 선택.
                    if (rest % HOUR_MINUTE == 0) {
                        tmp += rest / HOUR_MINUTE * 1000;
                    } else {
                        tmp += (rest / HOUR_MINUTE + 1) * 1000;
                    }

                }
            }


            while (useTime > 0) {

                if (useTime >= DAY_MINUTE) {
                    tmp += 19000;
                    useTime -= DAY_MINUTE;
                } else if (useTime >= HOUR_10_MINUTE) {
                    tmp += 5000;
                    useTime -= HOUR_10_MINUTE;
                    if (useTime % HOUR_MINUTE == 0) {
                        tmp += useTime / HOUR_MINUTE * 1000;
                    } else {
                        tmp += (useTime / HOUR_MINUTE + 1) * 1000;
                    }
                    break;
                } else {
                    if (useTime % HOUR_MINUTE == 0) {
                        tmp += Math.min(5000, useTime / HOUR_MINUTE * 1000);
                    } else {
                        tmp += Math.min(5000, (useTime / HOUR_MINUTE + 1) * 1000);
                    }
                    break;
                }

            }


            System.out.println(Math.min(minPrice, tmp));
        }
    }
}
