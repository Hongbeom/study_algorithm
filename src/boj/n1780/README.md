## 백준 1780 - [종이의 갯수](https://www.acmicpc.net/problem/1780)

아주 간단한 분할정복 문제
### 풀이법

1. 초기값을 저장한후 배열을 탐색.
2. 초기값과 다른값이 있다면 시작점과 탐색 크기를 달리하여 9개로 분할후 1번 실행.

```JAVA
static void count(int[][] paper, int h, int w, int n) {
    int init = paper[h][w];

    for (int i = h; i < h + n; i++) {

        for (int j = w; j < w + n; j++) {

            if (init != paper[i][j]) {
                n /= 3;
                count(paper, h, w, n);
                count(paper, h, w + n, n);
                count(paper, h, w + 2 * n, n);
                count(paper, h + n, w, n);
                count(paper, h + n, w + n, n);
                count(paper, h + n, w + 2 * n, n);
                count(paper, h + 2 * n, w, n);
                count(paper, h + 2 * n, w + n, n);
                count(paper, h + 2 * n, w + 2 * n, n);
                return;
            }

        }
    }

    if (init == 1) {
        PP++;
    } else if (init == 0) {
        P0++;
    } else {
        PN++;
    }

}
```
