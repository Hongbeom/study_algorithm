## SW Academy 5644 - [무선 충전](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo)

### 풀이법

1. List를 담는 10 * 10 2차원 배열을 선언
2. AP를 power 기준으로 오름차순으로 정렬한 후 bfs를 통해 reachable한 지점에 인덱스 삽입.
3. 이동하며 최댓값을 구해 더해준다.

```JAVA
static final int[] H = new int[]{0, -1, 0, 1, 0};
static final int[] W = new int[]{0, 0, 1, 0, -1};

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;
    for (int tc = 1; tc <= TC; tc++) {
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] moveA = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            moveA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] moveB = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            moveB[i] = Integer.parseInt(st.nextToken());
        }

        int[][] aps = new int[A][4];
        for (int i = 0; i < A; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    aps[i][1] = Integer.parseInt(st.nextToken()) - 1;
                } else if (j == 1) {
                    aps[i][0] = Integer.parseInt(st.nextToken()) - 1;
                } else {
                    aps[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        Arrays.sort(aps, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[3] > o2[3]) {
                    return -1;
                } else if (o1[3] < o2[3]) {
                    return 1;
                }
                return 0;
            }
        });
        List<Integer>[][] map = new ArrayList[10][10];

        for (int i = 0; i < aps.length; i++) {
            mark(map, aps[i], i);
        }

        int ah = 0;
        int aw = 0;

        int bh = 9;
        int bw = 9;

        int answer = 0;

        for (int i = 0; i <= m; i++) {
            ah += H[moveA[i]];
            aw += W[moveA[i]];


            bh += H[moveB[i]];
            bw += W[moveB[i]];


            if (map[ah][aw] != null && map[bh][bw] == null) {

                answer += aps[map[ah][aw].get(0)][3];

            } else if (map[ah][aw] == null && map[bh][bw] != null) {

                answer += aps[map[bh][bw].get(0)][3];

            } else if (map[ah][aw] != null && map[bh][bw] != null) {

                if (map[ah][aw].get(0) != map[bh][bw].get(0)) {
                    answer += aps[map[ah][aw].get(0)][3];
                    answer += aps[map[bh][bw].get(0)][3];
                } else {
                    answer += aps[map[ah][aw].get(0)][3];
                    int aSecond = 0;
                    if (map[ah][aw].size() >= 2) {
                        aSecond = aps[map[ah][aw].get(1)][3];
                    }

                    int bSecond = 0;
                    if (map[bh][bw].size() >= 2) {
                        bSecond = aps[map[bh][bw].get(1)][3];
                    }
                    answer += Math.max(aSecond, bSecond);
                }
            }
        }

        System.out.println("#" + tc + " " + answer);
    }
}

static void mark(List<Integer>[][] map, int[] ap, int id) {
    int h = ap[0];
    int w = ap[1];
    int limit = ap[2];

    boolean[][] visited = new boolean[map.length][map[0].length];
    visited[h][w] = true;

    if (map[h][w] == null) {
        map[h][w] = new ArrayList<>();
    }
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{h, w, 0});
    map[h][w].add(id);

    while (!queue.isEmpty()) {
        int[] current = queue.poll();

        int a = current[0];
        int b = current[1];
        int d = current[2];

        if (d + 1 > limit) {
            break;
        }

        for (int i = 1; i <= 4; i++) {
            int na = a + H[i];
            int nb = b + W[i];

            if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                continue;
            }

            if (visited[na][nb]) {
                continue;
            }
            visited[na][nb] = true;

            if (map[na][nb] == null) {
                map[na][nb] = new ArrayList<>();
            }
            map[na][nb].add(id);
            if (d + 1 < limit) {
                queue.add(new int[]{na, nb, d + 1});
            }
        }

    }

}
```