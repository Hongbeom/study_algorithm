## 백준 1865 - [웜홀](https://www.acmicpc.net/problem/1865)

음의 간선의 최단거리 -> 벨만포드 알고리

### 풀이법

1. 간선의 정보를 리스트로 저장 후 벨만포드 알고리즘을 각 노드에서 실행

~~~JAVA
static int Bellman(List<Edge> edges, int start, int n) {
    int[] distance = new int[n];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[start] = 0;

    for (int i = 0; i < n; i++) {
        boolean check = true;

        for (Edge edge : edges) {
            // relaxation
            int from = edge.from;
            int to = edge.to;

            if (distance[from] != Integer.MAX_VALUE
                    && distance[from] + edge.weight < distance[to]) {
                distance[to] = distance[from] + edge.weight;
                check = false;
            }
        }

        if (check) {
            break;
        }
    }


    return distance[start];

}
~~~
