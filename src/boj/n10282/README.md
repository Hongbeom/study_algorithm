## 백준 10282 - [해킹](https://www.acmicpc.net/problem/10282)

다익스트라 알고리즘 이용

### 풀이법

1. 인풋으로 인접리스트를 만들고, 인접리스트로 다익스트라 알고리즘 사용

~~~JAVA
static int[] dijkstra(List<int[]>[] edges, int start) {
    int[] distance = new int[edges.length];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[start] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1[1] > o2[1]) {
            return 1;
        } else if (o1[1] < o2[1]) {
            return -1;
        }
        return 0;
    });

    pq.add(new int[]{start, 0});

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int node = current[0];
        int d = current[1];

        if (distance[node] < d) {
            continue;
        }

        for (int[] edge : edges[node]) {
            int next = edge[0];
            int nextDist = d + edge[1];

            if (nextDist < distance[next]) {
                distance[next] = nextDist;
                pq.add(new int[]{next, nextDist});
            }
        }
    }

    return distance;
}
~~~

- PriorityQueue (Heap) 을 사용하여 O(NlogN)
- 먼저 자기 자신부터 탐색하여 인접 노드에 대한 distance를 초기화.
- Heap으로 거리가 가장 작은 노드를 뽑고 그 노드에 인접하여 distance를 초기화 하는 방식.
- 체크를 통해 최단거리가 아닌 노드들을 거른다.
