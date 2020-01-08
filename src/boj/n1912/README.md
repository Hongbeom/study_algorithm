## 백준 1912 - [연속합](https://www.acmicpc.net/problem/1912)

초기에 분할정복을 이용해 NlongN의 시간복잡도로 시도 -> 시간초과 -> DP 이용

### 초기 잘못된 접근 1

1. [N2104](../n2104) 와 비슷한 문제라고 판단 -> 분할정복을 이용해 보자.
2. 배열을 왼쪽, 오른쪽, 걸쳐있는 배열로 나눈 후 max값을 계속 갱신하도록 시도.
- 걸쳐있는 배열을 확장하는 방법은 1227번 문제와 똑같이 왼쪽, 오른쪽 중에 더 큰쪽으로 확장.

```JAVA
static int check(int[] arr, int start, int end) {

    if (start < end) {
        int middle = (start + end) / 2;

        int max = Math.max(check(arr, start, middle), check(arr, middle + 1, end));

        int sum = arr[middle];
        max = Math.max(sum, max);

        int lid = middle - 1;
        int rid = middle + 1;

        while (lid >= start && rid <= end) {
            if (arr[lid] > arr[rid]) {
                sum += arr[lid--];
            } else {
                sum += arr[rid++];
            }
            max = Math.max(sum, max);
        }

        while (lid >= start) {
            sum += arr[lid--];
            max = Math.max(sum, max);
        }

        while (rid <= end) {
            sum += arr[rid++];
            max = Math.max(sum, max);
        }

        return max;

    } else {
        return arr[start];
    }
}
```
- 반례가 존재 -> -1, -1, 3, -2, 5
- 더 큰쪽으로 확장하게 되면 최댓값이 도출될 수 없음

### 잘못된 접근 2

1. 걸쳐있는 배열의 최댓값을 도출할때, 왼쪽으로의 최대합과 오른쪽으로의 최대합을 단순히 더해주면 해결
- NlogN으로 만들 수 있다.

~~~JAVA
static int check(int[] arr, int start, int end) {

    if (start < end) {

        int middle = (start + end) / 2;

        int sum = 0;
        int left = Integer.MIN_VALUE;
        for (int i = middle; i >= 0; i--) {
            sum += arr[i];
            left = Math.max(sum, left);
        }

        sum = 0;
        int right = Integer.MIN_VALUE;

        for (int i = middle + 1; i <= end; i++) {
            sum += arr[i];
            right = Math.max(sum, right);
        }

        int max = left + right;
        int divide = Math.max(check(arr, start, middle), check(arr, middle + 1, end));

        return Math.max(max, divide);


    } else {

        return arr[start];

    }
}
~~~

- 이 코드로 시간초과가 발생

### 풀이법 DP

1. 단순히 입력을 받고, 이전의 값과 더해준것과 비교
- 합이 입력보다 크면 저장
- 적으면 입력을 선택
- 그 값들중에 max를 계속 갱신.
- 시간복잡도 N

~~~JAVA
int memo = 0;
int answer = Integer.MIN_VALUE;
for (int i = 0; i < n; i++) {
    int number = Integer.parseInt(st.nextToken());
    if (i == 0) {
        memo = number;
    } else {
        if (memo + number > number) {
            memo = memo + number;
        } else {
            memo = number;
        }
    }
    answer = Math.max(answer, memo);
}
~~~

