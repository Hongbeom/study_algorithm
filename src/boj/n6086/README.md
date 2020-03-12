## 백준 6086 - [최대유량](https://www.acmicpc.net/problem/6086)

에드몬드 카프 알고리즘 이용 최대유량 구하기

### 풀이법 
1. 물은 양방향으로 흐를 수 있고, 한 지점에서 다른 지점으로 경로가 많을 수 있다.
- capacity를 양방향으로 준다.
- 같은 경로가 들어오면 capacity를 계속 더해준다.
~~~JAVA
for (int i = 0; i < n; i++) {
    st = new StringTokenizer(br.readLine());
    int from = st.nextToken().charAt(0) - A;
    int to = st.nextToken().charAt(0) - A;
    int c = Integer.parseInt(st.nextToken());
    if (to > 25) {
        to -= 6;
    }
    if (from > 25) {
        from -= 6;
    }

    if (GRAPH[to] == null) {
        GRAPH[to] = new ArrayList<>();
    }

    if (GRAPH[from] == null) {
        GRAPH[from] = new ArrayList<>();
    }

    if (CAP[from][to] != 0 || CAP[to][from] != 0)  {
        CAP[from][to] += c;
        CAP[to][from] += c;
    }else{
        GRAPH[from].add(to);
        GRAPH[to].add(from);
        CAP[from][to] = c;
        CAP[to][from] = c;     
    }
}
~~~

2. 에드몬드 카프 알고리즘을 이용해 최대 유량을 구한다.
~~~JAVA
static int maxFlow(int start, int end) {
    int result = 0;
    // 무슨 노드에 거쳤는지를 알기 위한 int형 visited 배열
    int[] visited = new int[N];
    while (true) {
        // visited 배열 초기화
        Arrays.fill(visited, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        // bfs로 경로 탐색.
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (GRAPH[current] != null) {
                for (int next : GRAPH[current]) {
                    // 방문하지 않은 노드이고 흘러갈 유량이 남았을때 노드 선택.
                    if (visited[next] == -1 && CAP[current][next] - FLOW[current][next] > 0) {
                        queue.add(next);
                        // from으로 방문 표시
                        visited[next] = current;
                        // next가 end일시 bfs 종료.
                        if (next == end) break;
                    }
                }
            }
        }
        // 모든 경로를 탐색했으므로(더이상 end로 갈 수 있는 유량이 없는 경우) 종료.
        if (visited[end] == -1) break;

        // 최소 플로우를 찾기 위해 max값으로 초기화;
        int flow = Integer.MAX_VALUE;
        // 경로의 최소 유량을 찾아준다.
        for (int i = end; i != start; i = visited[i]) {
            flow = Math.min(flow, CAP[visited[i]][i] - FLOW[visited[i]][i]);
        }
        // 최소 유량을 각 경로에 없데이트 해준다. 이때, 음의 유량도 고려해 준다.
        for (int i = end; i != start; i = visited[i]) {
            FLOW[visited[i]][i] += flow;
            FLOW[i][visited[i]] -= flow;
        }
        result += flow;
    }

    return result;
}
~~~