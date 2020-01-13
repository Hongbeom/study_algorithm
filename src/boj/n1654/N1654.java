package boj.n1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1654 {

    static long[] LAN;
    static long TARGET;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        TARGET = Long.parseLong(st.nextToken());

        LAN = new long[k];

        long limit = 0;
        for (int i = 0; i < k; i++) {
            LAN[i] = Integer.parseInt(br.readLine());
            limit = Math.max(limit, LAN[i]);
        }

        System.out.println(bs(1, limit));


    }

    static long bs(long start, long end) {

        long middle = (start + end) / 2;

        long current = getValue(middle, LAN);

        if (current < TARGET) {
            // 왼쪽으로 이동
            return bs(start, middle);
        } else {
            if (getValue(middle + 1, LAN) < TARGET) {
                return middle;
            }

            return bs(middle + 1, end);

            // 오른쪽으로 이동 --> 여기서 판별이 이루어 져야 한다.
        }

    }

    static long getValue(long line, long[] lan) {
        long number = 0;

        for (long l : lan) {
            number += l / line;
        }

        return number;
    }
}
