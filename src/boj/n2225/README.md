## 백준 2225 - [합분해](https://www.acmicpc.net/problem/2225)

### 풀이법

1. 크기가 k+1, n+1인 2차원 memo 배열 이용 -> memo[k][n] 에는 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수
2. k=0인 경우와 n=0인 경우는 모두 0, k=1 인 경우는 자기 자신밖에 없으므로 모두 1
3. k > 1 이고 n > 0 일때, k-1 행에서 n보다 작은 모든 수를 더해주면 memo[k][n]을 구할수 있다
- 1, 2, 3, ... n 으로 k-1 인 경우를 알고 있으므로, 모두 더하기만 하면 k일때의 경우의 수!
4. 결국 memo[k][n] = memo[k-1][n] + memo[k][n-1] 

```JAVA
int[][] memo = new int[k + 1][n + 1];

for (int i = 1; i < memo.length; i++) {
    for (int j = 0; j < memo[0].length; j++) {

        if (i == 1 || j == 0) {
            memo[i][j] = 1;
        } else {
            memo[i][j] = (memo[i - 1][j] + memo[i][j - 1]) % 1000000000;
        }
    }
}
```