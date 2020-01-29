## 백준 10448 - [유레카 이론](https://www.acmicpc.net/problem/10448)


### 풀이법

1. 3번 더해야 하니 bfs 이용.

~~~JAVA
boolean[] visited = new boolean[1001];
root:
for (int t = 0; t < T; t++) {
    int n = Integer.parseInt(br.readLine());

    Queue<int[]> queue = new LinkedList<>();

    queue.add(new int[]{0, 0});
    visited[0] = true;
    int cnt = 0;

    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int value = current[0];
        int step = current[1];

        if (cnt + 1 == step) {
            cnt++;
            visited = new boolean[1001];
        }

        if (step == 3) {
            if (value == n) {
                System.out.println(1);
                continue root;
            }
            continue;
        }

        int a = 1;
        int plus = 2;
        while (a < n) {
            int nv = value + a;

            if (nv > n) {
                break;
            }
            if (!visited[nv]) {
                visited[nv] = true;
                queue.add(new int[]{nv, step + 1});
            }

            a += plus;
            plus++;

        }
    }
    System.out.println(0);
}
~~~
