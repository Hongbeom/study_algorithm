## 백준 2667 - [단지번호붙이기](https://www.acmicpc.net/problem/2667)


### 풀이법

1. bfs를 이용해 풀이, visited를 새롭게 갱신하지 않고 사용


```JAVA
static int bfs(char[][] home, boolean[][] visited, int h, int w) {

    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{h, w});
    visited[h][w] = true;
    int cnt = 1;

    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int a = current[0];
        int b = current[1];

        for (int i = 0; i < 4; i++) {
            int na = a + H[i];
            int nb = b + W[i];

            if (na < 0 || nb < 0 || na >= home.length || nb >= home[0].length || visited[na][nb]) {
                continue;
            }

            if (home[na][nb] != '0') {
                cnt++;
                visited[na][nb] = true;
                queue.add(new int[]{na, nb});
            }
        }
    }

    return cnt;

}
```

