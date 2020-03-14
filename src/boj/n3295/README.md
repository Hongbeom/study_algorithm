## 백준 3295 - [단방향 링크 네트워크](https://www.acmicpc.net/problem/3295)

### 풀이법

1. 링과 선형배열을 이루기 위해서는 노드 사이에 간선이 한개, 방향이 같은 간선.
2. 링과 선형배열의 가치는 구조를 이루는 단방향 간선의 갯수이다.
3. 노드들 사이에서 단방향 매칭, 즉 이분매칭의 갯수가 최대 가치이다.

~~~JAVA
static boolean dfs(int from) {
    if (GRAPH[from] != null) {
        for (int to : GRAPH[from]) {
            if (VISITED[to]) {
                continue;
            }
            VISITED[to] = true;
            if (MATCH[to] == -1 || dfs(MATCH[to])) {
                MATCH[to] = from;
                return true;
            }
        }
    }
    return false;
}
~~~
