## 백준 15684 - [사다리 조작](https://www.acmicpc.net/problem/15684)

### 풀이법

1. 모든 경우를 생성 후 사다리게임 시뮬레이션 진행.
  - 초기 메모리 초과 -> 3가지 인덱스만 가지고 재귀 진행
    - 재귀를 조금 더 고급스럽게 진행할 수도 있을듯.
  - 추가하지 않고도 답이 되는 경우가 존재.
```JAVA
static int N;
static int H;
static boolean[] INIT;

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());


    if (m == 0) {
        System.out.println(0);
        return;
    }

    INIT = new boolean[(N - 1) * H];


    for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;
        INIT[a * (N - 1) + b] = true;
        ;
    }
    
    int[] tm = new int[3];
    Arrays.fill(tm, -1);
    List<int[]> candidates = new ArrayList<>();
    for (int i = 0; i <= 3; i++) {
        createCase(candidates, tm.clone(), -1, 0, 0, i);
        for (int[] candidate : candidates) {
            if (check(candidate, i) != -1) {
                System.out.println(i);
                return;
            }
        }
        candidates.clear();
    }

    System.out.println(-1);
}

static void createCase(List<int[]> list, int[] tm, int before, int step, int count, int limit) {

    if (count == limit) {
        list.add(tm);
        return;
    }

    if (step >= INIT.length) {
        return;
    }


    if (N - 1 == 1) {
        if (!INIT[step]) {
            createCase(list, tm.clone(), before, step + 1, count, limit);
            tm[count] = step;
            createCase(list, tm, step, step + 1, count + 1, limit);
        } else {
            createCase(list, tm, before, step + 1, count, limit);
        }
    } else if (step % (N - 1) == N - 2) {
        // 가장 자리 경우.
        boolean back = INIT[step - 1];
        if (step - 1 == before) {
            back = true;
        }
        if (!INIT[step] && !back) {
            createCase(list, tm.clone(), before, step + 1, count, limit);
            tm[count] = step;
            createCase(list, tm, step, step + 1, count + 1, limit);
        } else {
            // 설치 불가능.
            createCase(list, tm, before, step + 1, count, limit);
        }
    } else if (step % (N - 1) == 0) {
        // 처음일 경우.
        if (!INIT[step] && !INIT[step + 1]) {
            createCase(list, tm.clone(), before, step + 1, count, limit);
            tm[count] = step;
            createCase(list, tm, step, step + 1, count + 1, limit);
        } else {
            // 설치 불가능.
            createCase(list, tm, before, step + 1, count, limit);
        }

    } else {
        // 중간일 경우.
        boolean back = INIT[step - 1];
        if (step - 1 == before) {
            back = true;
        }
        if (!INIT[step] && !back && !INIT[step + 1]) {
            createCase(list, tm.clone(), before, step + 1, count, limit);
            tm[count] = step;
            createCase(list, tm, step, step + 1, count + 1, limit);
        } else {
            // 설치 불가능.
            createCase(list, tm, before, step + 1, count, limit);
        }
    }


}

static int check(int[] tm, int count) {

    for (int j = 0; j < N; j++) {
        int current = j;
        for (int i = 0; i < H; i++) {

            boolean right = false;
            boolean left = false;

            if (current == N - 1) {
                right = false;
                int lid = i * (N - 1) + current - 1;
                if (tm[0] == lid || tm[1] == lid || tm[2] == lid) {
                    left = true;
                } else {
                    left = INIT[lid];
                }
            } else if (current == 0) {
                int rid = i * (N - 1) + current;
                if (tm[0] == rid || tm[1] == rid || tm[2] == rid) {
                    right = true;
                } else {
                    right = INIT[rid];
                }
                left = false;
            } else {
                int rid = i * (N - 1) + current;
                if (tm[0] == rid || tm[1] == rid || tm[2] == rid) {
                    right = true;
                } else {
                    right = INIT[rid];
                }

                int lid = i * (N - 1) + current - 1;
                if (tm[0] == lid || tm[1] == lid || tm[2] == lid) {
                    left = true;
                } else {
                    left = INIT[lid];
                }
            }

            if (right) {
                current++;
            } else if (left) {
                current--;
            }
        }

        if (current != j) {
            return -1;
        }

    }
    return count;
}
```
