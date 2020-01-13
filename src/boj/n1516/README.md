## 백준 1516 - [게임 개발](https://www.acmicpc.net/problem/1516)


### 풀이법

1. 위상정렬을 이용해 각 건물이 어느정도 시간이 되는지 저장.

```JAVA
for (int i = 0; i < n; i++) {
    int current = queue.poll();

    answer[current] += time[current];

    for (int next : edges[current]) {
        if (--weight[next] == 0) {
            queue.add(next);
        }
        answer[next] = Math.max(answer[next], answer[current]);
    }
}
```

- 현재 자신이 지어지고, 자신을 선행으로 가지고 있는 건물의 answer에 계속 max값을 갱신해준다.
