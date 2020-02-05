## 백준 1005 - [ACM Craft](https://www.acmicpc.net/problem/1005)

### 풀이법

1. 위상정렬를 이용해 건물 짓는 시간을 표시해 준다.
```JAVA
Queue<Integer> queue = new LinkedList<>();
for (int i = 0; i < weight.length; i++) {
    if (weight[i] == 0) {
        queue.add(i);
    }
}

long[] complete = new long[n];

for (int i = 0; i < n; i++) {

    if (queue.isEmpty()) {
        break;
    }

    int node = queue.poll();
    complete[node] = complete[node] + time[node];
    if (node == w) {
        System.out.println(complete[node]);
        break;
    }

    if (graph[node] != null) {
        for (int next : graph[node]) {
            complete[next] = Math.max(complete[next], complete[node]);
            if (--weight[next] == 0) {
                queue.add(next);
            }
        }
    }
}
```