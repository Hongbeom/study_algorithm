## 백준 16234 - [인구 이동](https://www.acmicpc.net/problem/16234)

### 풀이법

1. bfs를 이용하여 국경이 열리는 경우를 찾고 인구수를 갱신해준다.

```JAVA
static boolean bfs(int[][] map, boolean[][] visited, int[] init) {
    Queue<int[]> queue = new LinkedList<>();
    visited[init[0]][init[1]] = true;
    int count = 1;
    int sum = map[init[0]][init[1]];
    List<int[]> change = new ArrayList<>();
    change.add(init);
    queue.add(init);

    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int a = current[0];
        int b = current[1];

        int p = map[a][b];

        for (int i = 0; i < 4; i++) {

            int na = a + H[i];
            int nb = b + W[i];

            if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length || visited[na][nb]) {
                continue;
            }

            int diff = Math.abs(p - map[na][nb]);
            if (L <= diff && diff <= R) {
                count++;
                sum += map[na][nb];
                visited[na][nb] = true;
                change.add(new int[]{na, nb});
                queue.add(new int[]{na, nb});
            }

        }
    }

    if (count != 1) {
        for (int[] spot : change) {
            map[spot[0]][spot[1]] = sum / count;
        }
        return true;
    }
    return false;
}
```

~~~JAVA
check = check || bfs(map, visited, new int[]{i, j});
~~~ 

- 이런식으로 코드를 돌리다가 bfs가 안돌아가서 해맸다... 앞이 true면 뒤에것을 실행 안해준다..         
