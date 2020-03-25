## 백준 9252 - [LCS 2](https://www.acmicpc.net/problem/9252)

### 풀이법

1. LCS Dynamic Programming. -> 이차원 메모 배열 이용
    - 그 자리의 수가 같다면, 왼쪽 위의 값 + 1
    - 다르다면 max(왼쪽값, 위의값)
2. 재귀함수를 이용해 메모배열에서의 문자열 탐색

~~~JAVA
for (int i = 1; i < memo.length; i++) {
    for (int j = 1; j < memo[0].length; j++) {
        if (A[i - 1] == B[j - 1]) {
            memo[i][j] = memo[i - 1][j - 1] + 1;
            if (memo[i][j] > length) {
                length = memo[i][j];
                h = i;
                w = j;
            }
        } else {
            memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
        }

    }
}
~~~

~~~JAVA
static void search(int[][] memo, int h, int w, int target) {
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            if (memo[i][j] == target) {
                ANS.append(A[i - 1]);
                if (target - 1 != 0) {
                    search(memo, i, j, target - 1);
                }
                return;
            }
        }
    }
}
~~~

