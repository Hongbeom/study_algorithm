## 백준 2104 - [부분배열 고르기](https://www.acmicpc.net/problem/2104)

### 분할 정복 풀이법

1. 문제에서 요구하는것 -> 부분배열중 그 부분배열의 합과 최솟값의 곱이 가장 큰 값
2. 분할정복 이용 풀이
- 왼쪽 배열, 오른쪽 배열, 왼쪽과 오른쪽이 겹친 부분배열 중 계속 최댓값을 갱신
- 재귀적으로 탐색 -> start 와 end가 같을 시 자기 자신^2을 리턴.
- 겹친 부분배열을 고를때 middle에서 시작하여 왼쪽과 오른쪽으로 배열을 넓혀 나감
  - 이때, 왼쪽과 오른쪽 중 값이 더 큰 쪽으로 넓혀 나가야 한다. -> 합과 최솟값의 곱이 값이므로

```JAVA
static long check(long[] arr, int start, int end) {

    if (start < end) {

        int middle = (start + end) / 2;
        long left = check(arr, start, middle);
        long right = check(arr, middle + 1, end);

        long max = Math.max(left, right);

        long min = arr[middle];
        long sum = min;

        int lid = middle - 1;
        int rid = middle + 1;

        while (lid >= start && rid <= end) {
            if (arr[lid] > arr[rid]) {
                min = Math.min(min, arr[lid]);
                sum += arr[lid--];
            } else {
                min = Math.min(min, arr[rid]);
                sum += arr[rid++];
            }
            max = Math.max(max, sum * min);
        }

        while (lid >= start) {
            min = Math.min(min, arr[lid]);
            sum += arr[lid--];
            max = Math.max(max, sum * min);
        }

        while (rid <= end) {
            min = Math.min(min, arr[rid]);
            sum += arr[rid++];
            max = Math.max(max, sum * min);
        }

        return max;

    } else {

        return arr[start] * arr[start];

    }


}
```

