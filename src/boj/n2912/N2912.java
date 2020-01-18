package boj.n2912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class N2912 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] hat = new int[n];
        st = new StringTokenizer(br.readLine());

        ArrayList<Integer>[] colorOrder = new ArrayList[c + 1];

        for (int i = 0; i < n; i++) {
            int color = Integer.parseInt(st.nextToken());
            hat[i] = color;

            if (colorOrder[color] == null) {
                colorOrder[color] = new ArrayList<>();
            }
            colorOrder[color].add(i);
        }
        int m = Integer.parseInt(br.readLine());

        SegmentTree tree = new SegmentTree(hat, c);

        query:
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            int[] result = tree.query(1, 0, n - 1, a, b);
            int domi = result[0];

            int apr = getUpperBound(colorOrder[domi], 0, colorOrder[domi].size() - 1, b)
                    - getLowerBound(colorOrder[domi], 0, colorOrder[domi].size() - 1, a);

            if (apr >= (b - a + 1) / 2 + 1) {
                System.out.println("yes " + domi);
            } else {
                System.out.println("no");
            }

        }

    }

    static int getLowerBound(ArrayList<Integer> list, int start, int end, int key) {

        int mid = (start + end) / 2;
        int current = list.get(mid);

        if (current >= key) {
            if (mid == 0 || list.get(mid - 1) < key) {
                return mid;
            }
            return getLowerBound(list, start, mid, key);
        } else {
            return getLowerBound(list, mid + 1, end, key);
        }

    }

    static int getUpperBound(ArrayList<Integer> list, int start, int end, int key) {

        if (list.get(list.size() - 1) <= key) {
            return list.size();
        }

        int mid = (start + end) / 2;
        int current = list.get(mid);

        if (current > key) {
            if (mid == 0 || list.get(mid - 1) <= key) {
                return mid;
            }
            return getUpperBound(list, start, mid, key);
        } else {
            return getUpperBound(list, mid + 1, end, key);
        }

    }


    static class SegmentTree {
        int[][] tree;

        SegmentTree(int[] arr, int c) {
            tree = new int[arr.length * 4][2];

            init(arr, 1, 0, arr.length - 1);

        }

        private int[] init(int[] arr, int node, int left, int right) {

            if (left == right) {
                tree[node][0] = arr[left];
                tree[node][1]++;
                return tree[node];
            }

            int mid = (left + right) / 2;
            int[] leftChild = init(arr, node * 2, left, mid);
            int[] rightChild = init(arr, node * 2 + 1, mid + 1, right);

            if (leftChild[0] == rightChild[0]) {
                tree[node][0] = leftChild[0];
                tree[node][1] = leftChild[1] + rightChild[1];

            } else {

                if (leftChild[1] > rightChild[1]) {
                    tree[node][0] = leftChild[0];
                    tree[node][1] = leftChild[1] - rightChild[1];
                } else {
                    tree[node][0] = rightChild[0];
                    tree[node][1] = rightChild[1] - leftChild[1];
                }

            }

            return tree[node];
        }

        int[] query(int node, int left, int right, int a, int b) {

            if (a > right || b < left) {
                return null;
            } else if (a <= left && right <= b) {
                return tree[node];
            }

            int mid = (left + right) / 2;

            int[] leftChild = query(node * 2, left, mid, a, b);
            int[] rightChild = query(node * 2 + 1, mid + 1, right, a, b);

            if (leftChild == null) {
                return rightChild;
            } else if (rightChild == null) {
                return leftChild;
            } else {
                int[] result = new int[2];
                if (leftChild[0] == rightChild[0]) {
                    result[0] = leftChild[0];
                    result[1] = leftChild[1] + rightChild[1];
                } else {
                    if (leftChild[1] > rightChild[1]) {
                        result[0] = leftChild[0];
                        result[1] = leftChild[1] - rightChild[1];
                    } else {
                        result[0] = rightChild[0];
                        result[1] = rightChild[1] - leftChild[1];
                    }
                }
                return result;
            }
        }
    }
}
