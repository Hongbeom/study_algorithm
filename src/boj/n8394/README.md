## 백준 8394 - [악수](https://www.acmicpc.net/problem/8394)

### 초기 잘못된 접근

1. 사람은 오른쪽, 또는 왼쪽으로 한번만 악수할 수 있기 때문에 왼쪽에서부터 오른쪽으로 악수하는경우, 안하는 경우를 재귀적으로 탐색해봄.
~~~JAVA
static void check(int start, int end) {
    if (start >= end) {
        ANS++;
        return;
    }
    // 오른쪽 사람과 악수를 하는 경우
    check(start + 2, end);
    // 으론쪽 사람과 악수를 하지 않는 경우
    check(start + 1, end);
}
~~~

- n이 최대 10,000,000 이기 때문에 당연히 시간초과.

### 풀이법

1. 다시 생각해보니 dp 문제, n만큼의 배열을 생성하고 memo[n] = memo[n-1] + memo[n-2]
- memo[0] = 0, memo[1] = 1, memo[2] = 2
- long 으로 배열 선언 -> 하지만 long으로도 오버플로우가 발생하는 것 같다.

2. 문제를 다시보니... 마지막 자리만 출력하면 되므로, 1의 자리만 기억하도록 함.

~~~JAVA
int[] memo = new int[n + 1];

memo[1] = 1;
memo[2] = 2;

for (int i = 3; i < n + 1; i++) {
    memo[i] = (memo[i - 2] + memo[i - 1]) % 10;
}
~~~