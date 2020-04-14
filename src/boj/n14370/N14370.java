package boj.n14370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class N14370 {

    static List<HashMap<Character, Integer>> NUM = new ArrayList<>();
    static HashMap<Character, Integer> ZERO = new HashMap<>();
    static HashMap<Character, Integer> ONE = new HashMap<>();
    static HashMap<Character, Integer> TWO = new HashMap<>();
    static HashMap<Character, Integer> THREE = new HashMap<>();
    static HashMap<Character, Integer> FOUR = new HashMap<>();
    static HashMap<Character, Integer> FIVE = new HashMap<>();
    static HashMap<Character, Integer> SIX = new HashMap<>();
    static HashMap<Character, Integer> SEVEN = new HashMap<>();
    static HashMap<Character, Integer> EIGHT = new HashMap<>();
    static HashMap<Character, Integer> NINE = new HashMap<>();
    static boolean exit = false;
    static int[] ID;
    static List<Integer> ANS;

    public static void main(String[] args) throws IOException {
        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            int[] counts = new int[26];
            char[] words = br.readLine().toCharArray();
            int total = words.length;
            for (char c : words) {
                counts[c - 'A']++;
            }
            dfs(counts, total, new ArrayList<>());
            Collections.sort(ANS);
            StringBuilder sb = new StringBuilder();
            for (int n : ANS) {
                sb.append(n);
            }
            System.out.println("Case #" + tc + ": " + sb.toString());
            exit = false;
        }
    }

    static void dfs(int[] counts, int total, List<Integer> list) {
        if (exit) {
            return;
        }
        root:
        for (int i = 0; i < NUM.size(); i++) {
            HashMap<Character, Integer> map = NUM.get(i);
            int[] tmp = counts.clone();
            int tc = total;
            List<Integer> tList = new ArrayList<>(list);
            for (char key : map.keySet()) {
                tmp[key - 'A'] -= map.get(key);
                if (tmp[key - 'A'] < 0) {
                    continue root;
                }
                tc -= map.get(key);
            }
            tList.add(ID[i]);
            if (tc == 0) {
                ANS = tList;
                exit = true;
                return;
            }
            dfs(tmp, tc, tList);
            if (exit) {
                return;
            }
        }

    }

    static void init() {
        ZERO.put('Z', 1);
        ZERO.put('E', 1);
        ZERO.put('R', 1);
        ZERO.put('O', 1);

        ONE.put('O', 1);
        ONE.put('N', 1);
        ONE.put('E', 1);

        TWO.put('T', 1);
        TWO.put('W', 1);
        TWO.put('O', 1);

        THREE.put('T', 1);
        THREE.put('H', 1);
        THREE.put('R', 1);
        THREE.put('E', 2);

        FOUR.put('F', 1);
        FOUR.put('O', 1);
        FOUR.put('U', 1);
        FOUR.put('R', 1);

        FIVE.put('F', 1);
        FIVE.put('I', 1);
        FIVE.put('V', 1);
        FIVE.put('E', 1);

        SIX.put('S', 1);
        SIX.put('I', 1);
        SIX.put('X', 1);

        SEVEN.put('S', 1);
        SEVEN.put('E', 2);
        SEVEN.put('V', 1);
        SEVEN.put('N', 1);


        EIGHT.put('E', 1);
        EIGHT.put('I', 1);
        EIGHT.put('G', 1);
        EIGHT.put('H', 1);
        EIGHT.put('T', 1);

        NINE.put('N', 2);
        NINE.put('I', 1);
        NINE.put('E', 1);

        NUM.add(ZERO);
        NUM.add(SIX);
        NUM.add(FOUR);
        NUM.add(FIVE);
        NUM.add(EIGHT);
        NUM.add(THREE);
        NUM.add(TWO);
        NUM.add(NINE);
        NUM.add(SEVEN);
        NUM.add(ONE);
        ID = new int[]{0, 6, 4, 5, 8, 3, 2, 9, 7, 1};
    }
}
