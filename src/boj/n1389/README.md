## 백준 1389 - [케빈 베이컨의 6단계 법칙](https://www.acmicpc.net/problem/1389)

플로이드 워셜 알고리즘 이용.

### 풀이법

1. 3중 포문을 이용한 플로이드 워셜 알고리즘 이용.

~~~JAVA
static int floyd(int[][] relation) {

    for (int k = 0; k < relation.length; k++) {
        from:
        for (int i = 0; i < relation.length; i++) {
            for (int j = 0; j < relation.length; j++) {
                if (relation[i][k] == Integer.MAX_VALUE) {
                    continue from;
                }

                if (relation[k][j] == Integer.MAX_VALUE) {
                    continue;
                }

                if (relation[i][k] + relation[k][j] < relation[i][j]) {
                    relation[i][j] = relation[i][k] + relation[k][j];
                }
            }
        }
    }
    int answer = Integer.MAX_VALUE;
    int id = -1;
    for (int i = 0; i < relation.length; i++) {
        int sum = 0;
        for (int j = 0; j < relation.length; j++) {
            if (relation[i][j] != Integer.MAX_VALUE) {
                sum += relation[i][j];
            }
        }

        if (sum < answer) {
            answer = sum;
            id = i + 1;
        }
    }

    return id;
}
~~~o

- 거쳐가는 정점을 이용하여 거리의 최소를 업데이트 해준다.
