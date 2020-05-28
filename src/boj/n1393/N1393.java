package boj.n1393;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1393 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int targetX = Integer.parseInt(st.nextToken());
        int targetY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int vx = Integer.parseInt(st.nextToken());
        int vy = Integer.parseInt(st.nextToken());

        if (vx == 0 && vy == 0) {
            System.out.println(x + " " + y);
            return;
        }

        int gcd = gcd(Math.min(Math.abs(vx), Math.abs(vy)), Math.max(Math.abs(vx), Math.abs(vy)));
        vx /= gcd;
        vy /= gcd;

        int beforeDist = getDistance(targetX, targetY, x, y);
        while (true) {
            x += vx;
            y += vy;

            int currentDist = getDistance(targetX, targetY, x, y);

            if (beforeDist < currentDist) {
                x -= vx;
                y -= vy;
                break;
            } else {
                beforeDist = currentDist;
            }
        }

        System.out.println(x + " " + y);


    }

    static int getDistance(int targetX, int targetY, int x, int y) {
        return (int) (Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
    }

    static int gcd(int x, int y) {
        if (x == 0) {
            return y;
        }

        int gcd = 1;

        for (int i = 1; i <= x; i++) {
            if (x % i == 0 && y % i == 0) {
                gcd = Math.max(gcd, i);
            }
        }
        return gcd;
    }
}

