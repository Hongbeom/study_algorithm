## 백준 1018 - [체스판 다시 칠하기](https://www.acmicpc.net/problem/1018)

### 풀이법

1. 8 * 8 의 2가지 경우를 모두 탐색

~~~JAVA
static int check(int sh, int sw) {
    int c1 = 0;
    int c2 = 0;
    for (int i = 0; i < 8; i++) {
        int h = i + sh;
        boolean f;
        if (i % 2 == 0) {
            f = board[sh][sw];
        } else {
            f = !board[sh][sw];
        }
        for (int j = 0; j < 8; j++) {
            int w = j + sw;
            if (j % 2 == 0) {
                if (board[h][w] != f) {
                    c1++;
                } else {
                    c2++;
                }
            } else {
                if (board[h][w] == f) {
                    c1++;
                } else {
                    c2++;
                }
            }
        }
    }
    return Math.min(c1, c2);
}
~~~
