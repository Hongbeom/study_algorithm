## 백준 4386 - [별자리 만들기](https://www.acmicpc.net/problem/4386)

최소 스패닝 트리 -> 크루스칼 알고리즘

### 풀이법 

1. 별들간의 거리, 노드의 정보를 가지고 있는 Vertex클래스 구현


```JAVA
static class Vertex implements Comparable<Vertex> {
    int from;
    int to;
    double distance;

    Vertex(int from, int to, double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    @Override
    public int compareTo(Vertex o) {
        if (this.distance > o.distance) {
            return 1;
        } else if (this.distance < o.distance) {
            return -1;
        }

        return 0;
    }
}
```
- 거리로 대소 비교.

2. union find를 이용한 크루스칼 알고리즘 사용
- 거리가 제일 작은 간선부터 선택
- 사이클이 생기는지 판단 -> 두 노드의 루트가 같은경우 사이클
~~~JAVA
PriorityQueue<Vertex> pq = new PriorityQueue<>();
for (int i = 0; i < n - 1; i++) {
    for (int j = i + 1; j < n; j++) {
        pq.add(new Vertex(i, j, getDistance(spot[i], spot[j])));
    }
}

int[] parent = new int[n];
for (int i = 0; i < n; i++) {
    parent[i] = i;
}
double answer = 0;
int cnt = 0;
while (!pq.isEmpty()) {
    Vertex v = pq.poll();

    if (union(parent, v.from, v.to)) {
        answer += v.distance;
        cnt++;
    }
    if (cnt == n) {
        break;
    }
}
~~~
