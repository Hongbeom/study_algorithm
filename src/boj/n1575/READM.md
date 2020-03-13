## 백준 1575 - [졸업](https://www.acmicpc.net/problem/1575)

이분 매칭 + 최소비용 최대 유량

### 초기 잘못된 접근
1. dfs를 이용한 이분매칭을 이용하였다.
2. 매칭된 수업을 작은것부터 뽑으려고 리스트를 소팅해 주었다.
3. dfs를 이용하여 최솟값으로 매칭이 가능한지 판별하는 로직을 못세웠다... 재귀적으로 잘만 하면 가능할듯 하다.

### 풀이법

1. 조건은 주어진 target만큼 수업과 매칭해야 한다.
2. 수업은 조건이 겹치면 안되게 매칭되어야 한다.
3. src -> 조건 -> 수업 -> sink 의 형태로 매칭해준다.
  - 이때, 수업 -> sink 간선의 용량은 무조건 1이다.
  - src -> 조건 간선의 용량은 조건에 만족해야하는 수업의 갯수 이다.
  - cost[조건][수업]은 수업의 숫자로 둔다 (COST[수업][조건] = -1 * 거리[수업][조건])
  - 이때, 이미 들은 수업들의 cost를 min value로 해주어, 이미 들은 수업을 먼저 매칭하게 해준다.
  
~~~JAVA
taken = new boolean[101];
for (int i = 0; i < p; i++) {
    taken[Integer.parseInt(st.nextToken())] = true;
}

int n = Integer.parseInt(br.readLine());
src = 0;
sink = n + 101;
size = sink + 1;

CAP = new int[size][size];
FLOW = new int[size][size];
COST = new int[size][size];
GRAPH = new ArrayList[size];

for (int i = 0; i < size; i++) {
    GRAPH[i] = new ArrayList<>();
}
for (int i = 101; i <= 100 + n; i++) {
    st = new StringTokenizer(br.readLine());
    int t = Integer.parseInt(st.nextToken());
    target += t;
    int lecN = Integer.parseInt(st.nextToken());
    GRAPH[src].add(i);
    GRAPH[i].add(src);
    CAP[src][i] = t;
    for (int j = 0; j < lecN; j++) {
        int lecture = Integer.parseInt(st.nextToken());
        GRAPH[i].add(lecture);
        GRAPH[lecture].add(i);
        CAP[i][lecture] = Integer.MAX_VALUE;

        if (taken[lecture]) {
            COST[i][lecture] = Integer.MIN_VALUE;
        } else {
            COST[i][lecture] = lecture;
        }
        COST[lecture][i] = -1 * COST[i][lecture];
    }
}

for (int i = 1; i < 101; i++) {
    CAP[i][sink] = 1;
    GRAPH[i].add(sink);
    GRAPH[sink].add(i);
}
~~~
3. SPFA 알고리즘을 활용한 MCMF -> 최소 비용으로 최대로 매칭해 준다.
  - source에서 sink로 가는 최소비용으로 유량이 있는 경우는 매칭이 된 경우이다.
  - 이때, sink이전에 방문한 노드는 바로 수업이다.
  - 만약 매칭된 갯수가 문제에서 요구하는 target들의 합보다 적은 경우는 졸업을 못하는 경우이다. 
~~~JAVA
static List<Integer> maxFlowMinCost(int start, int end) {
    int cnt = 0;
    int[] distance = new int[size];
    int[] visited = new int[size];
    boolean[] inQueue = new boolean[size];
    List<Integer> ansList = new ArrayList<>();
    while (true) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, -1);
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

                    if (end == next) {
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
            FLOW[visited[i]][i] += flow;
            FLOW[i][visited[i]] -= flow;
        }
        if (!taken[visited[end]]) {
            ansList.add(visited[end]);
        }
        cnt++;
    }

    if (cnt < target) {
        return null;
    } else {
        return ansList;
    }
}
~~~

