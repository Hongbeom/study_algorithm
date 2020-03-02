## 백준 3943 - [헤일스톤 수열](https://www.acmicpc.net/problem/3943)


### 풀이법

1. 시작부터 1까지 최대값을 구한다. brute force
~~~JAVA
long start = Long.parseLong(br.readLine());
long answer = start;
while (start != 1) {
    if (start % 2 == 0) {
        start /= 2;
    } else {
        start = start * 3 + 1;
    }
    answer = Math.max(answer, start);
}
~~~
