package boj.n2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2042 {

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
            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                long diff = c - arr[b];
                arr[b] = c;
                tree.update(1, 0, n - 1, b, diff);
            } else {
                int c = Integer.parseInt(st.nextToken()) - 1;
                System.out.println(tree.sum(1, 0, n - 1, b, c));
            }

        }

    }

    static class SegmentTree {
        long[] tree;

        SegmentTree(long[] arr) {

            tree = new long[arr.length * 4];

            init(arr, 1, 0, arr.length - 1);
        }


        long init(long[] arr, int node, int left, int right) {

            if (left == right) {
                return tree[node] = arr[left];
            }
            int mid = (left + right) / 2;
            tree[node] += init(arr, node * 2, left, mid);
            tree[node] += init(arr, node * 2 + 1, mid + 1, right);

            return tree[node];
        }

        long sum(int node, int left, int right, int a, int b) {

            if (a > right || b < left) {
                return 0;
            } else if (a <= left && b >= right) {
                return tree[node];
            }
            int mid = (left + right) / 2;
            return sum(node * 2, left, mid, a, b) + sum(node * 2 + 1, mid + 1, right, a, b);

        }

        void update(int node, int left, int right, int id, long diff) {

            if (id < left || id > right) {
                return;
            }

            tree[node] += diff;

            if (left != right) {
                int mid = (left + right) / 2;
                update(node * 2, left, mid, id, diff);
                update(node * 2 + 1, mid + 1, right, id, diff);
            }
        }

    }
}
