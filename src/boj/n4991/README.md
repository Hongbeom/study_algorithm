## 백준 4991 - [로봇 청소기](https://www.acmicpc.net/problem/4991)

BFS와 와 순열
### 풀이법 

1. 청소기의 초기 위치도 먼지로 취급하고 각 먼지들의 최단거리를 bfs로 먼저 구해놓는다.


```JAVA
for (int i = 0; i < dustList.size() - 1; i++) {
    for (int j = i + 1; j < dustList.size(); j++) {
        distance[i][j] = bfs(map, dustList.get(i), dustList.get(j));
        distance[j][i] = distance[i][j];
    }
}
```

```JAVA
static int bfs(char[][] map, int[] p1, int[] p2) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[map.length][map[0].length];
    visited[p1[0]][p1[1]] = true;
    queue.add(new int[]{p1[0], p1[1], 0});

    while (!queue.isEmpty()) {

        int[] current = queue.poll();
        int a = current[0];
        int b = current[1];
        int step = current[2];

        for (int i = 0; i < 4; i++) {
            int na = a + H[i];
            int nb = b + W[i];

            if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length || visited[na][nb]) {
                continue;
            }

            if (na == p2[0] && nb == p2[1]) {
                return step + 1;
            }

            if (map[na][nb] != 'x') {
                visited[na][nb] = true;
                queue.add(new int[]{na, nb, step + 1});
            }

        }
    }

    return -1;

}
```
- 초기에 이 거리를 먼저 구하지 않고, 경로의 경우에수를 판단할때마다 구해서 시간초과가 발생.

2. 청소기의 위치를 시작점으로 경로의 경우의 수를 구한 뒤 최소 거리를 구한다.

~~~JAVA
List<int[]> candidates = new ArrayList<>();
getCandidate(candidates, new int[dustList.size()], new int[dustList.size()], 0);
int answer = -1;
candidate:
for (int[] candidate : candidates) {
    int tmp = 0;
    for (int i = 0; i < candidate.length - 1; i++) {
        int p1 = candidate[i];
        int p2 = candidate[i + 1];

        if (distance[p1][p2] == -1) {
            continue candidate;
        }

        tmp += distance[p1][p2];
    }

    if (answer == -1) {
        answer = tmp;
    } else {
        answer = Math.min(answer, tmp);
    }

}
~~~

~~~JAVA
static void getCandidate(List<int[]> list, int[] tm, int[] check, int start) {
    if (start == tm.length) {
        list.add(tm);
    }

    if (start == 0) {
        check[start]--;
        tm[start] = 0;
        getCandidate(list, tm, check, start + 1);
    } else {
        for (int i = 0; i < tm.length; i++) {
            if (check[i] == 0) {
                check[i]--;
                tm[start] = i;
                getCandidate(list, tm.clone(), check.clone(), start + 1);
                check[i]++;
            }
        }
    }
}
~~~
