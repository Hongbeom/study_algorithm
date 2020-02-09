## 백준 16236 - [아기상어](https://www.acmicpc.net/problem/16236)

### 풀이법

1. 상어는 규칙에 따라 상, 하, 좌, 우 로 움직일 수 있다.
2. bfs를 이용하여 먹을 수 있는 물고기를 탐색, 존재하면 먹음, 존재안하면 끝



```JAVA
static boolean bfs(int[][] map, BabyShark baby) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[map.length][map[0].length];
    visited[baby.i][baby.j] = true;

    int[] eat = null;
    queue.add(new int[]{baby.i, baby.j, 0});
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

            // 못먹는 상황
            if (map[na][nb] > baby.size) {
                continue;
            }

            visited[na][nb] = true;

            // 먹을 수 있는 상황
            if (map[na][nb] > 0 && map[na][nb] < baby.size) {
                if (eat == null) {
                    eat = new int[]{na, nb, step + 1};
                } else {
                    if (eat[2] == step + 1) {
                        if (eat[0] > na) {
                            eat[0] = na;
                            eat[1] = nb;
                            eat[2] = step + 1;
                        } else if (eat[0] == na) {
                            if (eat[1] > nb) {
                                eat[0] = na;
                                eat[1] = nb;
                                eat[2] = step + 1;
                            }
                        }
                    }
                }
                continue;
            }
            queue.add(new int[]{na, nb, step + 1});
        }
    }

    if (eat == null) {
        return false;
    }
    map[eat[0]][eat[1]] = 0;
    baby.eat(eat[0], eat[1], eat[2]);

    return true;
}
```
