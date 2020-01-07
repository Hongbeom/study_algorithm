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
