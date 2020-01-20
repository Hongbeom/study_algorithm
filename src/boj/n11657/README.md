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