## 백준 2073 - [수도배관공사](https://www.acmicpc.net/problem/2073)

### 풀이법

1. dp
2. memo[i][j]
  - i : 파이프의 번호
  - j : 현재 만드려는 길이
3. memo[i][j] = MAX(memo[i - 1][j], MIN(c, memo[i - 1][c]))

```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int d = Integer.parseInt(st.nextToken());
    int p = Integer.parseInt(st.nextToken());

    int[][] pipes = new int[p][2];

    for (int i = 0; i < p; i++) {
        st = new StringTokenizer(br.readLine());
        pipes[i][0] = Integer.parseInt(st.nextToken());
        pipes[i][1] = Integer.parseInt(st.nextToken());
    }

    int[][] memo = new int[p][d + 1];

    memo[0][pipes[0][0]] = pipes[0][1];

    for (int i = 1; i < p; i++) {
        int l = pipes[i][0];
        int c = pipes[i][1];

        for (int j = 1; j < memo[0].length; j++) {

            if (j - l < 0) {
                memo[i][j] = Math.max(memo[i][j], memo[i - 1][j]);
            } else if (j - l == 0) {
                memo[i][j] = Math.max(memo[i - 1][j], c);
            } else {
                memo[i][j] = Math.max(memo[i - 1][j], Math.min(c, memo[i - 1][j - l]));
            }
        }
    }

    System.out.println(memo[p - 1][d]);
}
```
