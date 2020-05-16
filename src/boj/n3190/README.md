## 백준 3190 - [뱀](https://www.acmicpc.net/problem/3190)

### 풀이법

1. 조건에 따라 시뮬레이션.
  - 머리가 먼저 움직이고 꼬리의 움직임 유무가 결정된다는게 주의점.
  
```JAVA
static final int[] H = new int[]{-1, 1, 0, 0};
static final int[] W = new int[]{0, 0, -1, 1};

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int k = Integer.parseInt(br.readLine());
    StringTokenizer st;
    int[][] board = new int[n][n];
    board[0][0] = -3;
    for (int i = 0; i < k; i++) {
        st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken()) - 1;
        int w = Integer.parseInt(st.nextToken()) - 1;

        board[h][w] = 1;
    }

    int l = Integer.parseInt(br.readLine());

    List<int[]> rotation = new ArrayList<>();

    for (int i = 0; i < l; i++) {
        st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int r;
        if (st.nextToken().equals("L")) {
            r = 1;
        } else {
            r = 2;
        }
        rotation.add(new int[]{t, r});
    }

    int rt = rotation.get(0)[0];
    int r = rotation.get(0)[1];
    int rid = 1;

    int[] head = new int[]{0, 0, 3};
    int[] tail = new int[]{0, 0, 3};

    int time = 1;

    while (true) {

        int nhh = head[0] + H[head[2]];
        int nhw = head[1] + W[head[2]];

        int nth = tail[0] + H[tail[2]];
        int ntw = tail[1] + W[tail[2]];

        // 벽에 부딪히거나 자신의 몸에 맞는 경우
        if (nhh < 0 || nhw < 0 || nhh >= board.length || nhw >= board[0].length
                || board[nhh][nhw] < 0) {
            break;
        }

        // 사과가 아닌 경우 tail은 이동.
        if (board[nhh][nhw] != 1) {
            board[tail[0]][tail[1]] = 0;
            tail[0] = nth;
            tail[1] = ntw;
        }

        head[0] = nhh;
        head[1] = nhw;

        // 자신이 왔다고 표식을 남기자.
        board[nhh][nhw] = -3;


        // head부터 진행방향 체크
        if (time == rt) {
            board[nhh][nhw] = -1 * r;
            head[2] = convert(head[2], r);

            if (rid < rotation.size()) {
                rt = rotation.get(rid)[0];
                r = rotation.get(rid)[1];
                rid++;
            }
        }

        // tail 진행방향 체크

        if (board[tail[0]][tail[1]] < 0 && board[tail[0]][tail[1]] != -3) {
            tail[2] = convert(tail[2], board[tail[0]][tail[1]]);
            board[tail[0]][tail[1]] = -3;
        }
        time++;
    }


    System.out.println(time);
}


static int convert(int d, int r) {
    int nd = 0;
    switch (d) {
        case 0:
            if (r % 2 == 0) {
                nd = 3;
            } else {
                nd = 2;
            }
            break;

        case 1:
            if (r % 2 == 0) {
                nd = 2;
            } else {
                nd = 3;
            }
            break;

        case 2:
            if (r % 2 == 0) {
                nd = 0;
            } else {
                nd = 1;
            }
            break;

        case 3:
            if (r % 2 == 0) {
                nd = 1;
            } else {
                nd = 0;
            }
            break;
    }
    return nd;
}
```

