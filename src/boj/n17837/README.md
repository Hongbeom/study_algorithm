## 백준 17837 - [새로운 게임 2](https://www.acmicpc.net/problem/17837)

### 풀이법

1. 조건에 맞게 시뮬레이션.
2. 자신의 리스트의 id를 알고 있는 객체를 이용.

~~~JAVA
static final int[] H = new int[]{0, 0, -1, 1};
static final int[] W = new int[]{1, -1, 0, 0};

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int[][] map = new int[n][n];
    List<Horse>[][] horseMap = new ArrayList[n][n];

    for (int i = 0; i < map.length; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < map[0].length; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
            horseMap[i][j] = new ArrayList<>();
        }
    }

    Horse[] horses = new Horse[k];
    for (int i = 0; i < k; i++) {
        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken()) - 1;
        int w = Integer.parseInt(st.nextToken()) - 1;
        int d = Integer.parseInt(st.nextToken()) - 1;

        Horse horse = new Horse(i, h, w, d);
        horses[i] = horse;
        horseMap[h][w].add(horse);
    }

    int answer = -1;
    int turn = 0;

    root:
    while (turn < 1000) {


        for (int hh = 0; hh < horses.length; hh++) {
            Horse moveHorse = horses[hh];
            int a = moveHorse.h;
            int b = moveHorse.w;

            int na = a + H[moveHorse.d];
            int nb = b + W[moveHorse.d];


            if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length || map[na][nb] == 2) {
                // 파랑 또는 벽
                moveHorse.check++;
                if (moveHorse.check < 2) {
                    hh--;
                    moveHorse.convert();
                    continue;
                } else {
                    moveHorse.check = 0;
                    continue;
                }
            }

            moveHorse.check = 0;

            List<Horse> from = horseMap[a][b];
            List<Horse> to = horseMap[na][nb];
            int fromSize = from.size();
            int toSize = to.size();
            int startId = moveHorse.id;

            if (map[na][nb] == 0) {
                // 흰색
                for (int i = startId; i < horseMap[a][b].size(); i++) {
                    Horse move = from.get(i);
                    move.h = na;
                    move.w = nb;
                    move.id = toSize++;
                    to.add(move);
                }

            } else {
                // 빨간색
                for (int i = fromSize - 1; i >= startId; i--) {
                    Horse move = from.get(i);
                    move.h = na;
                    move.w = nb;
                    move.id = toSize++;
                    to.add(move);
                }

            }

            for (int i = 0; i < fromSize - startId; i++) {
                if (from.isEmpty()) {
                    break;
                }
                from.remove(from.size() - 1);
            }

            if (to.size() >= 4) {
                answer = turn + 1;
                break root;
            }


        }

        turn++;
    }

    System.out.println(answer);


}

static class Horse {
    int n;

    int h;
    int w;
    int d;
    int id;
    int check;


    Horse(int n, int h, int w, int d) {
        this.n = n;
        this.h = h;
        this.w = w;
        this.d = d;
        this.id = 0;
        this.check = 0;
    }

    void convert() {
        switch (this.d) {
            case 0:
                this.d = 1;
                break;
            case 1:
                this.d = 0;
                break;
            case 2:
                this.d = 3;
                break;
            case 3:
                this.d = 2;
                break;
        }
    }

}
~~~