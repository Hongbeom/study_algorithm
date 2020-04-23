## 백준 7569 - [토마토](https://www.acmicpc.net/problem/7569)

### 풀이법 

1. 간단한 3차원에서의 bfs.
```JAVA
static final int[] H = new int[]{-1, 1, 0, 0};
static final int[] W = new int[]{0, 0, -1, 1};

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int m = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    int h = Integer.parseInt(st.nextToken());
    int[][][] board = new int[h][n][m];
    boolean[][][] ripen = new boolean[h][n][m];
    Queue<int[]> queue = new LinkedList<>();
    int cnt = 0;
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < n; j++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < m; k++) {
                board[i][j][k] = Integer.parseInt(st.nextToken());
                if (board[i][j][k] == 0) {
                    cnt++;
                } else if (board[i][j][k] == 1) {
                    ripen[i][j][k] = true;
                    queue.add(new int[]{i, j, k, 0});
                }
            }
        }
    }
    int t = -1;
    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int a = current[0];
        int b = current[1];
        int c = current[2];
        int d = current[3];
        t = d;
        for (int i = 0; i < 6; i++) {
            int na;
            int nb;
            int nc;
            if (i == 4) {
                na = a + 1;
                nb = b;
                nc = c;
            } else if (i == 5) {
                na = a - 1;
                nb = b;
                nc = c;
            } else {
                na = a;
                nb = b + H[i];
                nc = c + W[i];
            }

            if (na < 0 || na >= h || nb < 0 || nc < 0 || nb >= n || nc >= m) {
                continue;
            }

            if (ripen[na][nb][nc] || board[na][nb][nc] != 0) {
                continue;
            }
            cnt--;
            ripen[na][nb][nc] = true;
            queue.add(new int[]{na, nb, nc, d + 1});
        }
    }

    if(cnt == 0){
        System.out.println(t);
    }else{
        System.out.println(-1);
    }
}
```
