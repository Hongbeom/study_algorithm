## 백준 11657 - [타임머신](https://www.acmicpc.net/problem/11657)

음의 간선이 존재하는 최단거리 - 벨만 포드 알고리즘

### 풀이법

1. 벨만 포드 알고리즘 수행.

~~~JAVA
static int[] Bellman(List<Edge> list, int start, int n) {
    int[] distance = new int[n];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[start] = 0;

    for (int i = 0; i < n; i++) {
        boolean check = false;
        for (Edge edge : list) {
            int from = edge.from;
            int to = edge.to;
            if (distance[from] != Integer.MAX_VALUE && distance[from] + edge.weight < distance[to]) {
                distance[to] = distance[from] + edge.weight;
                check = true;
            }
        }
        if (!check) {
            break;
        } else {
            if (i == n - 1) {
                return null;
            }
        }
    }
    return distance;
}
~~~

- edge relaxation 을 V번 수행
  - 음수 사이클이 존재할때는 마지막 V번째에 값의 변화가 생겼을때.
  
### SPFA 풀이법

1. 벨만포드와 원리는 같다.
2. 벨만포드는 모든 간선에 대해서 업데이트(relaxation)를 수행하지만, SPFA는 바뀐 정점과 연결된 간선에 대해서만 업데이트(relaxation) 수행
3. 큐를 이용하여 바뀐 정점을 저장하고 정점과 연결된 간선들을 업데이트 해준다.
  - 이때 inQueue라는 배열을 이용하여 정점이 현재 큐에 있는지 없는지 판별.
  - 해당 정점이 n(v)번이상 방문되었다면 음수 사이클이 발생한것.
  
~~~JAVA
static int[] SPFA(int start, int n) {
    int[] distance = new int[n];
    // 노드를 몇번 방문했는지 표기하는 배열
    int[] visited = new int[n];
    // 큐에 존재하는지 확인하는 배열
    boolean[] inQueue = new boolean[n];
    Queue<Integer> queue = new LinkedList<>();

    queue.add(start);
    inQueue[start] = true;
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[start] = 0;
    visited[start]++;

    while (!queue.isEmpty()) {
        int current = queue.poll();
        inQueue[current] = false;

        if (edges[current] != null) {
            for (int[] edge : edges[current]) {
                int next = edge[0];
                int weight = edge[1];

                if (distance[current] != Integer.MAX_VALUE && distance[current] + weight < distance[next]) {
                    distance[next] = distance[current] + weight;

                    if (!inQueue[next]) {

                        if (++visited[next] == n) {
                            return null;
                        }
                        queue.add(next);
                        inQueue[next] = true;
                    }
                }
            }
        }
    }
    return distance;
}
~~~