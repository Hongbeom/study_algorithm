## 백준 1197 - [최소 스패닝 트리](https://www.acmicpc.net/problem/1197)

최소 스패닝 트리 -> 크루스칼 알고리즘

### 풀이법 

1. 간선의 정보를 weigth 오름차순으로 정렬 후 union-find 이용 크루스칼 알고리즘 적용


```JAVA
Collections.sort(edges, (o1, o2) -> {
    if (o1[2] > o2[2]) {
        return 1;
    } else if (o1[2] < o2[2]) {
        return -1;
    }
    return 0;
});

int answer = 0;
int cnt = 0;

for (int[] edge : edges) {

    if (union(parent, edge[0], edge[1])) {
        answer += edge[2];
        cnt++;
    }

    if (cnt == v-1) {
        break;
    }
}
```
