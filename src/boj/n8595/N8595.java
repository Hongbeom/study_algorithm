package boj.n8595;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class N8595 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();
        List<Character> list = new ArrayList<>();
        long answer = 0;
        for (char c : input) {
            int id = (int) c;
            if (48 <= id && id <= 57) {
                list.add(c);
            } else {
                if (list.size() != 0) {
                    if (list.size() > 6) {
                        list.clear();
                    } else {
                        answer += getNum(list);
                        list.clear();
                    }
                }
            }
        }

        if (list.size() != 0) {
            if (list.size() > 6) {
                list.clear();
            } else {
                answer += getNum(list);
                list.clear();
            }
        }

        System.out.println(answer);

    }


    static int getNum(List<Character> list) {
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            num += (int) ((list.get(i) - '0') * Math.pow(10, list.size() - i - 1));
        }
        return num;
    }
}
