## 백준 1227 - [그녀의 마음](https://www.acmicpc.net/problem/1227)

BFS 응용으로 배열의 크기가 너무 클때 어떻게 대처해야 하는가...? -> Solution을 보고 해결했다.

### 초기 잘못된 접근

1. 결국 문제가 원하는건, 시작점(0, 0) 에서 시작하여 s만큼 이동했을때 장해물을 피해서 이동 가능한 칸의 갯수
- 홀수번 이동시 정인이의 칸, 짝수번 이동 시 상근이의 칸. 

2. (2 * s + 1) * (2 * s + 1) 크기의 visited 배열 사용해서 BFS 진행

```JAVA
while (!queue.isEmpty()) {
    int[] current = queue.poll();

    int x = current[0];
    int y = current[1];
    int step = current[2];

    int check = step % 2;

    if (step <= s) {
        if (check == 0) {
            sanguen++;
        } else {
            jungin++;
        }

        if (step == s) {
            continue;
        }
    }

    for (int i = 0; i < 4; i++) {
        int nx = x + H[i];
        int ny = y + W[i];

        if (visited[nx][ny]) {
            continue;
        }
        visited[nx][ny] = true;
        queue.add(new int[]{nx, ny, step + 1});
    }
}
```
- 하지만 s 가 최대 10,000,000 이므로 당연히 메모리가 초과된다...

### 풀이법

1. 솔루션을 보고 풀이했다...
- 조건에 장애물 좌표 x, y의 절대값은 1000을 넘지 않는다.
- 그러면, 2001 * 2001 크기의 배열을 이용하여 이 사각형 안의 칸을 먼저 탐색한다 - BFS 이용 가능
- 사각형의 밖의 칸을 세는법 - 사각형 안의 edge 칸에 도달했을때의 상태를 이용
    - 코너일때 : 총 s - step 칸이 존재.
    - 코너가 아닐때 : 총 (s - step) * 2 와 (s - step - 2) * (s - step - 1) / 2 개의 칸이 존재.
- 경우에 따라, 정인이와 상근이의 칸에 더해준다.

~~~JAVA
 while (!queue.isEmpty()) {
    int[] current = queue.poll();

    int x = current[0];
    int y = current[1];
    int step = current[2];

    int check = step % 2;

    if (step > 0 && step <= s) {
        if (check == 0) {
            sanguen++;
        } else {
            jungin++;
        }

        if (step == s) {
            continue;
        }
    }


    if (x == 0 || x == N - 1 || y == M - 1 || y == 0) {

        int plus = s - step;


        if ((x == N - 1 && y == 0) || (x == 0 && y == M - 1) || (x == 0 && y == 0) || (x == N - 1 && y == M - 1)) {

            if (plus % 2 == 0) {
                sanguen += (plus / 2) * 2;
                jungin += (plus / 2) * 2;
            } else {
                if (check == 0) {
                    sanguen += (plus / 2) * 2;
                    jungin += (plus / 2 + 1) * 2;
                } else {
                    sanguen += (plus / 2 + 1) * 2;
                    jungin += (plus / 2) * 2;
                }
            }

            for (int i = 1; i < plus; i++) {
                if (i % 2 == 1) {
                    sanguen += i;
                } else {
                    jungin += i;
                }
            }

        } else {

            if (plus % 2 == 0) {
                sanguen += plus / 2;
                jungin += plus / 2;
            } else {
                if (check == 0) {
                    sanguen += plus / 2;
                    jungin += plus / 2 + 1;
                } else {
                    sanguen += plus / 2 + 1;
                    jungin += plus / 2;
                }
            }


        }
    }
}
~~~
