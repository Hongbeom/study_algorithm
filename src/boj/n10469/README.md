## 백준 10469 - [사이 나쁜 여왕들](https://www.acmicpc.net/problem/10469)

### 풀이법

1. 여왕들의 위치에서 각각 상하좌우 대각선 살핀다.

~~~JAVA

static final int[] H = new int[]{-1, 1, 0, 0, 1, 1, -1, -1};
static final int[] W = new int[]{0, 0, -1, 1, 1, -1, -1, 1};
    
static boolean check(char[][] board, int[] start) {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < 8; i++) {
            queue.add(new int[]{start[0], start[1], i});
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            current[0] += H[current[2]];
            current[1] += W[current[2]];


            if (current[0] < 0 || current[1] < 0 || current[0] >= 8 || current[1] >= 8) {
                continue;
            }

            if (board[current[0]][current[1]] == '*') {
                return true;
            }

            queue.add(current);
        }

        return false;
    }
~~~

