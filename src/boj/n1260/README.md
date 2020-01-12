## 백준 1260 - [DFS와 BFS](https://www.acmicpc.net/problem/1260)

기본적인 dfs, bfs 문제

### 풀이법 

1. 탐색할 노드가 여러개라면 더 적은 노드를 탐색해야 하기 때문에 인접리스트를 오름차순으로 정렬해준다.
2. queue를 이용해 bfs, 재귀함수를 이용해 dfs


```JAVA
static void dfs(List<Integer>[] edges, int v, boolean[] visited){
    System.out.print((v + 1) + " ");

    for(int next : edges[v]){

        if(visited[next]){
            continue;
        }
        visited[next] = true;
        dfs(edges, next, visited);

    }
}

static void bfs(List<Integer>[] edges, int v) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[edges.length];
    visited[v] = true;
    queue.add(v);

    while (!queue.isEmpty()) {
        int current = queue.poll();
        System.out.print((current + 1) + " ");

        for (int next : edges[current]) {

            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            queue.add(next);
        }
    }
}
```
