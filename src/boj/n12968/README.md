## 백준 12968 - [방문](https://www.acmicpc.net/problem/12968)


### 풀이법

1. 가로 또는 세로 중 하나가 짝수이면 몇번이든 동일하게 방문 가능
2. 가로 또는 세로가 모두 홀수일 경우 1번만 동일하게 방문 가능
~~~JAVA
if (r % 2 == 1 && c % 2 == 1) {
    if (k == 1) {
        System.out.println(1);
    } else {
        System.out.println(0);
    }
} else {
    System.out.println(1);
}
~~~
