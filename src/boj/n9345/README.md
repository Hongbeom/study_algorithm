## 백준 9345 - [디지털 비디오 디스크(DVDs)](https://www.acmicpc.net/problem/9345)

### 풀이

1. 최대, 최소 세그먼트 트리를 이용한다.
- 어떤 구간과 다른 구간에서의 수가 바뀌면 최대 최소값이 변한다.

- 처음에는 누적합을 이용했지만 반례 존재 -> 1, 3, 5 -> 2, 3, 4 
~~~JAVA
static class MaxMinSegTree {

    int[][] tree;

    MaxMinSegTree(int n) {
        tree = new int[n * 4][2];
        init(1, 0, n - 1);
    }


    int[] init(int node, int left, int right) {
        if (left == right) {
            tree[node][0] = left;
            tree[node][1] = left;
            return tree[node];
        }

        int mid = (left + right) / 2;

        int[] leftNode = init(node * 2, left, mid);
        int[] rightNode = init(node * 2 + 1, mid + 1, right);


        tree[node][0] = Math.min(leftNode[0], rightNode[0]);
        tree[node][1] = Math.max(leftNode[1], rightNode[1]);

        return tree[node];
    }

    int[] query(int node, int left, int right, int a, int b) {


        if (a > right || b < left) {
            return null;
        } else if (a <= left && right <= b) {
            return tree[node];
        }

        int mid = (left + right) / 2;

        int[] leftResult = query(node * 2, left, mid, a, b);
        int[] rightResult = query(node * 2 + 1, mid + 1, right, a, b);

        if (leftResult == null) {
            return rightResult;
        } else if (rightResult == null) {
            return leftResult;
        } else {
            int[] result = new int[2];

            result[0] = Math.min(leftResult[0], rightResult[0]);
            result[1] = Math.max(leftResult[1], rightResult[1]);

            return result;
        }

    }

    void update(int node, int left, int right, int id, int value) {

        if (id < left || id > right) {
            return;
        } else if (left == right) {
            tree[node][0] = value;
            tree[node][1] = value;
            return;
        }

        int mid = (left + right) / 2;
        update(node * 2, left, mid, id, value);
        update(node * 2 + 1, mid + 1, right, id, value);

        tree[node][0] = Math.min(tree[node * 2][0], tree[node * 2 + 1][0]);
        tree[node][1] = Math.max(tree[node * 2][1], tree[node * 2 + 1][1]);

    }

}
~~~