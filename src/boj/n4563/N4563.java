package boj.n4563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class N4563 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            long a = Long.parseLong(br.readLine());
            if (a == 0) {
                return;
            }

            System.out.println(check(a));


        }


    }


    static int check(long a) {
        int answer = 0;
        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();
        long target = (long) Math.pow(a, 2);
        for (int i = 1; i < a; i++) {
            if (target % i == 0) {
                left.add((long) i);
                right.add(target / i);
            }
        }

        for (int i = 0; i < left.size(); i++) {
            long l = left.get(i);
            long r = right.get(i);

            long plus = l + r;
            long minus = r - l;

            if (plus % 2 == 0 && minus % 2 == 0) {
                long b = minus / 2;

                if(b > a){
                    answer++;
                }
            }
        }

        return answer;
    }


}
