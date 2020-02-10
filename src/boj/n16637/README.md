## 백준 16637 - [괄호 추가하기](https://www.acmicpc.net/problem/16637)

### 풀이법

1. boolean 배열을 이용하여 괄호의 경우의 수를 구해준다.
- 괄호는 붙어 있을 수 없다.


```JAVA
static void getCandidates(List<boolean[]> list, boolean[] tm, int start, int count, int target) {

    if (count == target) {
        list.add(tm);
        return;
    }

    if (start >= tm.length) {
        return;
    }


    getCandidates(list, tm.clone(), start + 1, count, target);
    tm[start] = true;
    getCandidates(list, tm.clone(), start + 2, count + 1, target);

}
```

2. 각 괄호의 존재 경우마다의 계산을 해주고 최댓값을 갱신.
~~~JAVA
static int calculate(List<Integer> inNums, List<Character> inOps, boolean[] bracket) {

    List<Integer> numbers = new ArrayList<>(inNums);
    List<Character> op = new ArrayList<>(inOps);

    for (int i = bracket.length - 1; i >= 0; i--) {
        if (bracket[i]) {
            int left = i;
            int right = i + 1;
            int value = operation(numbers.get(left), numbers.get(right), op.get(i));
            numbers.set(left, value);
            numbers.remove(right);
            op.remove(i);
        }
    }

    int ret = numbers.get(0);

    for (int i = 1; i < numbers.size(); i++) {

        ret = operation(ret, numbers.get(i), op.get(i - 1));
    }

    return ret;
}

static int operation(int left, int right, char op) {

    switch (op) {
        case '+':
            return left + right;
        case '-':
            return left - right;
        case '*':
            return left * right;
    }
    return -1;

}
~~~