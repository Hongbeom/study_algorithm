## 백준 2843 - [마블](https://www.acmicpc.net/problem/2843)

간선을 삭제한다? -> 오프라인 쿼리를 떠올려 보자


### 초기 잘못된 접근

1. 그래프와 간선이 주어지므로 union-find를 이용하여 해결하려 하였다.
- 하지만, 간선을 삭제하였을때, 어떤식으로 처리를 해야할지 몰랐다.
2. 한 노드에는 한개의 간선만 있으므로, 각각 간선을 삭제하면서 탐색하는 방법을 이용하여 첫번째로 문제를 풀었다.
- cycle 판단은 visited 배열을 이용함.

~~~JAVA
static int search(int[] vertex, int first) {
    boolean[] visited = new boolean[vertex.length];

    int current = first;
    visited[current] = true;
    while (true) {
        int next = vertex[current];

        if (next == -1) {
            return current;
        }

        if (visited[next]) {
            return -1;
        }

        visited[next] = true;
        current = next;
    }

}
~~~

- 메모리 초과... 비효율적인 방법.

### 풀이법

1. 간선을 삭제한다? -> 거꾸로 하면 간선을 생성한다! 오프라인 쿼리.
2. union find 알고리즘 이용 -> 부모는 간선 방향으로 결정된다.
3. cycle 배열을 만들고, cycle이 발생할때는 union중 각각의 parent가 같은 경우이다.
- 두 노드의 parent가 같을 경우, 그 parent의 자식들은 모두 cycle이 발생 -> cycle 배열을 이용해 cycle 판단.
~~~JAVA
static void union(int[] parent, int a, int b, boolean[] cycle) {
    a = getParent(parent, a);
    b = getParent(parent, b);
    
    if (a == b) {
        cycle[a] = true;
    } else {
        parent[a] = b;
    }
}
~~~
