## 백준 3050 - [집들이](https://www.acmicpc.net/problem/3050)

### 풀이법

1. 인풋받은 상황에서 최대 직사각형의 둘레 - 1 이 정답.
2. 전처리 배열 blocks[i][j]를 만든다.
- blocks[i][j]는 row i에서 0 부터 j까지 X의 갯수.
~~~JAVA
for (int i = 0; i < r; i++) {
    apart[i] = br.readLine().toCharArray();
    for (int j = 0; j < c; j++) {
        if (apart[i][j] == 'X') {
            blocks[i][j] = 1;
        } else {
            blocks[i][j] = 0;
        }

        if (j != 0) {
            blocks[i][j] += blocks[i][j - 1];
        }
    }

}
~~~

3. 배열을 i, j 가 증가하는 순서대로 탐색한다.
- '.'을 만났을때, 그 오른쪽, 아래쪽으로의 최대 직사각형의 둘레를 계속 max로 갱신시켜 준다.
- 최대 width는 blocks를 이용해 blocks[i][c1] - blocks[i][c2] 가 0인 제일큰 c1 (c1 - c2 + 1)
~~~JAVA
static int check(int[][] blocks, char[][] apart, int h, int w) {

    int re = 3;
    int width = 1;
    int height = 1;
    for (int c = blocks[0].length - 1; c >= w; c--) {
        if (blocks[h][c] - blocks[h][w] == 0) {
            width = c - w + 1;
            break;
        }
    }
    re = Math.max(re, 2 * width + 2 * height - 1);
    for (int r = h + 1; r < blocks.length; r++) {
        if (apart[r][w] == 'X') {
            break;
        }

        for (int c = blocks[0].length - 1; c >= w; c--) {
            if (blocks[r][c] - blocks[r][w] == 0) {
                width = Math.min(width, c - w + 1);
                re = Math.max((r - h + 1) * 2 + 2 * width - 1, re);
                break;
            }
        }
    }

    return re;


}
~~~