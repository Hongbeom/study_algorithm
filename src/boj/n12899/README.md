## 백준 12899 - [데이터 구조](https://www.acmicpc.net/problem/12899)


세그먼트 트리를 이용 k번째 원소를 효율적으로 찾아보쟈.


### 풀이법

1. 구간 합 세그먼트 트리를 구성한다.  
- 수가 존재할 경우 1, 존재 안할경우 0

2. k번째 원소를 노드를 탐색하며 찾는다.
~~~JAVA
int query(int node, int left, int right, int k) {
    if (left == right) {
        return left;
    }

    int mid = (left + right) / 2;
    if (k <= tree[node * 2]) {
        return query(node * 2, left, mid, k);
    } else {
        return query(node * 2 + 1, mid + 1, right, k - tree[node * 2]);
    }


}
~~~
