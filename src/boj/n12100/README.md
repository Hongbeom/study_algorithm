## 백준 12100 - [2048 (Easy)](https://www.acmicpc.net/problem/12100)

### 풀이법

1. 변형된 게임판, 스텝, max로 이루어진 클래스 구현.
~~~JAVA

static class Node {
    int[][] board;
    int max;
    int step;

    Node(int[][] board, int max, int step) {
        this.board = board;
        this.max = max;
        this.step = step;
    }
}

~~~
2. 각 노드에 대해서 상,하,좌,우 로 이동 시키고 max 값 갱신 --> step이 5일때 max를 도출
- 합쳐질때 앞에서부터 뒤로 이동. 합쳐짐을 판단하는 summed 배열 이용.
```JAVA
Arrays.sort(meetings, (o1, o2) -> {
    if (o1[0] > o2[0]) {
        return 1;
    } else if (o1[0] < o2[0]) {
        return -1;
    }
    return 0;
});
```
2. 정렬된 회의를 탐색한다.
- 만약 현재 회의의 시작시간이 전 회의의 끝나는 시간보다 적고, 끝나는 시간도 적다면 그 회의를 택한다.
~~~JAVA
static int move(int[][] board, int direction) {
    int max = Integer.MIN_VALUE;
    boolean[][] summed = new boolean[board.length][board[0].length];

    switch (direction) {
        case 0:
            for (int j = 0; j < board[0].length; j++) {
                for (int i = 1; i < board.length; i++) {
                    int id = i;
                    while (id >= 1) {
                        if (board[id][j] == 0) {
                            id--;
                            continue;
                        } else if (board[id - 1][j] == 0) {
                            board[id - 1][j] = board[id][j];
                            board[id][j] = 0;
                            if (summed[id][j]) {
                                summed[id - 1][j] = summed[id][j];
                                summed[id][j] = false;
                            }
                        } else if (board[id - 1][j] == board[id][j] && !summed[id - 1][j] && !summed[id][j]) {
                            board[id - 1][j] *= 2;
                            summed[id - 1][j] = true;
                            board[id][j] = 0;

                            max = Math.max(max, board[id - 1][j]);
                        }
                        id--;
                    }

                }
            }
            break;
        case 1:
            for (int j = 0; j < board[0].length; j++) {
                for (int i = board.length - 2; i >= 0; i--) {
                    int id = i;
                    while (id <= board.length - 2) {
                        if (board[id][j] == 0) {
                            id++;
                            continue;
                        } else if (board[id + 1][j] == 0) {
                            board[id + 1][j] = board[id][j];
                            board[id][j] = 0;
                            if (summed[id][j]) {
                                summed[id + 1][j] = summed[id][j];
                                summed[id][j] = false;
                            }
                        } else if (board[id + 1][j] == board[id][j] && !summed[id + 1][j] && !summed[id][j]) {
                            board[id + 1][j] *= 2;
                            summed[id + 1][j] = true;
                            board[id][j] = 0;

                            max = Math.max(max, board[id + 1][j]);
                        }
                        id++;
                    }

                }
            }
            break;

        case 2:
            for (int i = 0; i < board.length; i++) {
                for (int j = 1; j < board[0].length; j++) {
                    int id = j;
                    while (id >= 1) {
                        if (board[i][id] == 0) {
                            id--;
                            continue;
                        } else if (board[i][id - 1] == 0) {
                            board[i][id - 1] = board[i][id];
                            board[i][id] = 0;
                            if (summed[i][id]) {
                                summed[i][id - 1] = summed[i][id];
                                summed[i][id] = false;
                            }
                        } else if (board[i][id - 1] == board[i][id] && !summed[i][id - 1] && !summed[i][id]) {
                            board[i][id - 1] *= 2;
                            summed[i][id - 1] = true;
                            board[i][id] = 0;

                            max = Math.max(max, board[i][id - 1]);
                        }
                        id--;
                    }

                }
            }
            break;
        case 3:
            for (int i = 0; i < board.length; i++) {
                for (int j = board[0].length - 2; j >= 0; j--) {
                    int id = j;
                    while (id <= board[0].length - 2) {
                        if (board[i][id] == 0) {
                            id++;
                            continue;
                        } else if (board[i][id + 1] == 0) {
                            board[i][id + 1] = board[i][id];
                            board[i][id] = 0;
                            if (summed[i][id]) {
                                summed[i][id + 1] = summed[i][id];
                                summed[i][id] = false;
                            }
                        } else if (board[i][id + 1] == board[i][id] && !summed[i][id + 1] && !summed[i][id]) {
                            board[i][id + 1] *= 2;
                            summed[i][id + 1] = true;
                            board[i][id] = 0;

                            max = Math.max(max, board[i][id + 1]);
                        }
                        id++;
                    }

                }
            }
            break;
    }

    return max;
}
~~~
