## 백준 5373 - [큐빙](https://www.acmicpc.net/problem/5373)

### 풀이법 

1. 조건에 따라 시뮬레이션.
```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;
    for (int tc = 0; tc < TC; tc++) {
        int[][][] cube = new int[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = i;
                }
            }
        }

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            char[] rotation = st.nextToken().toCharArray();

            char f = rotation[0];
            if (rotation[1] == '-') {
                rotation(cube, f);
                rotation(cube, f);
                rotation(cube, f);
            } else {
                rotation(cube, f);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(getChar(cube[0][i][j]));
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}

static void rotation(int[][][] cube, char f) {
    int id = getId(f);

    int[][] tmp = new int[3][3];
    tmp[0][0] = cube[id][2][0];
    tmp[0][1] = cube[id][1][0];
    tmp[0][2] = cube[id][0][0];
    tmp[1][0] = cube[id][2][1];
    tmp[1][1] = cube[id][1][1];
    tmp[1][2] = cube[id][0][1];
    tmp[2][0] = cube[id][2][2];
    tmp[2][1] = cube[id][1][2];
    tmp[2][2] = cube[id][0][2];
    cube[id] = tmp;

    switch (id) {
        case 0:
            U(cube);
            break;
        case 1:
            F(cube);
            break;
        case 2:
            D(cube);
            break;
        case 3:
            L(cube);
            break;
        case 4:
            R(cube);
            break;
        default:
            B(cube);
            break;
    }
}

static void U(int[][][] cube) {
    int[] edge = new int[12];
    int id = 0;
    int sid = 3;
    int tid = 6;
    int fid = 9;

    for(int i = 0; i < 3; i ++){
        edge[id++] = cube[3][0][2 - i];
        edge[sid++] = cube[5][0][2 - i];
        edge[tid++] = cube[4][0][2 - i];
        edge[fid++] = cube[1][0][2 - i];
    }

    id = 0;
    sid = 3;
    tid = 6;
    fid = 9;
    for(int i = 0; i < 3; i ++){
        cube[5][0][2 - i] = edge[id++];
        cube[4][0][2 - i] = edge[sid++];
        cube[1][0][2 - i] = edge[tid++];
        cube[3][0][2 - i] = edge[fid++];
    }

}
static void F(int[][][] cube) {
    int[] edge = new int[12];
    int id = 0;
    int sid = 3;
    int tid = 6;
    int fid = 9;

    for(int i = 0; i < 3; i ++){
        edge[id++] = cube[3][2 - i][2];
        edge[sid++] = cube[0][2][i];
        edge[tid++] = cube[4][i][0];
        edge[fid++] = cube[2][0][2 - i];
    }

    id = 0;
    sid = 3;
    tid = 6;
    fid = 9;
    for(int i = 0; i < 3; i ++){
        cube[0][2][i] = edge[id++];
        cube[4][i][0] = edge[sid++];
        cube[2][0][2 - i] = edge[tid++];
        cube[3][2 - i][2] = edge[fid++];
    }
}
static void D(int[][][] cube) {
    int[] edge = new int[12];
    int id = 0;
    int sid = 3;
    int tid = 6;
    int fid = 9;

    for(int i = 0; i < 3; i ++){
        edge[id++] = cube[3][2][i];
        edge[sid++] = cube[1][2][i];
        edge[tid++] = cube[4][2][i];
        edge[fid++] = cube[5][2][i];
    }

    id = 0;
    sid = 3;
    tid = 6;
    fid = 9;
    for(int i = 0; i < 3; i ++){
        cube[1][2][i] = edge[id++];
        cube[4][2][i] = edge[sid++];
        cube[5][2][i] = edge[tid++];
        cube[3][2][i] = edge[fid++];
    }
}
static void L(int[][][] cube) {
    int[] edge = new int[12];
    int id = 0;
    int sid = 3;
    int tid = 6;
    int fid = 9;

    for(int i = 0; i < 3; i ++){
        edge[id++] = cube[5][2 - i][2];
        edge[sid++] = cube[0][i][0];
        edge[tid++] = cube[1][i][0];
        edge[fid++] = cube[2][i][0];
    }

    id = 0;
    sid = 3;
    tid = 6;
    fid = 9;
    for(int i = 0; i < 3; i ++){
        cube[0][i][0] = edge[id++];
        cube[1][i][0] = edge[sid++];
        cube[2][i][0] = edge[tid++];
        cube[5][2 - i][2] = edge[fid++];
    }

}
static void R(int[][][] cube) {
    int[] edge = new int[12];
    int id = 0;
    int sid = 3;
    int tid = 6;
    int fid = 9;

    for(int i = 0; i < 3; i ++){
        edge[id++] = cube[1][2 - i][2];
        edge[sid++] = cube[0][2 - i][2];
        edge[tid++] = cube[5][i][0];
        edge[fid++] = cube[2][2 - i][2];
    }

    id = 0;
    sid = 3;
    tid = 6;
    fid = 9;
    for(int i = 0; i < 3; i ++){
        cube[0][2 - i][2] = edge[id++];
        cube[5][i][0] = edge[sid++];
        cube[2][2 - i][2] = edge[tid++];
        cube[1][2 - i][2] = edge[fid++];
    }
}
static void B(int[][][] cube) {
    int[] edge = new int[12];
    int id = 0;
    int sid = 3;
    int tid = 6;
    int fid = 9;

    for(int i = 0; i < 3; i ++){
        edge[id++] = cube[4][2 - i][2];
        edge[sid++] = cube[0][0][2 - i];
        edge[tid++] = cube[3][i][0];
        edge[fid++] = cube[2][2][i];
    }

    id = 0;
    sid = 3;
    tid = 6;
    fid = 9;
    for(int i = 0; i < 3; i ++){
        cube[0][0][2 - i] = edge[id++];
        cube[3][i][0] = edge[sid++];
        cube[2][2][i] = edge[tid++];
        cube[4][2 - i][2] = edge[fid++];
    }
}

static char getChar(int i) {
    switch (i) {
        case 0:
            return 'w';
        case 1:
            return 'r';
        case 2:
            return 'y';
        case 3:
            return 'g';
        case 4:
            return 'b';
        default:
            return 'o';

    }
}

static int getId(char f) {

    switch (f) {
        case 'U':
            return 0;
        case 'F':
            return 1;
        case 'D':
            return 2;
        case 'L':
            return 3;
        case 'R':
            return 4;
        default:
            return 5;
    }
}
```
