## SW Academy 1767 - [미생물 격리](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl)

### 잘못된 접근

1. 크기가 쿤 군집을 먼저 이동하기 위해 PriorityQueue를 사용해 주었다.
  - 객체가 들어간 후, 객체의 값을 변경해도 PriorityQueue안에서는 적용이 되지 않는다...

### 풀이법

1. 모든값을 다 설정한 후 PriorityQueue에 집어넣어줕다.

```JAVA
static final int[] H = new int[]{-1, 1, 0, 0};
static final int[] W = new int[]{0, 0, -1, 1};

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;

    for (int tc = 1; tc <= TC; tc++) {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Cell[][] map = new Cell[n][n];

        List<Cell> next = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;

            Cell cell = new Cell(h, w, d, p, 0);
            map[h][w] = cell;
            next.add(cell);
        }

        long answer = 0;


        PriorityQueue<Cell> pq = new PriorityQueue<>();

        while (next.size() != 0) {

            for (Cell c : next) {
                pq.add(c);
            }

            next.clear();

            while (!pq.isEmpty()) {
                Cell current = pq.poll();


                if (current.step == m) {
                    answer += current.p;
                    continue;
                }

                int na = current.h + H[current.d];
                int nb = current.w + W[current.d];

                if (na == 0 || nb == 0 || na == map.length - 1 || nb == map[0].length - 1) {
                    current.p /= 2;
                    if (current.p == 0) {
                        if (map[current.h][current.w] == current) {
                            map[current.h][current.w] = null;
                        }
                        continue;
                    }
                    current.convert();
                }


                if (map[current.h][current.w] == current) {
                    map[current.h][current.w] = null;
                }

                if (map[na][nb] == null || map[na][nb].step <= current.step) {
                    current.step++;
                    current.h = na;
                    current.w = nb;
                    map[na][nb] = current;
                    next.add(current);
                } else {
                    map[na][nb].p += current.p;
                }

            }
        }


        System.out.println("#" + tc + " " + answer);
    }
}


static class Cell implements Comparable<Cell> {

    int h;
    int w;
    int d;
    int p;
    int step;

    Cell(int h, int w, int d, int p, int step) {
        this.h = h;
        this.w = w;
        this.d = d;
        this.p = p;
        this.step = step;
    }

    @Override
    public int compareTo(Cell o) {
        if (this.step < o.step) {
            return -1;
        } else if (this.step > o.step) {
            return 1;

        } else {
            if (this.p < o.p) {
                return 1;
            } else if (this.p > o.p) {
                return -1;
            }
        }
        return 0;
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
```