## 백준 1753 - [최단 경로](https://www.acmicpc.net/problem/1753)

### 초기 잘못된 접근법
1. 혼자 착각하여 bfs로 경계를 만든 후 거기서 이분매칭을 진행햐였다...

### 풀이법

1. 체스판의 가로, 세로 기준으로 segment를 만들어 인덱스로 표현한다.
2. 그 세그먼트들 사이로 간선을 지정한다(겹쳐 있으면)
  - 겹쳐있지만 그 지점이 1인 세그먼트들은 간선로 연결해 주지 않는다.
3. 이분매칭을 이용해 최대 매칭을 찾아준다.

~~~JAVA
static int[][] BOARD;
static boolean[] VISITED;
static int[] MATCH;
static List<Integer>[] GRAPH;
~~~

~~~JAVA
BOARD = new int[m][n];

for (int i = 0; i < m; i++) {
    st = new StringTokenizer(br.readLine());
    for (int j = 0; j < n; j++) {
        BOARD[i][j] = Integer.parseInt(st.nextToken());
    }
}

int[][] idCheck = new int[m][n];
int vid = 1;
for (int j = 0; j < n; j++) {
    boolean check = false;

    for (int i = 0; i < m; i++) {
        if (BOARD[i][j] == 2) {
            if (check) {
                vid++;
            }
        } else {
            if (!check) {
                check = true;
            }
            idCheck[i][j] = vid;
            if (i == m - 1) {
                vid++;
            }
        }
    }
}
GRAPH = new ArrayList[vid];
for (int i = 0; i < vid; i++) {
    GRAPH[i] = new ArrayList<>();
}

int hid = 1;
for (int i = 0; i < m; i++) {
    boolean check = false;
    for (int j = 0; j < n; j++) {
        if (BOARD[i][j] == 2) {
            if (check) {
                hid++;
            }
        } else {
            if (!check) {
                check = true;
            }
            if (BOARD[i][j] != 1) {
                GRAPH[idCheck[i][j]].add(hid);
            }
            if (j == n - 1) {
                hid++;
            }
        }
    }
}
MATCH = new int[hid];
int ans = 0;
for (int i = 1; i < vid; i++) {
    VISITED = new boolean[hid];
    if (dfs(i)) {
        ans++;
    }
}
~~~

~~~JAVA
static boolean dfs(int from) {

    if (GRAPH[from] != null) {
        for (int to : GRAPH[from]) {
            if (VISITED[to]) {
                continue;
            }
            VISITED[to] = true;

            if (MATCH[to] == 0 || dfs(MATCH[to])) {
                MATCH[to] = from;
                return true;
            }
        }
    }
    return false;
}
~~~