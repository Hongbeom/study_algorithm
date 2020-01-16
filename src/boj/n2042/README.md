## 백준 2042 - [구간 합 구하기](https://www.acmicpc.net/problem/2042)


세그먼트 트리 이용, 각 연산을 O(logN) 으로 수행한다.

### 풀이법 

1. 세그먼트 트리를 구현

```JAVA
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
```

- 멤버로는 long 배열, 배열 사이즈는 n으로 결정된다.
  - 4 * n 의 크기로 배열을 잡으면 충분하다.
  - 정확한 배열의 사이즈는 N이 2의 제곱꼴인 경우에 Full Binary Tree, 그 때 높이는 logN ->  2*N-1.
  - N이 2의 제곱꼴이 아닌 경우에 높이가 H = [logN], 배열의 크기는 2^(H+1) - 1
- root 노드의 인덱스는 1, 자식노드의 인덱스는 왼쪽, 오른쪽 각각 2*node, 2*node + 1
- 노드는 그 구간의 합을 나타낸다 -> 루트는 모든 구간, 왼쪽 자식은 0 ~ (n/2), 오른쪽 자식은 (n/2) + 1 ~ n
- 재귀적으로 init 해준다 -> 기저조건은 left == right
- sum 과 update 모두 root 에서부터 시작 -> 구간에 대한 조건으로 인해 update 및 sum 을 구한다.

2. 인풋에 대해 sum 과 update 수행

~~~JAVA
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
~~~