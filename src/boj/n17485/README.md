## 백준 17485 - [진우의 달 여행 (Large)](https://www.acmicpc.net/problem/17485)

### 풀이법

1. 다이나믹 프로그래밍 문제 -> 이전의 최솟값을 이용해 최소 연료랑을 계속 구해나간다.
2. 3차원 메모 배열 이용 -> memo[i][j][d]
  - i, j는 각각 위치를 의미함.
  - d는 이전 칸에서 현재 (i, j)칸으로 이동한 방법을 의미.
3. 방향(d)에 맞게 계속 구해나가준다.
  - 맨 오른쪽과 맨 왼쪽 칸은 오는 방법이 제한적이므로 고려해준다.

~~~JAVA
static int MAX = 100001;

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[][] map = new int[n][m];
    int[][][] memo = new int[n][m][3];
    int answer = MAX;
    for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < m; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
        }
    }
    for (int j = 0; j < m; j++) {
        Arrays.fill(memo[0][j], map[0][j]);
        if (j == 0) {
            memo[0][j][2] = MAX;
        }
        if (j == m - 1) {
            memo[0][j][0] = MAX;
        }
    }

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (j == 0) {
                memo[i][j][0] = Math.min(memo[i - 1][j + 1][1], memo[i - 1][j + 1][2]) + map[i][j];
                memo[i][j][2] = MAX;
            } else if (j == m - 1) {
                memo[i][j][0] = MAX;
                memo[i][j][2] = Math.min(memo[i - 1][j - 1][0], memo[i - 1][j - 1][1]) + map[i][j];
            } else {
                memo[i][j][0] = Math.min(memo[i - 1][j + 1][1], memo[i - 1][j + 1][2]) + map[i][j];
                memo[i][j][2] = Math.min(memo[i - 1][j - 1][0], memo[i - 1][j - 1][1]) + map[i][j];
            }
            memo[i][j][1] = Math.min(memo[i - 1][j][0], memo[i - 1][j][2]) + map[i][j];
            if (i == n - 1) {
                answer = Math.min(answer, memo[i][j][0]);
                answer = Math.min(answer, memo[i][j][1]);
                answer = Math.min(answer, memo[i][j][2]);
            }
        }
    }
    System.out.println(answer);
}
~~~