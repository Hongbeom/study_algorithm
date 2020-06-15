## 백준 1963 - [소수 경로](https://www.acmicpc.net/problem/1963)


### 풀이법

1. 소수 판별 로직을 이용 BFS.
~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;
    for (int tc = 0; tc < TC; tc++) {
        st = new StringTokenizer(br.readLine());

        int from = 0;
        int f = 3;
        for (char c : st.nextToken().toCharArray()) {
            from += Math.pow(10, f--) * (c - '0');
        }

        int target = 0;
        int t = 3;
        for (char c : st.nextToken().toCharArray()) {
            target += Math.pow(10, t--) * (c - '0');
        }

        if (from == target) {
            System.out.println(0);
            continue;
        }

        boolean[] visited = new boolean[10000];
        visited[from] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{from, 0});

        int answer = -1;
        root:
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cv = current[0];
            for (int i = 0; i < 4; i++) {
                int p = getDigit(cv, 3, i);
                int init = cv - (int) Math.pow(10, 3 - i) * p;

                for (int j = 0; j < 10; j++) {
                    if ((i == 0 && j == 0) || p == j) {
                        continue;
                    }

                    int nv = init + (int) Math.pow(10, 3 - i) * j;

                    if (nv == target) {
                        answer = current[1] + 1;
                        break root;
                    }

                    if (!isPrime(nv)) {
                        continue;
                    }

                    if (visited[nv]) {
                        continue;
                    }

                    visited[nv] = true;
                    queue.add(new int[]{nv, current[1] + 1});
                }
            }
        }

        if (answer == -1) {
            System.out.println("Impossible");
        } else {
            System.out.println(answer);
        }

    }
}

static int getDigit(int value, int e, int id) {
    int rest = value % (int) Math.pow(10, e - id + 1);
    int div = (int) Math.pow(10, e - id);
    return (rest - value % div) / div;
}

static boolean isPrime(int value) {
    if (value == 1) {
        return false;
    }
    for (int i = 2; i <= Math.sqrt(value); i++) {
        if (value % i == 0) {
            return false;
        }
    }
    return true;
}
~~~
