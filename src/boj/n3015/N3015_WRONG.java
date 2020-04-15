package boj.n3015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class N3015_WRONG {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> tallList = new ArrayList<>();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int tall = Integer.parseInt(br.readLine());
            if (i == 0) {
                tallList.add(tall);
                continue;
            }
            if (tall > tallList.get(0)) {
                answer += tallList.size();
                tallList.clear();
            } else {
                int last = tallList.get(tallList.size() - 1);
                if (tall < last) {
                    answer++;
                } else if (tall == last) {
                    int id = bsEqual(tallList, 0, tallList.size() - 1, tall);
                    answer += 1 + tallList.size() - id;
                } else {
                    int id = bs(tallList, 0, tallList.size() - 1, tall);
                    answer += 1 + tallList.size() - id;
                    tallList = tallList.subList(0, id);

                    last = tallList.get(tallList.size() - 1);
                    if(last == tall){
                        id = bsEqual(tallList, 0, tallList.size() - 1, tall);
                        answer += tallList.size() - id;
                    }
                }
            }
            tallList.add(tall);
        }
        System.out.println(answer);
    }

    static int bsEqual(List<Integer> list, int left, int right, int target) {
        int mid = (left + right) / 2;
        int current = list.get(mid);

        if (current <= target) {
            if (mid == 0 || list.get(mid - 1) > target) {
                return mid;
            }
            return bsEqual(list, left, mid, target);
        } else {
            return bsEqual(list, mid + 1, right, target);
        }
    }

    static int bs(List<Integer> list, int left, int right, int target) {
        int mid = (left + right) / 2;
        int current = list.get(mid);

        if (current < target) {
            if (list.get(mid - 1) >= target) {
                return mid;
            }
            return bs(list, left, mid, target);
        } else {
            return bs(list, mid + 1, right, target);
        }
    }
}
