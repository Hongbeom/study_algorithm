## 백준 3055 - [탈출](https://www.acmicpc.net/problem/3055)

### 풀이법

1. 고슴도치는 물이 찰 예정인곳으로 이동할 수 없으므로, 물을 먼저 이동히켜준다.
- int 배열을 이용해 bfs, 물일경우 마지막 원소는 -1, 고슴도치의 위치일경우 마지막 원소는 걸린 시간.
- queue에 물부터 채우고 마지막에 고슴도치의 위치를 넣어줌.

```JAVA
char[][] map = new char[n][m];
LinkedList<int[]> queue = new LinkedList<>();
for (int i = 0; i < n; i++) {
    int cnt = 0;
    for (char c : br.readLine().toCharArray()) {
        map[i][cnt] = c;
        if (c == '*') {
            queue.add(new int[]{i, cnt, -1});
        } else if (c == 'S') {
            dochi = new int[]{i, cnt, 0};
        }
        cnt++;
    }
}
queue.add(dochi);
```
2. 물을 이동시킬때에는 map의 원소를 바꾸어 주고, 고슴도치는 이동 가능한 위치를 queue에 넣어준다.
- 물이 이동 가능한 경우는 그 자리가 S 이거나 . 일때.
- 고슴도치가 이동 가능한 경우는 D 이거나 ., 그리고 visited 배열을 이용해 같은 자리를 가지 않는다.
~~~JAVA
root:
while (!queue.isEmpty()) {
    int[] current = queue.poll();

    int a = current[0];
    int b = current[1];
    int check = current[2];

    for (int i = 0; i < 4; i++) {
        int na = a + H[i];
        int nb = b + W[i];

        if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
            continue;
        }

        if (check == -1) {
            if (map[na][nb] == '.' || map[na][nb] == 'S') {
                map[na][nb] = '*';
                queue.add(new int[]{na, nb, -1});
            }
        } else {

            if (visited[na][nb]) {
                continue;
            }

            if (map[na][nb] == '.') {
                visited[na][nb] = true;
                queue.add(new int[]{na, nb, check + 1});
            } else if (map[na][nb] == 'D') {
                answer = check + 1;
                break root;
            }

        }
    }
}
~~~
