## 백준 18223 - [민준이와 마산 그리고 건우](https://www.acmicpc.net/problem/18223)

### 풀이법

1. 다익스트라 알고리즈 활용, p번 정점을 들리는 지 체크한다.


```JAVA
static boolean check(List<int[]>[] graph, int start, int p) {
    int[] distance = new int[graph.length];
    boolean[] check = new boolean[graph.length];
    check[p] = true;
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

        if (graph[node] != null) {
            for (int[] next : graph[node]) {
                int nxt = next[0];
                int nd = next[1];

                if (d + nd <= distance[nxt]) {
                    if (nxt == p || check[node]) {
                        check[nxt] = true;
                    } else if (d + nd != distance[nxt]) {
                        check[nxt] = false;
                    }
                    distance[nxt] = d + nd;
                    pq.add(new int[]{nxt, d + nd});
                }
            }
        }
    }
    return check[graph.length - 1];
}
```
