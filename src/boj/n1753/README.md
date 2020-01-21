## 백준 1753 - [최단 경로](https://www.acmicpc.net/problem/1753)

다익스트라 알고리즘

### 풀이법

1. 인풋으로 인접리스트 생성 후 Priority Queue를 이용해 다익스트라 알고리즘풀

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

    while(!pq.isEmpty()){
        int[] current = pq.poll();

        int node = current[0];
        int d = current[1];

        if(distance[node] < d){
            continue;
        }

        for(int[] edge : edges[node]){
            int next = edge[0];
            int nextDist = edge[1] + d;

            if(nextDist < distance[next]){
                distance[next] = nextDist;
                pq.add(new int[]{next, nextDist});
            }
        }
    }

    return distance;
}
~~~