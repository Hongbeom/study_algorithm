package boj.n12867;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class N12867 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<HashMap<Integer, Integer>> check = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] spots = new int[m];
        for (int i = 0; i < m; i++) {
            spots[i] = Integer.parseInt(st.nextToken());
        }

        char[] move = br.readLine().toCharArray();
        HashMap<Integer, Integer> current = new HashMap<>();
        if (move[0] == '+') {
            current.put(spots[0], 1);
        } else {
            current.put(spots[0], -1);
        }
        check.add(current);
        for (int i = 1; i < move.length; i++) {
            HashMap<Integer, Integer> next = new HashMap<>(check.get(check.size() - 1));
            int key = spots[i];
            if (next.containsKey(key)) {
                int value = next.get(key);
                if (move[i] == '+') {
                    value++;
                } else {
                    value--;
                }
                if (value == 0) {
                    next.remove(key);
                } else {
                    next.put(key, value);
                }
            } else {
                if (move[i] == '+') {
                    next.put(key, 1);
                } else {
                    next.put(key, -1);
                }
            }
            if (next.isEmpty() || checkDuplicate(check, next)) {
                System.out.println(0);
                return;
            }
            check.add(next);
        }

        System.out.println(1);
    }


    static boolean checkDuplicate(List<HashMap<Integer, Integer>> check, HashMap<Integer, Integer> next) {
        for (HashMap<Integer, Integer> map1 : check) {
            if (spotEquals(map1, next)) {
                return true;
            }
        }
        return false;
    }

    static boolean spotEquals(HashMap<Integer, Integer> map1, HashMap<Integer, Integer> map2) {
        if (map1.size() != map2.size()) {
            return false;
        }
        for (int key1 : map1.keySet()) {
            if (!map2.containsKey(key1)) {
                return false;
            }
            if (map1.get(key1).intValue() != map2.get(key1).intValue()) {
                return false;
            }
        }
        return true;
    }
}
