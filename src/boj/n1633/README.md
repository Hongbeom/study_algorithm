## 백준 1633 - [최고의 팀 만들기](https://www.acmicpc.net/problem/1633)

3차원 배열을 이용한 0/1 knapsack

### 풀이법

1. 한 플레이어당 경우는 3가지
- 백으로 선택
- 흑으로 선택
- 선택하지 않음

2. dp[i][w][b] 배열
- i: 플레이어의 수
- w: 백으로 선택한 플레이어 수
- b: 흑으로 선택한 플레이어 수

3. dp[i][w][b] = Max(dp[i-1][w][b], dp[i-1][w - 1][b] + white(i), dp[i-1][w][b-1] + black(i))
- w = 0, b = 0 일때 고려를 해주어야함.


~~~JAVA
int[][][] dp = new int[white.size() + 1][16][16];


for (int i = 1; i <= white.size(); i++) {
    for (int w = 0; w < 16; w++) {
        for (int b = 0; b < 16; b++) {
            if (w == 0 && b == 0) {
                dp[i][w][b] = 0;
            } else if (w == 0) {
                dp[i][w][b] = Math.max(dp[i - 1][w][b], dp[i - 1][w][b - 1] + black.get(i - 1));
            } else if (b == 0) {
                dp[i][w][b] = Math.max(dp[i - 1][w][b], dp[i - 1][w - 1][b] + white.get(i - 1));
            }else{
                dp[i][w][b] = max(dp[i - 1][w][b], dp[i - 1][w - 1][b] + white.get(i - 1), dp[i - 1][w][b - 1] + black.get(i - 1));
            }
        }
    }
}
~~~