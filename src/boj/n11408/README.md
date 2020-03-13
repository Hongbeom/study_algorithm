## 백준 11408 - [열혈강호 5](https://www.acmicpc.net/problem/11408)

이분매칭, MCMF 알고리즘 이용 (SPFA)

### 풀이법
1. 사람은 한개의 일을 할 수 있으므로 이분매칭.
2. 최대로 매칭하고, 최소 비용(월급)을 구해야 하므로 MCMF를 사용.
2. source = 0, sink = n+m+1, 1 ~ n은 사람, n + 1, n + m은 일.
  - source -> 사람 간선의 용량은 1(이분배칭 이므로)
  - 일 -> sink 간선의 용량은 1.
  - 사람 i에서 일 j로의 비용은 COST[i][j] (COST[j][i] = -1 * 거리[i][j])
  - 사람 i에서  j로의 간선의 용량은 MAX.
~~~JAVA
src = 0;
sink = n + m + 1;
size = sink + 1;

CAP = new int[size][size];
FLOW = new int[size][size];
COST = new int[size][size];
GRAPH = new ArrayList[size];
for (int i = 0; i < size; i++) {
    GRAPH[i] = new ArrayList<>();
}

for (int i = 1; i <= n; i++) {
    GRAPH[src].add(i);
    GRAPH[i].add(src);
    CAP[src][i] = 1;
    st = new StringTokenizer(br.readLine());
    int s = Integer.parseInt(st.nextToken());
    for (int j = 0; j < s; j++) {
        int task = Integer.parseInt(st.nextToken()) + n;
        int money = Integer.parseInt(st.nextToken());
        GRAPH[i].add(task);
        GRAPH[task].add(i);

        CAP[i][task] = Integer.MAX_VALUE;

        COST[i][task] = money;
        COST[task][i] = -1 * money;
    }
}
for (int i = n + 1; i <= n + m; i++) {
    GRAPH[i].add(sink);
    GRAPH[sink].add(i);
    CAP[i][sink] = 1;
}
~~~
3. SPFA 알고리즘을 활용한 MCMF, 즉 최대유량의 최소비용을 구한다.
  - 이분매칭이므로 거리의 합이 비용이 된다(유량이 모두 1이기 때문)
~~~JAVA
static int[] maxFlowMinCost(int start, int end) {
    int result = 0;
    int cnt = 0;

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
                        CAP[here][next] - FLOW[here][next] > 0 &&
                        distance[next] > distance[here] + COST[here][next]) {
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
            result += COST[visited[i]][i];
            FLOW[visited[i]][i] += flow;
            FLOW[i][visited[i]] -= flow;
        }
        cnt++;
    }

    return new int[]{cnt, result};

}
~~~
- 각 다음경로를 판단할때, 최단거리 이면서 유량이 존재하는 경로가 next. 
- visited를 조건으로 삼지 않는다.
- source에서 sink로의 경로가 있다는 것은 이분 매칭이므로 매칭이 한게 되었다는것을 의미

