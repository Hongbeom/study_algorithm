package boj.n1821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N1821 {


    static int[][] COEF = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 2, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 3, 3, 1, 0, 0, 0, 0, 0, 0},
            {1, 4, 6, 4, 1, 0, 0, 0, 0, 0},
            {1, 5, 10, 10, 5, 1, 0, 0, 0, 0},
            {1, 6, 15, 20, 15, 6, 1, 0, 0, 0},
            {1, 7, 21, 35, 35, 21, 7, 1, 0, 0},
            {1, 8, 28, 56, 70, 56, 28, 8, 1, 0},
            {1, 9, 36, 84, 126, 126, 84, 36, 9, 1},
    };

    static int N;
    static int F;
    static List<Integer> ANS = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        dfs(0, 0, new boolean[N]);

        for (int i = ANS.size() - 1; i >= 0; i--) {
            if(i == 0){
                System.out.print(ANS.get(i));
            }else{
                System.out.print(ANS.get(i) + " ");
            }
        }

    }

    static int dfs(int current, int step, boolean[] check) {
        if (step == check.length) {
            if (current == F) {
                return 1;
            } else {
                return -1;
            }
        }

        for (int i = 0; i < check.length; i++) {
            if (!check[i]) {
                check[i] = true;
                int v = dfs(current + ((i + 1) * COEF[N][step]), step + 1, check.clone());
                if (v == 1) {
                    ANS.add(i + 1);
                    return 1;
                }
                check[i] = false;
            }
        }
        return 0;
    }
}
