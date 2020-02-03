## 백준 13547 - [수열과 쿼리 5](https://www.acmicpc.net/problem/13547)

Mo's algorithm 으로 쿼리를 정렬해 시간복잡도를 줄인다.


### 풀이법

1. 구간에서 원소의 종류의 갯수를 알기 위해서 쿼리를 먼저 정렬한다.
- 정렬 기준은 (시작 / N^0.5) 오름차순으로, 같다면 끝의 오름차순으로 정렬.
- 이 개념은 sqrt decomposition을 사용한 개념으로 순열을 sqrt(n)의 크기로 묶어서 생각한다.
- 정렬된 순서대로 쿼리를 처리한다.

```JAVA
queries.sort((o1, o2) -> {
    int size = (int) Math.sqrt(n);
    if (o1[0] / size > o2[0] / size) {
        return 1;
    } else if (o1[0] / size < o2[0] / size) {
        return -1;
    } else {
        if (o1[1] > o2[1]) {
            return 1;
        } else if (o1[1] < o2[1]) {
            return -1;
        }
    }
    return 0;
});

add(0);
int preL = 0;
int preR = 0;
for (int[] query : queries) {
    int l = query[0];
    int r = query[1];

    // l1 > l2 인 경우, 구간이 늘어났으므로 노드 추가
    for (int i = preL - 1; i >= l; i--) add(i);

    // r1 < r2 인 경우, 구간이 늘어났으므로 노드 추가
    for (int i = preR + 1; i <= r; i++) add(i);

    // l1 < l2 인 경우, 구간이 줄었으므로 노드 삭제
    for (int i = preL; i < l; i++) erase(i);

    for (int i = preR; i > r; i--) erase(i);

    preL = l;
    preR = r;
    answer[query[2]] = ANS;
}

for (int a : answer) {
    System.out.println(a);
}
```

~~~JAVA
static void add(int id) {
        if (CNT[IN[id]]++ == 0) {
            ANS++;
        }
    }

static void erase(int id) {
    if (--CNT[IN[id]] == 0) {
        ANS--;
    }
}
~~~
