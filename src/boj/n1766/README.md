## 백준 1766 - [문제집](https://www.acmicpc.net/problem/1766)

위상정렬 + 우선순


### 풀이법

1. 우선순위는 현재 풀수있는 문제(weigth 가 0) 중에서도 문제 번호가 가장 적은것
2. priority queue에다가 문제 번호를 넣어서 해결
~~~JAVA
PriorityQueue<Integer> pq = new PriorityQueue<>();

for (int i = 0; i < weight.length; i++) {
    if (weight[i] == 0) {
        pq.add(i);
    }
}

while (!pq.isEmpty()) {
    int current = pq.poll();
    System.out.print((current + 1) + " ");
    for (int to : vertex[current]) {
        if (--weight[to] == 0) {
            pq.add(to);
        }
    }
}
~~~
