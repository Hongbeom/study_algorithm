## 백준 16496 - [소년 점프](https://www.acmicpc.net/problem/16496)

### 풀이법

1. 넉살, 스윙스, 창모가 3명이 이동 가능한 지점에서의 3명의 이동시간의 max가 min인 지점을 찾는다.
- bfs 이용.

```JAVA
static int[] bfs(int[] start) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[map.length][map[0].length];
    visited[start[0]][start[1]] = true;
    queue.add(start);

    int minT = Integer.MAX_VALUE;
    int count = 0;

    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int a = current[0];
        int b = current[1];
        int t = current[2];

        time[a][b][0] += 1;
        time[a][b][1] = Math.max(time[a][b][1], t);

        if (time[a][b][0] == 3) {

            if (time[a][b][1] < minT) {
                minT = time[a][b][1];
                count = 1;
            }else if(time[a][b][1] == minT){
                count++;
            }
        }

        for (int i = 0; i < 4; i++) {
            int na = a + H[i];
            int nb = b + W[i];

            if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                continue;
            }

            if (visited[na][nb]) {
                continue;
            }

            if (map[na][nb] == '0') {
                visited[na][nb] = true;
                queue.add(new int[]{na, nb, t + 1});
            }

        }
    }

    if(minT == Integer.MAX_VALUE){
        return null;
    }else{
        return new int[]{minT, count};
    }
}
```
