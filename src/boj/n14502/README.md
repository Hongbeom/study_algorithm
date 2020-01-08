## 백준 14502 - [연구소](https://www.acmicpc.net/problem/14502)


### 풀이법

1. 벽을 총 3개 세워야 하므로 후보지 list를 만들고 3중 포문을 안에서 bfs

```JAVA
for (int i = 0; i < candidates.size() - 2; i++) {
    for (int j = i + 1; j < candidates.size() - 1; j++) {
        for (int k = j + 1; k < candidates.size(); k++) {
            boolean[][] nv = clone(visited);

            nv[candidates.get(i)[0]][candidates.get(i)[1]] = true;
            nv[candidates.get(j)[0]][candidates.get(j)[1]] = true;
            nv[candidates.get(k)[0]][candidates.get(k)[1]] = true;

            answer = Math.max(answer, bfs(virus, nv, safe));
        }
    }
}
```

~~~JAVA
static int bfs(List<int[]> virus, boolean[][] visited, int safe) {

    Queue<int[]> queue = new LinkedList<>(virus);

    while (!queue.isEmpty()) {

        int[] current = queue.poll();
        int a = current[0];
        int b = current[1];

        for (int i = 0; i < 4; i++) {
            int na = a + H[i];
            int nb = b + W[i];

            if (na < 0 || nb < 0 || na >= visited.length || nb >= visited[0].length) {
                continue;
            }

            if (visited[na][nb]) {
                continue;
            }

            safe--;
            visited[na][nb] = true;
            queue.add(new int[]{na, nb});
        }

    }

    return safe;
}
~~~