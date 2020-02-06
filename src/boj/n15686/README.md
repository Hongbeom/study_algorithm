## 백준 15686 - [치킨 배달](https://www.acmicpc.net/problem/15686)

### 풀이법

1. m개의 치킨집을 순열로 뽑는다.

```JAVA
static void candidates(List<int[]> list, int[] tm, int start, int value, int max) {


    if (start == tm.length) {
        list.add(tm);
        return;
    }

    if (value == max) {
        return;
    }


    tm[start] = value;
    candidates(list, tm.clone(), start + 1, value + 1, max);
    candidates(list, tm.clone(), start, value + 1, max);
}
```

2. 선택 후보를 map에다 표시하고 bfs를 이용하여 거리를 구한다. - 후보에 대한 거리 최솟값도 갱신해줌.

```JAVA
static int bfs(int[][] map, int[] init) {
    boolean[][] visited = new boolean[map.length][map[0].length];
    visited[init[0]][init[1]] = true;
    Queue<int[]> queue = new LinkedList<>();

    queue.add(init);
    int distance = 0;
    root:
    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int a = current[0];
        int b = current[1];
        int step = current[2];

        for (int i = 0; i < 4; i++) {
            int na = a + H[i];
            int nb = b + W[i];

            if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                continue;
            }

            if (visited[na][nb]) {
                continue;
            }

            if (map[na][nb] == 2) {
                distance = step + 1;
                break root;
            }

            visited[na][nb] = true;
            queue.add(new int[]{na, nb, step + 1});
        }
    }
    return distance;
}
```
