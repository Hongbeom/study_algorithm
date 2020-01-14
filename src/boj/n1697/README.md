## 백준 1697 - [숨바꼭질](https://www.acmicpc.net/problem/1697)

### 풀이법

1. bfs로 3가지 경우로 탐색, 범위설정이 중요
- 0보다 크거나 같고, k+1 or n 까지 탐색.
- k+1 까지 하면 최적해를 구할 수 있다.

~~~JAVA
if (n > k) {
    LIMIT = n + 1;
} else {
    LIMIT = k + 2;
}

boolean[] visited = new boolean[LIMIT];
visited[n] = true;
while (!queue.isEmpty()) {
    int[] current = queue.poll();

    int x = current[0];
    int step = current[1];

    if (x == k) {
        System.out.println(step);
        return;
    }


    if (check(x + 1, visited)) {
        visited[x + 1] = true;
        queue.add(new int[]{x + 1, step + 1});
    }

    if (check(x - 1, visited)) {
        visited[x - 1] = true;
        queue.add(new int[]{x - 1, step + 1});
    }

    if (check(x * 2, visited)) {
        visited[x * 2] = true;
        queue.add(new int[]{x * 2, step + 1});
    }
    
}
~~~

