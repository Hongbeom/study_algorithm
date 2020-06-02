## 백준 2174 - [로봇 시뮬레이션](https://www.acmicpc.net/problem/2174)

### 풀이법 

1. 주어진 조건에 따라 시뮬레이션

~~~JAVA
static int A;
static int B;

static final int[] H = new int[]{-1, 0, 1, 0};
static final int[] W = new int[]{0, -1, 0, 1};

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    A = Integer.parseInt(st.nextToken());
    B = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[][] map = new int[B][A];
    int[][] robots = new int[n + 1][3];

    for (int i = 1; i <= n; i++) {
        st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken()) - 1;
        int h = B - Integer.parseInt(st.nextToken());

        robots[i][0] = h;
        robots[i][1] = w;
        map[h][w] = i;

        String d = st.nextToken();
        if (d.equals("N")) {
            robots[i][2] = 0;
        } else if (d.equals("W")) {
            robots[i][2] = 1;
        } else if (d.equals("S")) {
            robots[i][2] = 2;
        } else {
            robots[i][2] = 3;
        }

    }

    for (int i = 0; i < m; i++) {

        st = new StringTokenizer(br.readLine());

        int rid = Integer.parseInt(st.nextToken());
        String s = st.nextToken();
        int iteration = Integer.parseInt(st.nextToken());

        for (int q = 0; q < iteration; q++) {
            if (s.equals("L")) {
                robots[rid][2] = (robots[rid][2] + 1) % 4;
            } else if (s.equals("R")) {
                robots[rid][2] = (robots[rid][2] + 3) % 4;
            } else {
                int a = robots[rid][0];
                int b = robots[rid][1];
                int d = robots[rid][2];

                int na = a + H[d];
                int nb = b + W[d];

                if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                    crashWall(rid);
                    return;
                }

                if (map[na][nb] != 0) {
                    crashRobot(rid, map[na][nb]);
                    return;
                }

                map[na][nb] = rid;
                map[a][b] = 0;

                robots[rid][0] = na;
                robots[rid][1] = nb;
            }
        }

    }

    System.out.println("OK");

}

static void crashWall(int x) {
    System.out.println("Robot " + x + " crashes into the wall");
}

static void crashRobot(int x, int y) {
    System.out.println("Robot " + x + " crashes into robot " + y);
}
~~~