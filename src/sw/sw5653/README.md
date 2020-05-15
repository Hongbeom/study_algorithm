## SW Academy 5653 - [줄기세포배양](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRJ8EKe48DFAUo)

### 풀이법

1. 세포 클래스를 만든다.
  - 큰 세포가 작은 세포를 이기므로 비교 가능하게 구현.
2. PriorityQueue 를 이용하여 세포 번식을 구현.

```JAVA
static int ADJ = 300;

static int[] H = new int[]{-1, 1, 0, 0};
static int[] W = new int[]{0, 0, -1, 1};

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;

    for (int tc = 1; tc <= TC; tc++) {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[][] check = new boolean[n + ADJ * 2][m + ADJ * 2];
        PriorityQueue<Cell> queue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x != 0) {
                    Cell cell = new Cell(ADJ + i, ADJ + j, x, 0);
                    check[ADJ + i][ADJ + j] = true;
                    queue.add(cell);
                }
            }
        }


        int answer = 0;
        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            if (current.k == k) {
                answer++;
                continue;
            }


            if (current.activate) {
                current.left--;
                current.k++;
                if (current.left > 0) {
                    queue.add(current);
                }


                if (current.left + 1 == current.x) {

                    for (int i = 0; i < 4; i++) {

                        int a = current.h + H[i];
                        int b = current.w + W[i];

                        if (check[a][b]) {
                            continue;
                        }
                        check[a][b] = true;
                        queue.add(new Cell(a, b, current.x, current.k));
                    }
                }

            } else {
                current.left--;
                if (current.left == 0) {
                    current.activate = true;
                    current.left = current.x;
                }
                current.k++;
                queue.add(current);
            }
        }

        System.out.println("#" + tc + " " + answer);
    }

}

static class Cell implements Comparable<Cell> {
    boolean activate;

    int h;
    int w;

    int x;
    int k;
    int left;


    Cell(int h, int w, int x, int k) {
        this.activate = false;
        this.h = h;
        this.w = w;
        this.x = x;
        this.k = k;
        this.left = x;
    }


    @Override
    public int compareTo(Cell o) {
        if (this.k < o.k) {
            return -1;
        } else if (this.k > o.k) {
            return 1;
        } else {
            if (this.x > o.x) {
                return -1;
            } else if (this.x < o.x) {
                return 1;
            }
        }
        return 0;
    }
}
```