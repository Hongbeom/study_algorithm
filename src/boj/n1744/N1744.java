package boj.n1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class N1744 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine());
            if (v > 0) {
                positive.add(v);
            } else {
                negative.add(v);
            }
        }
        positive.sort(Collections.reverseOrder());
        Collections.sort(negative);
        int answer = 0;
        for (int i = 0; i <= positive.size() / 2; i++) {
            int id = i * 2;
            if (id >= positive.size()) {
                break;
            }
            if (id + 1 >= positive.size()) {
                answer += positive.get(id);
            } else {
                int f = positive.get(id);
                int s = positive.get(id + 1);
                if (s > 1) {
                    answer += f * s;
                } else {
                    answer += f + s;
                }
            }
        }

        for (int i = 0; i <= negative.size() / 2; i++) {
            int id = i * 2;
            if (id >= negative.size()) {
                break;
            }
            if (id + 1 >= negative.size()) {
                answer += negative.get(id);
            } else {
                int f = negative.get(id);
                int s = negative.get(id + 1);
                answer += f * s;
            }
        }
        System.out.println(answer);
    }
}
