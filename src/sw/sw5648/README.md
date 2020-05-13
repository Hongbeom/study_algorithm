## SW Academy 5648 - [원자 소멸 시뮬레이션](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRFInKex8DFAUo)

시물레이션 문제

### 풀이법

1. 좌표를 2배 늘려주어 편하게 계산하도록 한다.
2. 각 원자에 대해 2차원 배열로 메모를 이용하여 원자 소멸을 체크한다.
 - 만약 가려는 좌표가 양수라면 그 시간에 처음 2개의 원자가 충돌.
 - 만약 가려는 좌표가 음수라면 그 시간에 2개 이상의 원자가 그 좌표에서 충돌.
 - 만약 가려는 좌표가 0 이라면 그냥 이동.
 - 만약 원자가 이동하려는데 자기 자신의 위치의 자표가 음수라면 그 원자는 소멸된 원자.

3. 주의점: 메모리가 아슬아슬하게 통과
 - 변수의 크기가 크지 않아 short 배열을 사용하여 메모리를 줄임
 - 매 테스트케이스에 큰 2차원 배열을 사용하는 것 보다, 101등과 같은 수를 이용하여 테스트케이스마다 2차원 배열을 다시 생성하지 않고 해결하는 방법도 존재.

```JAVA
static int[] H = new int[]{-1, 1, 0, 0};
static int[] W = new int[]{0, 0, -1, 1};
static int ADJ = 2000;

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;
    for (int tc = 1; tc <= TC; tc++) {
        int n = Integer.parseInt(br.readLine());
        List<Atom> atomList = new ArrayList<>();
        short[][] map = new short[4001][4001];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) * 2;
            int y = Integer.parseInt(st.nextToken()) * 2;
            int d = Integer.parseInt(st.nextToken());
            short k = Short.parseShort(st.nextToken());

            int h = ADJ - y;
            int w = ADJ + x;

            map[h][w] = k;

            atomList.add(new Atom(h, w, d, k));
        }

        int answer = 0;
        boolean[] checked = new boolean[n];
        int checkCnt = 0;

        root:
        while (true) {

            for (int i = 0; i < n; i++) {
                if (checkCnt == n) {
                    break root;
                }

                if (checked[i]) {
                    continue;
                }

                Atom current = atomList.get(i);
                int a = current.h;
                int b = current.w;

                if (map[a][b] < 0) {
                    checked[i] = true;
                    checkCnt++;
                    map[a][b] = 0;
                    continue;
                }

                map[a][b] = 0;

                // 일단 이동
                int[] nextPosition = current.move();

                int na = nextPosition[0];
                int nb = nextPosition[1];

                if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                    checked[i] = true;
                    checkCnt++;
                    continue;
                }

                if (map[na][nb] < 0) {
                    answer += current.k;
                    checked[i] = true;
                    checkCnt++;
                } else if (map[na][nb] > 0) {
                    answer += map[na][nb] + current.k;
                    checked[i] = true;
                    checkCnt++;
                    map[na][nb] = -1;
                } else {
                    map[na][nb] = current.k;
                }

            }

        }

        System.out.println("#" + tc + " " + answer);
    }
}

static class Atom {
    int h;
    int w;
    int d;
    short k;
    int step;

    Atom(int h, int w, int d, short k) {
        this.h = h;
        this.w = w;
        this.d = d;
        this.k = k;
        this.step = 0;
    }

    int[] move() {
        this.h += H[d];
        this.w += W[d];
        this.step++;

        return new int[]{this.h, this.w};
    }

}
```