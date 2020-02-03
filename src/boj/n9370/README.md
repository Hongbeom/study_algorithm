## 백준 9370 - [미확인 도착지](https://www.acmicpc.net/problem/9370)
 
### 풀이법
 
1. 다익스트라 알고리즘을 이용해 출발점부터 각 다른 노드까지의 최단 거리를 구한다.
- 최단거리를 구할때, g - h 사이의 간선을 지나가는지 여부를 체크한다 - check 배열 이용

 
 ```JAVA
static boolean[] check(List<int[]>[] edges, int start, int g, int h) {

    int[] d = new int[edges.length];
    boolean[] check = new boolean[edges.length];
    Arrays.fill(d, Integer.MAX_VALUE);
    d[start] = 0;

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
        int dist = current[1];

        if (d[node] < dist) {
            continue;
        }

        for (int[] edge : edges[node]) {

            int to = edge[0];
            int nd = edge[1];
            
            if (nd + dist <= d[to]) {

                if ((node == g && to == h) || (node == h && to == g) || check[node]) {
                    check[to] = true;
                } else if (nd + dist != d[to]) {
                    check[to] = false;
                }
                
                d[to] = nd + dist;
                pq.add(new int[]{to, d[to]});
            }

        }
    }

    return check;

}
```
