## 백준 9465 - [스티커](https://www.acmicpc.net/problem/9465)

### 풀이법

1. dp 문제, 전값과 현재값을 교차로 max 하여 memo 이용.

~~~JAVA
for (int j = 0; j < n; j++) {
    if (j == 0) {
        memo[0][j] = input[0][j];
        memo[1][j] = input[1][j];
    } else {
        memo[0][j] = Math.max(memo[0][j-1], memo[1][j-1] + input[0][j]);
        memo[1][j] = Math.max(memo[1][j-1], memo[0][j-1] + input[1][j]);

        if( j == n-1){
            answer = Math.max(memo[0][j], memo[1][j]);
        }
    }
}
~~~

