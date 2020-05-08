## 백준 15483 - [최소 편집](https://www.acmicpc.net/problem/15483)

### 풀이법

1. DP를 이용하여 최소 편집 거리를 구한다.
2. 2차원 memo 배열을 이용
  - height와 width는 각각 문자열의 길이 + 1 로 설정 (맨 앞에 공백이 오기 때문)
  - 각각 공백에 대해서는 그 위치의 인덱스로 설정
  - 만약 각각의 인덱스에 대해 문자가 같으면 memo[i][j] = memo[i-1][j-1]
  - 다르다면 memo[i][j] = MIN(memo[i-1][j], memo[i-1][j-1], memo[i][j-1]) + 1

```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] change = br.readLine().toCharArray();
    char[] target = br.readLine().toCharArray();

    int[][] memo = new int[change.length + 1][target.length + 1];

    for (int i = 0; i < memo.length; i++) {
        for (int j = 0; j < memo[0].length; j++) {
            if (i == 0) {
                memo[i][j] = j;
                continue;
            }
            if (j == 0) {
                memo[i][j] = i;
                continue;
            }
            if (change[i - 1] == target[j - 1]) {
                memo[i][j] = memo[i - 1][j - 1];
            } else {
                memo[i][j] = min3(memo[i - 1][j] + 1, memo[i - 1][j - 1] + 1, memo[i][j - 1] + 1);
            }
        }
    }

    System.out.println(memo[change.length][target.length]);

}

static int min3(int a, int b, int c) {
    if (a < b) {
        return Math.min(a, c);
    } else {
        return Math.min(b, c);
    }
}
```
