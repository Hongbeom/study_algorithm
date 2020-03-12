## 백준 2188 - [축사 배정](https://www.acmicpc.net/problem/2188)

### 풀이법
1. dfs를 이용한 이분매칭.

~~~JAVA
static boolean dfs(int current) {

    if (graph[current] != null) {
        for (int target : graph[current]) {

            if (visited[target]) {
                continue;
            }
            visited[target] = true;

            if (matching[target] == -1 || dfs(matching[target])) {
                matching[target] = current;
                return true;
            }
        }
    }

    return false;
}
~~~

- 각 소에 대해 일에 대해 탐색
- 매칭이 없다면 매칭해주고 true 반환
- 매칭이 있다면 매칭된 축사를 또 dfs로 탐색하여 다른 축사로 매칭이 가능한지 알아봄.
- visited 배열은 소에 대해 처음으로 dfs를 실행할때 마다 초기화 해주어야 한다.
