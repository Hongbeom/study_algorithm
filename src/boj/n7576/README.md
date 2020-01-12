## 백준 7576 - [토마토](https://www.acmicpc.net/problem/7576)
 
 
 ### 풀이법
 
 1. visited 배열을 이용, 토마토 수-- 해주면서 bfs.
 
 ```JAVA
while (!queue.isEmpty()) {
    int[] current = queue.poll();

    int a = current[0];
    int b = current[1];
    int step = current[2];

    for (int i = 0; i < 4; i++) {
        int na = a + H[i];
        int nb = b + W[i];

        if (na < 0 || nb < 0 || na >= box.length || nb >= box[0].length) {
            continue;
        }

        if (visited[na][nb]) {
            continue;
        }

        visited[na][nb] = true;
        cnt--;

        if (cnt == 0) {
            System.out.println(step + 1);
            return;
        }

        queue.add(new int[]{na, nb, step + 1});
    }
}
```