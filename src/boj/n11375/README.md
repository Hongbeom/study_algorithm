## 백준 11375 - [열혈강호](https://www.acmicpc.net/problem/11375)

이분그래프 -> 이분매칭. 다양한 알고리즘이 존재


### 초기 잘못된 접근
1. 단순하게 생각하여 가장 일이 적은 애들부터 가장 겹침이 없는 일을 할당.
- 반례 존재.

### 풀이법
1. dfs를 이용한 이분매칭.

~~~JAVA
static boolean dfs(int node) {

    if (visited[node]) {
        return false;
    }

    visited[node] = true;

    for (int task : graph[node]) {

        if (matching[task] == 0 || dfs(matching[task])) {
            matching[task] = node;
            return true;
        }
    }

    return false;

}
~~~

- 각 사람에 대해 일에 대해 탐색
- 매칭이 없다면 매칭해주고 true 반환
- 매칭이 있다면 매칭된 사람을 또 dfs로 탐색하여 매칭이 가능한지 알아봄.

