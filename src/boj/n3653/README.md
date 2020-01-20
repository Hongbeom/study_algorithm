## 백준 3653 - [영화 수집](https://www.acmicpc.net/problem/3653)

세그먼트 트리, 펜윅 트리 풀이법 2가지 존재

### 세그먼트 트리 풀이법

1. 영화가 총 n개 존재하고 쿼리가 총 m개 존재하므로 n+m 크기의 배열을 구성.
- 앞의 m개는 0, 뒤의 n개는 1로 초기화
- 영화가 선택되면 맨 앞으로 이동하므로 m개 자리의 뒤에서부터 1로 초기화 해준다.
- 인덱스를 관리하는 배열을 이용해 0 에서부터 자기 자신 - 1 까지의 구간합을 구한다.
~~~JAVA
int[] arr = new int[n + m];
int[] idx = new int[n];

for (int i = 0; i < n; i++) {
    idx[i] = m + i;
}

for (int i = m; i < n + m; i++) {
    arr[i] = 1;
}

SegmentTree tree = new SegmentTree(arr);
st = new StringTokenizer(br.readLine());
int moveId = m - 1;
for (int i = 0; i < m; i++) {
    int id = Integer.parseInt(st.nextToken()) - 1;
    System.out.print(tree.query(1, 0, n + m - 1, 0, idx[id] - 1) + " ");
    tree.update(1, 0, n + m - 1, idx[id], -1);
    tree.update(1, 0, n + m - 1, moveId, 1);
    idx[id] = moveId;
    moveId--;
}
~~~
```JAVA
static class SegmentTree {
    int[] tree;

    SegmentTree(int[] arr) {

        tree = new int[arr.length * 4];

        init(arr, 1, 0, arr.length - 1);

    }

    int init(int[] arr, int node, int left, int right) {
        if (left == right) {
            return tree[node] = arr[left];
        }
        int mid = (left + right) / 2;
        tree[node] += init(arr, node * 2, left, mid);
        tree[node] += init(arr, node * 2 + 1, mid + 1, right);

        return tree[node];
    }

    int query(int node, int left, int right, int a, int b) {
        if (a > right || b < left) {
            return 0;
        } else if (a <= left && right <= b) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, a, b)
                + query(node * 2 + 1, mid + 1, right, a, b);

    }

    void update(int node, int left, int right, int id, int diff) {
        if (id > right || id < left) {
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
```
