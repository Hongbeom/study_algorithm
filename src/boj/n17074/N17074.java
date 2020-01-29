package boj.n17074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class N17074 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] input = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        int[] memo = new int[n];
        ArrayList<Integer> list = new ArrayList<>();

        int before = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                before = input[i];
            } else {
                if (input[i] < before) {
                    list.add(i);
                }
                before = input[i];
            }
        }
        if (list.size() == 0) {
            System.out.println(n);
        } else if (list.size() >= 2) {
            System.out.println(0);
        } else {

            int answer = 0;
            int id = list.get(0);

            // 1. 자기를 빼는것 -- 자기 앞, 뒤 를 비교
            if (id == n - 1 || input[id - 1] <= input[id + 1]) {
                answer++;
            }


            // 2. 자기 앞에를 빼는것 -- 자기 -2 와 자기를 비교
            if (id == 1 || input[id - 2] <= input[id]) {
                answer++;
            }

            System.out.println(answer);
        }


    }
}
