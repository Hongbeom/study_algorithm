package boj.n10999;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N10999 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree tree = new SegmentTree(arr);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            if (a == 1) {
                long d = Long.parseLong(st.nextToken());
                tree.update_range(1, 0, n - 1, b, c, d);
            } else {
                System.out.println(tree.sum(1, 0, n - 1, b, c));
            }


        }

    }


    static class Node {
        long value;
        long lazy;

        Node() {
        }

    }


    static class SegmentTree {

        Node[] tree;


        SegmentTree(long[] arr) {

            tree = new Node[4 * arr.length];

            init(arr, 1, 0, arr.length - 1);

        }


        long init(long[] arr, int node, int left, int right) {

            tree[node] = new Node();

            if (left == right) {
                return tree[node].value = arr[left];
            }
            int mid = (left + right) / 2;
            tree[node].value += init(arr, node * 2, left, mid);
            tree[node].value += init(arr, node * 2 + 1, mid + 1, right);

            return tree[node].value;
        }

        long sum(int node, int left, int right, int a, int b) {

            if (tree[node].lazy != 0) {
                tree[node].value += (right - left + 1) * tree[node].lazy;
                if (left != right) {
                    tree[node * 2].lazy += tree[node].lazy;
                    tree[node * 2 + 1].lazy += tree[node].lazy;
                }
                tree[node].lazy = 0;
            }

            if (a > right || b < left) {
                return 0;
            } else if (a <= left && right <= b) {
                return tree[node].value;
            }
            int mid = (left + right) / 2;
            return sum(node * 2, left, mid, a, b) + sum(node * 2 + 1, mid + 1, right, a, b);


        }


        void update_range(int node, int left, int right, int a, int b, long diff) {

            if (tree[node].lazy != 0) {
                tree[node].value += (right - left + 1) * tree[node].lazy;

                if (left != right) {
                    tree[node * 2].lazy += tree[node].lazy;
                    tree[node * 2 + 1].lazy += tree[node].lazy;
                }

                tree[node].lazy = 0;
            }

            if (a > right || b < left) {
                return;
            }

            if (a <= left && right <= b) {
                tree[node].value += (right - left + 1) * diff;
                if (left != right) {
                    tree[node * 2].lazy += diff;
                    tree[node * 2 + 1].lazy += diff;
                }
                return;
            }
            int mid = (left + right) / 2;
            update_range(node * 2, left, mid, a, b, diff);
            update_range(node * 2 + 1, mid + 1, right, a, b, diff);

            tree[node].value = tree[node * 2].value + tree[node * 2 + 1].value;
        }


    }


}
