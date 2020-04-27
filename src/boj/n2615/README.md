## 백준 2615 - [오목](https://www.acmicpc.net/problem/2615)

### 풀이법 

1. 오목의 룰에 따라, 한점에서 4가지 방향을 체크한다.
2. 이미 그 방향으로 체크된 점은 다시 체크할 필요가 없다.

```JAVA
static int[][] GO = new int[][]{
        {-1, 0, 1, 0},
        {0, -1, 0, 1},
        {1, -1, -1, 1},
        {-1, -1, 1, 1}
};

static int[] ANS;

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[][] board = new int[19][19];
    boolean[][][] checked = new boolean[4][19][19];
    StringTokenizer st;
    for (int i = 0; i < board.length; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < board[0].length; j++) {
            board[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            if (board[i][j] == 0 ||
                    (checked[0][i][j] && checked[1][i][j] && checked[2][i][j] && checked[3][i][j])) {
                continue;
            }

            if (isWin(board, i, j, checked)) {
                System.out.println(board[i][j]);
                System.out.println(ANS[0] + " " + ANS[1]);
                return;
            }
        }
    }

    System.out.println(0);
}

static boolean isWin(int[][] board, int h, int w, boolean[][][] checked) {
    for (int i = 0; i < 4; i++) {
        if (checked[i][h][w]) {
            continue;
        }

        ANS = check(board, i, h, w, checked);
        if (ANS != null) {
            return true;
        }
    }
    return false;
}

static int[] check(int[][] board, int d, int h, int w, boolean[][][] checked) {
    int sh = h;
    int sw = w;

    int eh = h;
    int ew = w;

    boolean start = true;
    int s = 0;
    boolean end = true;
    int e = 0;
    checked[d][h][w] = true;
    while (start || end) {
        if (start) {
            sh += GO[d][0];
            sw += GO[d][1];
            if (!(sh < 0 || sw < 0 || sh >= board.length || sw >= board[0].length) && board[sh][sw] == board[h][w]) {
                s++;
                checked[d][sh][sw] = true;
            } else {
                sh -= GO[d][0];
                sw -= GO[d][1];
                start = false;
            }
        }

        if (end) {
            eh += GO[d][2];
            ew += GO[d][3];
            if (!(eh < 0 || ew < 0 || eh >= board.length || ew >= board[0].length) && board[eh][ew] == board[h][w]) {
                e++;
                checked[d][sh][sw] = true;
            } else {
                eh -= GO[d][2];
                ew -= GO[d][3];
                end = false;
            }
        }
    }

    if (s + e == 4) {
        return new int[]{sh + 1, sw + 1};
    } else {
        return null;
    }
}
```


