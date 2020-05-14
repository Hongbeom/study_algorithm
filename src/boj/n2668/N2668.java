package boj.n2668;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class N2668 {

    static boolean[] CHECK;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];
        CHECK = new boolean[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine()) - 1;
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (CHECK[i]) {
                continue;
            }

            List<Integer> list = check(numbers, i);
            if (list != null) {
                for (int id : list) {
                    CHECK[id] = true;
                    cnt++;
                }
            }
        }


        System.out.println(cnt);
        for (int i = 0; i < CHECK.length; i++) {
            if (CHECK[i]) {
                System.out.println(i + 1);
            }

        }


    }


    static List<Integer> check(int[] numbers, int f) {
        boolean[] up = new boolean[numbers.length];
        boolean[] down = new boolean[numbers.length];
        List<Integer> list = new ArrayList<>();
        int u = f;

        while (true) {
            if (up[u]) {
                return null;
            }
            up[u] = true;
            list.add(u);

            if (down[numbers[u]]) {
                return null;
            }

            down[numbers[u]] = true;
            if (numbers[u] == f) {
                break;
            }
            u = numbers[u];
        }

        return list;
    }

}
