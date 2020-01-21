## 백준 1613 - [역사](https://www.acmicpc.net/problem/1613)

플로이드 워셜 알고리즘을 사용한 전후관계 파악 - DFS, BFS 풀이 가능

### 풀이법

1. 인풋으로 들어오는 값에는 모순이 없다 -> 사이클이 존재하지 않는다.
2. i에서 j로 최단거리가 존재한다 -> i사건이 j사건보다 먼저 일어난것.
- 모순이 없으므로 j에서 i까지의 최단거리는 존재하지 않는다.
3. i에서 j, j에서 i까지의 최단거리가 존재하지 않는다 -> 전후관계를 파악할 수 없다.

~~~JAVA
for (int i = 0; i < s; i++) {
    st = new StringTokenizer(br.readLine());
    int from = Integer.parseInt(st.nextToken()) - 1;
    int to = Integer.parseInt(st.nextToken()) - 1;

    if (relation[from][to] == Integer.MAX_VALUE
            && relation[to][from] == Integer.MAX_VALUE) {
        System.out.println(0);
    } else if (relation[from][to] == Integer.MAX_VALUE) {
        System.out.println(-1);
    } else {
        System.out.println(1);
    }
}
~~~

~~~JAVA
static void floyd(int[][] relation) {
    int n = relation.length;

    for (int k = 0; k < n; k++) {
        from:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (relation[i][k] == Integer.MAX_VALUE) {
                    continue from;
                }

                if (relation[k][j] == Integer.MAX_VALUE) {
                    continue;
                }

                if (relation[i][k] + relation[k][j] < relation[i][j]) {
                    relation[i][j] = relation[i][k] + relation[k][j];
                }
            }
        }
    }
}
~~~