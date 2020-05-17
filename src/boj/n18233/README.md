## 백준 18233 - [러버덕을 사랑하는 모임](https://www.acmicpc.net/problem/18233)

### 풀이법

1. dfs와 재귀적인 백트래킹 사용.


```JAVA
static int[][] LIMIT;
static int N;
static int P;
static int E;

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    P = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    LIMIT = new int[N][2];

    for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        LIMIT[i][0] = Integer.parseInt(st.nextToken());
        LIMIT[i][1] = Integer.parseInt(st.nextToken());
    }

    List<Integer> list = dfs(0, 0, 0, -1);

    if (list == null) {
        System.out.println(-1);
        return;
    } else {
        boolean[] check = new boolean[N];
        for (int id : list) {
            check[id] = true;
            E -= LIMIT[id][0];
        }

        for (int i = 0; i < check.length; i++) {
            if (check[i]) {
                if (E == 0) {
                    System.out.print(LIMIT[i][0] + " ");
                } else {
                    int diff = LIMIT[i][1] - LIMIT[i][0];

                    if (E < diff) {
                        System.out.print((LIMIT[i][0] + E) + " ");
                        E = 0;
                    } else {
                        System.out.print((LIMIT[i][0] + diff) + " ");
                        E -= diff;
                    }
                }
            } else {
                System.out.print("0 ");
            }
        }
    }


}

static List<Integer> dfs(int left, int right, int step, int before) {

    List<Integer> list = null;

    if (step == P) {
        if (left <= E && E <= right) {
            list = new ArrayList<>();
        }
        return list;
    }

    for (int i = before + 1; i < N; i++) {
        list = dfs(left + LIMIT[i][0], right + LIMIT[i][1], step + 1, i);
        if (list != null) {
            list.add(i);
            break;
        }
    }
    return list;
}
```
