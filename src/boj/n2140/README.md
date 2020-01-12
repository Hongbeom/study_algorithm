## 백준 2140 - [지뢰찾기](https://www.acmicpc.net/problem/2140)

### 풀이법 

1. 지뢰의 최대값이니 테두리의 지뢰만 계산해 주고 n > 4일때 (n-4) ^ 2 개만큼 더 존재.


```JAVA
for (int j = 1; j < n - 2; j++) {
    int current = 0;
    if (j == 1) {
        if (board[0][0] == 1) {
            board[1][1] = 1;
            answer++;
            current++;
        }
        if (board[0][j] - current > 0) {
            board[1][j + 1] = 1;
            answer++;
        }
    } else {

        if (board[1][j - 1] == 1) {
            current++;
        }

        if (board[1][j] == 1) {
            current++;
        }

        if (board[0][j] - current > 0) {
            board[1][j + 1] = 1;
            answer++;
        }
    }
}

for (int j = 1; j < n - 2; j++) {
    int current = 0;
    if (j == 1) {

        if (board[n - 1][0] == 1) {
            board[n - 2][1] = 1;
            answer++;
            current++;
        }

        if (board[n - 1][j] - current > 0) {
            board[n - 2][j + 1] = 1;
            answer++;
        }

    } else {

        if (board[n - 2][j - 1] == 1) {
            current++;
        }

        if (board[n - 2][j] == 1) {
            current++;
        }

        if (board[n - 1][j] - current > 0) {
            board[n - 2][j + 1] = 1;
            answer++;
        }
    }
}

for (int i = 1; i < n - 2; i++) {
    int current = 0;
    if (i == 1) {
        if (board[0][0] == 1) {
            board[1][1] = 1;
            current++;
        }

        if (board[i][0] - current > 0) {
            board[i + 1][1] = 1;
            answer++;
        }

    } else {

        if (board[i - 1][1] == 1) {
            current++;
        }

        if (board[i][1] == 1) {
            current++;
        }

        if (board[i][0] - current > 0) {
            board[i + 1][1] = 1;

            if (i + 1 != n - 2) {
                answer++;
            }
        }
    }
}

for (int i = 1; i < n - 2; i++) {
    int current = 0;
    if (i == 1) {
        if (board[0][n - 1] == 1) {
            board[1][n - 2] = 1;
            current++;
        }

        if (board[i][n - 1] - current > 0) {
            board[i + 1][n - 2] = 1;
            answer++;
        }

    } else {

        if (board[i - 1][n - 2] == 1) {
            current++;
        }

        if (board[i][n - 2] == 1) {
            current++;
        }

        if (board[i][n - 1] - current > 0) {
            board[i + 1][n - 2] = 1;
            if (i + 1 != n - 2) {
                answer++;
            }
        }
    }
}
if(n == 3 && board[0][0] == 1){
    answer = 1;
}

if (n <= 4) {
    System.out.println(answer);
} else {
    System.out.println((int) (answer + Math.pow(n - 4, 2)));
}
```
