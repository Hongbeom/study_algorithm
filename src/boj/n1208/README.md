## 백준 1208 - [부분수열의 합 2](https://www.acmicpc.net/problem/1208)

[N1182](../n1182) 에서 범위가 2배 늘었다. - meet in the middle

### 풀이법

1. 브루트 포스로 하게되면 N(2^n) 이므로 쵀대 2^40 이므로 당연히 시간 초과
2. 배열의 왼쪽, 오른쪽으로 먼저 나눈다.
3. 왼쪽 부분배열의 값들을 count - HashMap 이용 -> 2^N/2 * N(hash map 탐색시간?)
4. 오른쪽 부분배열의 결과를 이용해 target - sum 의 저장된 값들을 더해준다 -> 2^N/2 * N


```JAVA
static void checkLeft(int[] numbers, int current, int start, int end) {

    if (start == end) {
        Integer cnt = MEMO.getOrDefault(current, null);
        if (cnt == null) {
            MEMO.put(current, 1);
        } else {
            MEMO.put(current, cnt + 1);
        }
        return;
    }

    checkLeft(numbers, current + numbers[start], start + 1, end);
    checkLeft(numbers, current, start + 1, end);


}

static void checkRight(int[] numbers, int current, int start, int end) {

    if (start == end) {
        ANS += MEMO.getOrDefault(TARGET - current, 0);
        return;
    }

    checkRight(numbers, current + numbers[start], start + 1, end);
    checkRight(numbers, current, start + 1, end);

}
```
