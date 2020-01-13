## 백준 1654 - [랜선 자르기](https://www.acmicpc.net/problem/1654)
 
 
 ### 풀이법
 
 1. 이분탐색 이용
 - 0으로 나누어 주는 경우 주의
 - 문제 조건에선는 int범위 이지만 실제 연산에서는 long값이 나올수도 있다.
 
 ```JAVA
static long bs(long start, long end) {

    long middle = (start + end) / 2;

    long current = getValue(middle, LAN);

    if (current < TARGET) {
        // 왼쪽으로 이동
        return bs(start, middle);
    } else {
        if (getValue(middle + 1, LAN) < TARGET) {
            return middle;
        }

        return bs(middle + 1, end);

        // 오른쪽으로 이동 --> 여기서 판별이 이루어 져야 한다.
    }

}

static long getValue(long line, long[] lan) {
    long number = 0;

    for (long l : lan) {
        number += l / line;
    }

    return number;
}
```