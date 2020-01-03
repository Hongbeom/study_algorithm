package boj.n14458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class N14458_WRONG {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] order = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            int r = Integer.parseInt(br.readLine()) - 1;
            order[r] = i;
        }

        for (int i = 0; i < n; i++) {
            int l = Integer.parseInt(br.readLine()) - 1;
            left[order[l]] = i;
            right[i] = order[l];
        }

        HTree init = new HTree(left[0], true);
        long answer = 0;
        for (int i = 1; i < left.length; i++) {
            answer += init.insert(left[i]);
        }

        long compare = answer;
        for (int k = n - 1; k > 0; k--) {
            int p = left[k] + 1;
            compare += p - 1 - (n - p);
            answer = Math.min(compare, answer);
        }

        init = new HTree(right[0], true);
        compare = 0;
        for (int i = 1; i < right.length; i++) {
            compare += init.insert(right[i]);
        }
        answer = Math.min(compare, answer);
        for (int k = n - 1; k > 0; k--) {
            int p = right[k];
            compare += p - (n - p - 1);
            answer = Math.min(compare, answer);
        }


        System.out.println(answer);

    }

    static class HTree {
        int value;
        int more;
        boolean isRoot;

        HTree leftChild;
        HTree rightChild;

        HTree(int value, boolean isRoot) {
            this.value = value;
            this.isRoot = isRoot;
            this.more = 0;
        }

        int insert(int value) {

            HTree t = this;

            while (true) {

                if (t.value > value) {
                    if (t.leftChild == null) {
                        t.leftChild = new HTree(value, false);
                        t.leftChild.more += t.more + 1;
                        t = t.leftChild;
                        break;
                    }
                    t = t.leftChild;
                } else {
                    t.more++;
                    if (t.leftChild != null) {
                        t.leftPlus();
                    }
                    if (t.rightChild == null) {
                        t.rightChild = new HTree(value, false);
                        t.rightChild.more += t.more - 1;

                        t = t.rightChild;

                        break;
                    }
                    t = t.rightChild;
                }
            }

            return t.more;
        }


        void leftPlus() {

            Queue<HTree> queue = new LinkedList<>();
            queue.add(this.leftChild);

            while (!queue.isEmpty()) {
                HTree t = queue.poll();
                t.more++;
                if (t.leftChild != null) {
                    queue.add(t.leftChild);
                }
                if (t.rightChild != null) {
                    queue.add(t.rightChild);
                }
            }
        }
    }


}


