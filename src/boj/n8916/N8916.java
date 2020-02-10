package boj.n8916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N8916 {


    static long ANS = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            Tree tree = null;

            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    tree = new Tree(true, Integer.parseInt(st.nextToken()));
                } else {
                    tree.add(Integer.parseInt(st.nextToken()));
                }
            }
            getCandidates(tree);
            System.out.println(ANS);
            ANS = 1;

        }

    }


    static long comb(int n, int r) {

        if (n - r < r) {
            r = n - r;
        }

        long up = 1;
        long down = 1;
        for (int i = n; i > n - r; i--) {
            up *= i;
            down *= (i - n + r);
        }

        return (up / down);

    }

    static class Tree {

        boolean root;
        int value;
        int lCnt;
        int rCnt;
        Tree leftNode;
        Tree rightNode;

        Tree(boolean root, int value) {
            this.root = root;
            this.value = value;
            this.lCnt = 0;
            this.rCnt = 0;
        }

        void add(int value) {
            Tree t = this;

            while (true) {
                if (t.value > value) {
                    t.lCnt++;
                    if (t.leftNode == null) {
                        t.leftNode = new Tree(false, value);
                        break;
                    } else {
                        t = t.leftNode;
                    }

                } else {
                    t.rCnt++;
                    if (t.rightNode == null) {
                        t.rightNode = new Tree(false, value);
                        break;
                    } else {
                        t = t.rightNode;
                    }
                }
            }
        }


    }

    static void getCandidates(Tree tree) {

        if (tree == null) {
            return;
        }

        ANS = (ANS * comb(tree.lCnt + tree.rCnt, tree.lCnt)) % 9999991;
        getCandidates(tree.leftNode);
        getCandidates(tree.rightNode);
    }

}
