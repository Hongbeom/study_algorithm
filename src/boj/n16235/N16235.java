package boj.n16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class N16235 {

    static final int[] H = new int[]{-1, 1, 0, 0, 1, 1, -1, -1};
    static final int[] W = new int[]{0, 0, -1, 1, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] eat = new int[n][n];
        int[][] plus = new int[n][n];

        for (int i = 0; i < plus[0].length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < plus[0].length; j++) {
                plus[i][j] = Integer.parseInt(st.nextToken());
                eat[i][j] = 5;
            }
        }

        List<Tree> treeList = new ArrayList<>();
        List<Tree> tmpList = new ArrayList<>();
        List<Tree> deadList = new ArrayList<>();
        List<Tree> breedList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            treeList.add(new Tree(x, y, z));
        }

        for (int i = 0; i < k; i++) {
            // spring
            Collections.sort(treeList);

            for (Tree tree : treeList) {
                if (eat[tree.h][tree.w] - tree.age >= 0) {
                    eat[tree.h][tree.w] -= tree.age;
                    tree.age += 1;
                    if (tree.age % 5 == 0) {
                        breedList.add(tree);
                    } else {
                        tmpList.add(tree);
                    }
                } else {
                    deadList.add(tree);
                }
            }
            treeList.clear();
            treeList.addAll(tmpList);
            tmpList.clear();

            // summer
            for (Tree tree : deadList) {
                eat[tree.h][tree.w] += tree.age / 2;
            }
            deadList.clear();

            // fall;
            treeList.addAll(breedList);
            for (Tree tree : breedList) {
                for (int d = 0; d < H.length; d++) {
                    int na = tree.h + H[d];
                    int nb = tree.w + W[d];
                    if (na < 0 || nb < 0 || na >= eat.length || nb >= eat[0].length) {
                        continue;
                    }
                    treeList.add(new Tree(na, nb, 1));
                }
            }
            breedList.clear();

            // winter;
            refresh(eat, plus);
        }
        System.out.println(treeList.size());
    }

    static void refresh(int[][] eat, int[][] plus) {
        for (int i = 0; i < eat.length; i++) {
            for (int j = 0; j < eat[0].length; j++) {
                eat[i][j] += plus[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree> {

        int h;
        int w;
        int age;

        Tree(int h, int w, int age) {
            this.h = h;
            this.w = w;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {

            if (this.age > o.age) {
                return 1;
            } else if (this.age < o.age) {
                return -1;
            }

            return 0;
        }


    }
}
