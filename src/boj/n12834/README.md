## 백준 12834 - [주간 미팅](https://www.acmicpc.net/problem/12834)

### 풀이법

1. 주어진 N개의 노드에서 A와 B로 가는 최단거리를 모두 구하면 되는 문제.
  - 음의 간선이 주어지지 않으므로 다익스트라 알고리즘 활용
  - 시간복잡도: N * V * log(V)
~~~JAVA
static int dijkstra(int start) {
    int d = 0;
    int[] distance = new int[GRAPH.length];
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
        int here = current[0];
        int dist = current[1];
        if (dist > distance[here]) {
            continue;
        }
        if (GRAPH[here] != null) {
            for (int[] nextNode : GRAPH[here]) {
                int next = nextNode[0];
                int nextDist = nextNode[1];
                if (distance[here] != Integer.MAX_VALUE &&
                        distance[here] + nextDist < distance[next]) {
                    distance[next] = distance[here] + nextDist;
                    pq.add(new int[]{next, distance[next]});
                }
            }
        }
    }

    if (distance[A] == Integer.MAX_VALUE) {
        d -= 1;
    } else {
        d += distance[A];
    }

    if (distance[B] == Integer.MAX_VALUE) {
        d -= 1;
    } else {
        d += distance[B];
    }

    return d;

}
~~~
