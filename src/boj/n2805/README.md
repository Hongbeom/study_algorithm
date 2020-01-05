## 백준 2805 - [나무 자르기](https://www.acmicpc.net/problem/2805)

이분탐색 문제. 변이하는 범위를 생각하고 풀자. 자료형을 고려하지 않아 시간이 걸린 문제.

### 풀이법

1. h를 0 부터 가장 큰 높이까지 이분탐색 시작.
2. value를 보고, value가 m보다 작다 -> 왼쪽 탐색
3. value가 m보다 크거나 같다 -> 자기 자신보다 앞에있는걸 보고 그 값이 target보다 적으면 탐색 종료


```JAVA
static long bs(long start, long end, long[] trees, long target) {

    long middle = (start + end) / 2;
    long[] current = getValue(trees, middle);

    long m = current[0];
    long next = current[1];

    if (m < target) {
        return bs(start, middle, trees, target);
    } else {
        if (next < target) {
            return middle;
        } else {
            return bs(middle + 1, end, trees, target);
        }

    }
}
```

- 이분 탐색시, start, end, middle의 변화를 체크해주자.
