## 백준 1012 - [유기농 배추](https://www.acmicpc.net/problem/1012)

### 풀이법

1. visited 와 map 을 이용해 원소 하니씩 bfs가 가능한 횟수를 카운트 해준다.

~~~JAVA
int answer = 0;
for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
        if (visited[i][j]) {
            continue;
        }

        if (map[i][j] == 1) {
            answer++;
            bfs(map, visited, i, j);
        }

    }
}
~~~

~~~JAVA
static void bfs(int[][] map, boolean[][] visited, int x, int y) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{x, y});
    visited[x][y] = true;

    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int a = current[0];
        int b = current[1];

        for (int i = 0; i < 4; i++) {
            int na = a + H[i];
            int nb = b + W[i];

            if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                continue;
            }

            if (visited[na][nb]) {
                continue;
            }

            if (map[na][nb] == 1) {
                visited[na][nb] = true;
                queue.add(new int[]{na, nb});
            }
        }
    }
}
~~~
