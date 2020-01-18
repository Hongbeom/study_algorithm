package boj.n2912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2912_WRONG {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] hat = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            hat[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        int m = Integer.parseInt(br.readLine());

        SegmentTree tree = new SegmentTree(hat, c);

        query:
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            int[] result = tree.query(1, 0, n - 1, a, b);

            for (int j = 0; j < result.length; j++) {
                int r = result[j];
                if (r >= (b - a + 1) / 2 + 1) {
                    System.out.println("yes " + (j + 1));
                    continue query;
                }
            }
            System.out.println("no");
        }

    }


    static class SegmentTree {
        int[][] tree;
        int c;
        int[] zero;

        SegmentTree(int[] arr, int c) {
            this.c = c;
            this.zero = new int[c];
            tree = new int[arr.length * 4][c];

            init(arr, 1, 0, arr.length - 1);

        }

        private int[] init(int[] arr, int node, int left, int right) {

            if (left == right) {
                tree[node][arr[left]]++;
                return tree[node];
            }

            int mid = (left + right) / 2;
            int[] leftChild = init(arr, node * 2, left, mid);

            for (int i = 0; i < c; i++) {
                tree[node][i] += leftChild[i];
            }

            int[] rightChild = init(arr, node * 2 + 1, mid + 1, right);

            for (int i = 0; i < c; i++) {
                tree[node][i] += rightChild[i];
            }

            return tree[node];
        }

        int[] query(int node, int left, int right, int a, int b) {

            if (a > right || b < left) {
                return zero;
            } else if (a <= left && right <= b) {
                return tree[node];
            }
            int mid = (left + right) / 2;

            int[] result = zero.clone();

            int[] leftResult = query(node * 2, left, mid, a, b);
            int[] rightResult = query(node * 2 + 1, mid + 1, right, a, b);

            for (int i = 0; i < c; i++) {
                result[i] += leftResult[i] + rightResult[i];
            }


            return result;
        }

    }

}
