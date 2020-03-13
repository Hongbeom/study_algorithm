## 백준 11405 - [책 구매하기](https://www.acmicpc.net/problem/11405)

MCMF 알고리즘 이용 (SPFA)

### 풀이법
1. 서점의 모든 책(모든 유량) 은 모든 사람들에게 배송된다.
2. source = 0, sink = n+m+1, 1 ~ n은 사람, n + 1, n + m은 서점으로 잡는다.
  - 사람들이 사려는 책의 갯수를 사람 -> sink 간선의 용량.
  - 서점에서 파는 책의 갯수롤 source -> 서점 간선의 용량.
  - 서점 i에서 사람 j로의 비용은 거리[i][j] (거리[j][i] = -1 * 거리[i][j])
  - 서점 i에서 사람 j로의 간선의 용량은 MAX.
~~~JAVA
src = 0;
sink = n + m + 1;
size = sink + 1;
CAP = new int[size][size];
COST = new int[size][size];
FLOW = new int[size][size];

GRAPH = new ArrayList[size];
for (int i = 0; i < size; i++) {
    GRAPH[i] = new ArrayList<>();
}

st = new StringTokenizer(br.readLine());
for (int i = 1; i <= n; i++) {
    GRAPH[i].add(sink);
    GRAPH[sink].add(i);
    CAP[i][sink] = Integer.parseInt(st.nextToken());
}

st = new StringTokenizer(br.readLine());
for (int i = n + 1; i <= n + m; i++) {
    GRAPH[i].add(src);
    GRAPH[src].add(i);
    CAP[src][i] = Integer.parseInt(st.nextToken());
}

for (int i = n + 1; i <= n + m; i++) {
    st = new StringTokenizer(br.readLine());
    for (int j = 1; j <= n; j++) {
        COST[i][j] = Integer.parseInt(st.nextToken());
        COST[j][i] = -1 * COST[i][j];
        // 서점과 사람의 간선은 순방향 간선만 용량을 max로 해준다.
        CAP[i][j] = Integer.MAX_VALUE;
        GRAPH[i].add(j);
        GRAPH[j].add(i);
    }
}
~~~
3. SPFA 알고리즘을 활용한 MCMF, 즉 최대유량의 최소비용을 구한다.
  - 비용은 유량 * 거리
~~~JAVA
static int maxFlowMinCost(int start, int end) {
    int result = 0;
    int[] visited = new int[size];
    int[] distance = new int[size];
    boolean[] inQueue = new boolean[size];
    while (true) {
        Arrays.fill(visited, -1);
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(inQueue, false);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        inQueue[start] = true;
        distance[start] = 0;
        while (!queue.isEmpty()) {
            int here = queue.poll();
            inQueue[here] = false;
            for (int next : GRAPH[here]) {
                if (distance[here] != Integer.MAX_VALUE &&
                        distance[here] + COST[here][next] < distance[next] &&
                        CAP[here][next] - FLOW[here][next] > 0) {
                    distance[next] = distance[here] + COST[here][next];
                    visited[next] = here;
                    if (next == end) {
                        break;
                    }
                    if (!inQueue[next]) {
                        queue.add(next);
                        inQueue[next] = true;
                    }
                }
            }
        }
        if (visited[end] == -1) break;
        int flow = Integer.MAX_VALUE;
        for (int i = end; i != start; i = visited[i]) {
            flow = Math.min(flow, CAP[visited[i]][i] - FLOW[visited[i]][i]);
        }

        for (int i = end; i != start; i = visited[i]) {
            result += flow * COST[visited[i]][i];
            FLOW[visited[i]][i] += flow;
            FLOW[i][visited[i]] -= flow;
        }
    }
    return result;
}
~~~
- 각 다음경로를 판단할때, 최단거리 이면서 유량이 존재하는 경로가 next. 
- visited를 조건으로 삼지 않는다.

