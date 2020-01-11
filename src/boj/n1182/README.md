## 백준 1182 - [부분수열의 합](https://www.acmicpc.net/problem/1182)

### 풀이법

1. 재귀 함수를 이용해 2^N의 경우를 모두 탐색
- 만약 합(target)이 0인 경우, 1을 빼준다.

```JAVA
    static void check(int[] numbers, int current, int start) {
        if (start == numbers.length) {
            if (current == TARGET) {
                ANS++;
            }
            return;
        }

        check(numbers, current + numbers[start], start + 1);
        check(numbers, current, start + 1);

    }
```
