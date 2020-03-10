## 백준 3187 - [양치기 꿍](https://www.acmicpc.net/problem/3187)

### 풀이법

1. bfs를 이용하여 울타리 안의 양과 늑대의 수를 구해준다.
```JAVA
static int[] bfs(char[][] map, boolean[][] visited, int h, int w) {

     Queue<int[]> queue = new LinkedList<>();

     int sheep = 0;
     int wolf = 0;

     if (map[h][w] == 'v') {
         wolf++;
     } else if (map[h][w] == 'k') {
         sheep++;
     }

     visited[h][w] = true;
     queue.add(new int[]{h, w});

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

             if (visited[na][nb] || map[na][nb] == '#') {
                 continue;
             }

             if (map[na][nb] == 'v') {
                 wolf++;
             } else if (map[na][nb] == 'k') {
                 sheep++;
             }
             visited[na][nb] = true;
             queue.add(new int[]{na, nb});
         }
     }
     if (sheep > wolf) {
         return new int[]{sheep, 0};
     } else {
         return new int[]{0, wolf};
     }
 }
```

