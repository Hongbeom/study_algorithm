## 백준 13903 - [출근](https://www.acmicpc.net/problem/13903)

### 풀이법

1. 규칙에 대해 배열을 만들고 시작점(첫번째줄) 부터 BFS.


```JAVA
static int bfs(int[][] block, List<int[]> init) {
    Queue<int[]> queue = new LinkedList<>(init);
    boolean[][] visited = new boolean[block.length][block[0].length];
    
    for (int[] start : init) {
        visited[start[0]][start[1]] = true;
    }
    
    while (!queue.isEmpty()) {
        int[] current = queue.poll();
    
        int a = current[0];
        int b = current[1];
        int s = current[2];
    
        for (int i = 0; i < H.length; i++) {
            int na = a + H[i];
            int nb = b + W[i];
    
    
            if (na < 0 || nb < 0 || na >= block.length || nb >= block[0].length) {
                continue;
            }
    
            if (visited[na][nb]) {
                continue;
            }
    
            if (block[na][nb] == 1) {
                if (na == block.length - 1) {
                    return s + 1;
                }
                visited[na][nb] = true;
                queue.add(new int[]{na, nb, s + 1});
            }
    
        }
    }
    
    return Integer.MAX_VALUE;
}
```
