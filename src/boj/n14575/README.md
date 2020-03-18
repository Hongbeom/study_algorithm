## 백준 14575 - [뒤풀이](https://www.acmicpc.net/problem/14575)


### 풀이법

1. 먼저 최소로 술을주고, 최대로 술을주는 경우에도 만족할 수 없으면 -1을 출력.
2. S를 이분탐색으로 구한다.
  - Max(L[i]) <= S <= Max(R[i])
  - 처음에 범위를 잘못 정해주어 시간초과가 발생.

```JAVA
static int bs(int left, int right) {

    int mid = (left + right) / 2;

    int value = getValue(mid);

    if (value >= T) {
        if (mid == LOW_MAX || getValue(mid - 1) < T) {
            return mid;
        }
        return bs(left, mid);

    } else {
        return bs(mid + 1, right);
    }
}

static int getValue(int s) {
    int value = 0;
    for (int[] limit : LIMIT) {
        int high = limit[1];
        value += Math.min(high, s);
    }
    return value;
}
```