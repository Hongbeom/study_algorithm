package boj.n16237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N16237 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int answer = 0;

        answer += e;

        if (a >= 0 && d >= 0) {
            if (a > d) {
                answer += d;
                a -= d;
                d = 0;
            } else if (a < d) {
                answer += a;
                d -= a;
                a = 0;
            } else {
                answer += a;
                a = 0;
                d = 0;
            }
        }

        if (b >= 0 && c >= 0) {
            if (b > c) {
                answer += c;
                b -= c;
                c = 0;
            } else if (b < c) {
                answer += b;
                c -= b;
                b = 0;
            } else {
                answer += b;
                b = 0;
                c = 0;
            }
        }

        if (a == 0 && b == 0 && c == 0 && d == 0) {
            System.out.println(answer);
            return;
        }

        if (a > 0) {
            if (c > 0) {
                // 1을 2로 변환해서 생각.
                int two = a / 2;
                a = a % 2;

                if (two >= c) {
                    answer += c;
                    two -= c;
                    a += two * 2;
                    answer += a / 5;
                    if (a % 5 > 0) {
                        answer++;
                    }
                } else {
                    answer += two;
                    answer += c - two;
                }
            } else {
                int four = b / 2;
                b = b % 2;

                if (four >= a) {
                    // a를 여기서 다썻다.
                    answer += a;
                    four -= a;
                    b += four * 2;
                    answer += b / 2;
                    if (b % 2 > 0) {
                        answer++;
                    }
                } else {
                    answer += four;
                    a -= four;
                    // b가 0개 또는 1개 있다. a 는 몇개있는지 모른다.
                    if (b > 0) {
                        answer++;
                        a -= 3;
                        if (a > 0) {
                            answer += a / 5;
                            if (a % 5 > 0) {
                                answer++;
                            }
                        }
                    } else {
                        answer += a / 5;
                        if (a % 5 > 0) {
                            answer++;
                        }
                    }
                }

            }

        } else {
            answer += d;
            if (c > 0) {
                answer += c;
            } else {
                answer += b / 2;
                if (b % 2 > 0) {
                    answer++;
                }
            }
        }


        System.out.println(answer);
    }
}
