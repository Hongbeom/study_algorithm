## SW Academy 4013 - [특이한 자석](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH&categoryId=AWIeV9sKkcoDFAVH&categoryType=CODE)

### 풀이법

1. 조건에 따라 자석의 index를 적절히 설정하고 시뮬레이션.

```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());

    StringTokenizer st;
    for (int tc = 1; tc <= TC; tc++) {
        int k = Integer.parseInt(br.readLine());

        int[][] wheels = new int[4][8];

        for (int i = 0; i < wheels.length; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < wheels[0].length; j++) {
                wheels[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] ids = new int[4];
        int answer = 0;

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int id = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken());

            rotate(wheels, ids, id, r);

        }

        for (int e = 0; e < 4; e++) {
            if (wheels[e][ids[e]] == 1) {
                answer += Math.pow(2, e);
            }
        }

        System.out.println("#" + tc + " " + answer);

    }


}

static void rotate(int[][] wheels, int[] ids, int id, int d) {
    int ld = d;
    int rd = d;

    int lid = id;
    int rid = id;

    int[] rotate = new int[4];
    rotate[id] = d;

    boolean leftCheck = true;
    boolean rightCheck = true;

    while (leftCheck || rightCheck) {
        lid--;
        rid++;

        if (lid < 0) {
            leftCheck = false;
        }

        if (rid >= 4) {
            rightCheck = false;
        }

        if (leftCheck) {
            if (wheels[lid][(ids[lid] + 2) % 8] != wheels[lid + 1][(ids[lid + 1] + 6) % 8]) {
                if (ld == 1) {
                    rotate[lid] = -1;
                    ld = -1;
                } else {
                    rotate[lid] = 1;
                    ld = 1;
                }
            } else {
                leftCheck = false;
            }
        }

        if (rightCheck) {
            if (wheels[rid - 1][(ids[rid - 1] + 2) % 8] != wheels[rid][(ids[rid] + 6) % 8]) {
                if (rd == 1) {
                    rotate[rid] = -1;
                    rd = -1;
                } else {
                    rotate[rid] = 1;
                    rd = 1;
                }
            } else {
                rightCheck = false;
            }

        }
    }

    for (int i = 0; i < rotate.length; i++) {
        int r = rotate[i];
        if (r == 1) {
            ids[i] = (ids[i] + 7) % 8;
        } else if (r == -1) {
            ids[i] = (ids[i] + 1) % 8;
        }
    }
}
```