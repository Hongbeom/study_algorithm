## 백준 17213 - [과일 서리](https://www.acmicpc.net/problem/17213)

DP 문제.

### 풀이법

1. n가지 과일중에서 m - n 개를 뽑는 경우의 수.
2. 이전 값(m - n - 1)의 경우의 수가 다음 값(m - n)에 포함된다.
3. memo[i][j] = memo[i][j - 1] + memo[i - 1][j];

~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int m = Integer.parseInt(br.readLine());

    if (n == m) {
        System.out.println(1);
        return;
    }

    int[][] memo = new int[n + 1][m - n + 1];

    for (int i = 0; i < memo.length; i++) {
        for (int j = 0; j < memo[0].length; j++) {
            if (i == 0 && j != 0) {
                memo[i][j] = 0;
            } else if (j == 0) {
                memo[i][j] = 1;
            } else {
                memo[i][j] = memo[i][j - 1] + memo[i - 1][j];
            }
        }
    }
    System.out.println(memo[n][m - n]);

}
~~~