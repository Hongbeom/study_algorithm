package boj.n1342;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class N1342 {
    static int ANS = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> wordMap = new HashMap<>();
        char[] words = br.readLine().toCharArray();
        int size = words.length;
        for (char w : words) {
            int c = wordMap.getOrDefault(w, 0);
            wordMap.put(w, c + 1);
        }
        dfs(wordMap, size, '\u0000');
        System.out.println(ANS);
    }

    static void dfs(HashMap<Character, Integer> wordMap, int size, char before) {
        if (size == 0) {
            ANS++;
            return;
        }
        for (char key : wordMap.keySet()) {
            if (before == key) {
                continue;
            }
            HashMap<Character, Integer> tmp = new HashMap<>(wordMap);
            int a = tmp.get(key);

            if (a - 1 == 0) {
                tmp.remove(key);
            } else {
                tmp.put(key, a - 1);
            }
            dfs(tmp, size - 1, key);
        }
    }
}
