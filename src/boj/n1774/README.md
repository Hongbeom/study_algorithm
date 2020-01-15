## 백준 1774 - [우주신과의 교감](https://www.acmicpc.net/problem/1774)

### 풀이법

1. union find 이용 크루스칼 알고리즘

~~~JAVA
PriorityQueue<Edge> pq = new PriorityQueue<>();

for (int i = 0; i < n - 1; i++) {
    for (int j = i + 1; j < n; j++) {
        double dist = getDistance(spot[i], spot[j]);
        pq.add(new Edge(i, j, dist));
    }
}
int cnt = 0;
while (!pq.isEmpty()) {
    Edge current = pq.poll();

    if (check(parent, current.from, current.to)) {
        continue;
    }

    answer += current.distance;
    union(parent, current.from, current.to);
    cnt++;

    if (cnt == n - 1 - m) {

        break;
    }
}
~~~

