## 백준 10999 - [구간 합 구하기 2](https://www.acmicpc.net/problem/10999)

세그먼트 트리 + lazy propagation

### 풀이법

1. value, lazy로 구성된 객체로 세그먼트 트리를 구성.

~~~JAVA
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
~~~

- lazy propagation을 이용해여 어느 range를 변경하는 작업을 O(logN) 으로 수행한다.
- sum과 update 중, 노드를 방문할때 lazy를 검사해준다.
  - lazy값이 존재하면 자신의 Value를 업데이트 해주고 자식들에게 전파.
- update의 마지막에 자식들이 update를 해주고, 마지막에 자신의 value를 두 자식의 합으로 다시 합쳐준다.