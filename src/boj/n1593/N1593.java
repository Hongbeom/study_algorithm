package boj.n1593;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class N1593 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int g = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<>();
        int[] base = new int[60];

        for (char c : br.readLine().toCharArray()) {
            base[c - 'A']++;
            set.add(c - 'A');
        }

        char[] words = br.readLine().toCharArray();
        int answer = 0;

        int[] compare = new int[60];

        for (int i = 0; i < g; i++) {
            compare[words[i] - 'A']++;
        }

        if (compareWords(base, compare, set)) {
            answer++;
        }

        for (int i = g; i < s; i++) {

            compare[words[i - g] - 'A']--;
            compare[words[i] - 'A']++;
            if (compareWords(base, compare, set)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static boolean compareWords(int[] base, int[] compare, Set<Integer> set) {

        for (int id : set) {
            if (base[id] != compare[id]) {
                return false;
            }
        }
        return true;
    }
}
