## 백준 2151 - [거울 설치](https://www.acmicpc.net/problem/2151)

bfs 문제. 색다른 visit 체크

### 초기 잘못된 접근
1. bfs 탐색에 대해, 모든 경우에 visit을 체크해 주었다.
2. 경우가 무수히 많으므로, 당연한 시간과 메모리 초과가 발생

### 풀이법 

1. 문제는 시작점과 끝점의 경로를 구하는 문제이다.
  - 시작점과 끝점의 경로중에서, 최소의 거울의 갯수(방향을 바꾼 횟수)를 구하면 된다.
2. 시작점에서 상, 하, 좌, 우 방향으로 탐색을 우선 시작한다.
3. 바꾼 횟수를 최소로 해야한다는게 맹점이다.
  - 이미 그 점을 같은 방향으로 방문을 했고, 방문했을때의 거울의 갯수보다 현재가 크다면 더이상 방문을 할 필요가 없다.
4. visit 배열을 3차원 int형으로 만들어서 방문체크를 한다.
  - visit[a][b][c] = d 가 의미하는 것은, a, b 에서 c 만큼 방문했을때의 최소 횟수가 d라는것을 의미.
5. 모든 경우에 대해 최솟값을 찾는다.

~~~JAVA
static int[] H = new int[]{-1, 1, 0, 0};
static int[] W = new int[]{0, 0, -1, 1};

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    char[][] home = new char[n][n];

    Queue<int[]> queue = new LinkedList<>();
    int[] end = new int[2];
    for (int i = 0; i < n; i++) {
        home[i] = br.readLine().toCharArray();
        for (int j = 0; j < n; j++) {
            if (home[i][j] == '#') {
                if (queue.isEmpty()) {
                    queue = new LinkedList<>();
                    for (int d = 0; d < 4; d++) {
                        queue.add(new int[]{i, j, d, 0});
                    }
                } else {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
    }
    System.out.println(bfs(home, queue, end));
}

static int bfs(char[][] home, Queue<int[]> queue, int[] end) {

    int[][][] visited = new int[home.length][home[0].length][4];
    for (int[][] ints : visited) {
        for (int j = 0; j < visited[0].length; j++) {
            Arrays.fill(ints[j], Integer.MAX_VALUE);
        }
    }
    int mirror = Integer.MAX_VALUE;
    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int a = current[0];
        int b = current[1];
        int d = current[2];
        int m = current[3];

        int na = a + H[d];
        int nb = b + W[d];

        if (na < 0 || nb < 0 || na >= home.length || nb >= home[0].length) {
            continue;
        }

        if (na == end[0] && nb == end[1]) {
            mirror = Math.min(mirror, m);
            continue;
        }

        if (home[na][nb] == '*' || visited[na][nb][d] < m) {
            continue;
        }

        visited[na][nb][d] = m;
        queue.add(new int[]{na, nb, d, m});
        if (home[na][nb] == '!') {
            queue.add(new int[]{na, nb, convert(d, true), m + 1});
            queue.add(new int[]{na, nb, convert(d, false), m + 1});
        }

    }
    return mirror;
}

static int convert(int d, boolean md) {
    int nd = 0;
    switch (d) {
        case 0:
            nd = md ? 2 : 3;
            break;
        case 1:
            nd = md ? 3 : 2;
            break;
        case 2:
            nd = md ? 0 : 1;
            break;
        case 3:
            nd = md ? 1 : 0;
            break;
    }
    return nd;
}
~~~
