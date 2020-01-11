## 백준 11053 - [가장 긴 증가하는 부분 수열](https://www.acmicpc.net/problem/12100)

DP 문제, N^2 과 NlongN의 풀이법이 존재
### O(N^2) 풀이법

1. memo 배열 활용. 초기 memo[i] = 1 로 초기화
2. 배열 탐색 -> memo[i] 는 1 ~ i-1 중의 자신보다 작은값의 max(memo[i]) + 1

~~~JAVA
static int checkN2(int[] numbers) {

    int[] memo = new int[numbers.length];
    Arrays.fill(memo, 1);

    int answer = 1;

    for (int i = 1; i < numbers.length; i++) {
        int current = numbers[i];
        int max = memo[i];
        for (int j = 0; j < i; j++) {
            if (current > numbers[j]) {
                max = Math.max(max, memo[j] + 1);
            }
        }
        memo[i] = max;
        answer = Math.max(answer, max);
    }


    return answer;
}
~~~


### O(NlogN) 풀이법

1. list 이용. 배열을 탐색
- list가 비어있거나 현재 값이 list의 마지막 값보다 크다면 list의 맨 뒤에 현재값 추가.
- 그렇지 않다면 이진탐색을 통해 그 값의 lower bound 검색 후 replace. 
~~~JAVA
static int checkNlogN(int[] numbers) {

    List<Integer> lis = new ArrayList<>();
    lis.add(numbers[0]);

    for (int i = 1; i < numbers.length; i++) {
        int last = lis.get(lis.size() - 1);
        if (numbers[i] > last) {
            lis.add(numbers[i]);
        } else {
            lis.set(getLowerBound(lis, 0, lis.size() - 1, numbers[i]), numbers[i]);
        }
    }

    return lis.size();
}


static int getLowerBound(List<Integer> list, int start, int end, int target) {

    int middle = (start + end) / 2;
    int current = list.get(middle);

    if (target > current) {
        return getLowerBound(list, middle + 1, end, target);
    } else {
        if (middle == 0 || list.get(middle - 1) < target) {
            return middle;
        } else {
            return getLowerBound(list, start, middle, target);
        }
    }
}
~~~

- list 의 길이가 곧 lis 의 길이
- lis에 해당되는 원소를 알고 싶다면 따로 배열을 만들어 각 원소들이 lis에 위치하는 인덱스 기억 -> 끝부터 배열 탐색
