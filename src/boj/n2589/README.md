## 백준 2589 - [보물섬](https://www.acmicpc.net/problem/2589)

### 풀이법

1. 가로, 세로의 길이가 50 이니 bfs를 통해 L to L의 거리의 최댓값을 구한다.

~~~JAVA
static final int[] H = new int[]{-1, 1, 0, 0};
static final int[] W = new int[]{0, 0, -1, 1};
static char[][] BOARD;
static int ANS;

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    BOARD = new char[n][m];

    for (int i = 0; i < n; i++) {
        BOARD[i] = br.readLine().toCharArray();
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (BOARD[i][j] == 'W') {
                continue;
            }
            bfs(i, j);
        }
    }
    System.out.println(ANS);
}

static void bfs(int h, int w) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[BOARD.length][BOARD[0].length];
    visited[h][w] = true;
    queue.add(new int[]{h, w, 0});

    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int a = current[0];
        int b = current[1];
        int c = current[2];
        for (int i = 0; i < 4; i++) {
            int na = a + H[i];
            int nb = b + W[i];
            if (na < 0 || nb < 0 || na >= BOARD.length || nb >= BOARD[0].length) {
                continue;
            }
            if (visited[na][nb] || BOARD[na][nb] == 'W') {
                continue;
            }

            visited[na][nb] = true;
            ANS = Math.max(c + 1, ANS);
            queue.add(new int[]{na, nb, c + 1});
        }
    }
}
~~~