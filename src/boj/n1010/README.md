## 백준 1010 - [다리 놓기](https://www.acmicpc.net/problem/1010)

### 풀이법

1. i = 0 이가나 j = 0 이면 memo[i][j] = 0 
2. i = 1 이면 memo[i][j] = j 
3. memo[i][j] = memo[i-1][j-1] + memo[i][j-1] 

```JAVA
int[][] memo = new int[31][31];

for (int i = 0; i < memo.length; i++) {
    for (int j = 0; j < memo[0].length; j++) {
        if (i == 0 || j == 0) {
            memo[i][j] = 0;
        } else if(i == 1){
            memo[i][j] = j;
        }else {
            memo[i][j] = memo[i][j - 1] + memo[i - 1][j - 1];
        }
    }
}
```