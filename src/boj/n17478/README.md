## 백준 17478 - [재귀함수가 뭔가요?](https://www.acmicpc.net/problem/17478)

### 풀이법

1. 재귀함수를 주어진 조건대로 짜면 되는 문제.

~~~JAVA
static void ans(int count, int target) {
    append(count);
    ANS.append(SEN[0]);
    if (count == target) {
        append(count);
        ANS.append(SEN[4]);
    } else {
        for (int i = 1; i <= 3; i++) {
            append(count);
            ANS.append(SEN[i]);
        }
        ans(count + 1, target);
    }
    append(count);
    ANS.append(SEN[5]);

}
~~~