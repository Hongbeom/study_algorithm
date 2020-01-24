## 백준 1783 - [병든 나이트](https://www.acmicpc.net/problem/1783)

그리디 알고리

### 풀이법

1. 나이트의 이동은 위, 아래, 오른쪽 -> 각각 1칸, 2칸씩.
- 각 이동을 한번 이상씩 수행해야 하기 때문에 칸이 충분하면 최대는 m-2 칸이 된다.
- 만약 4회이상 이동할 수 없는 경우 최대는 4이다.
- 4회이상 이동할때 오른쪽으로 1칸 가는 경우를 먼저 이동시키면서 4이상이면 break.
~~~JAVA
if (n > 2 && m > 6) {
    System.out.println(m - 2);
    return;
}

int a = n - 1;
int b = 0;
int answer = 1;
root:
while (true) {
    for (int i = 0; i < 4; i++) {
        int na = a + H[i];
        int nb = b + W[i];

        if (na < 0 || na >= n) {
            continue;
        }

        if (nb >= m) {
            break root;
        }

        if (++answer == 4) {
            break root;
        }
        a = na;
        b = nb;
        continue root;
    }
    break;
}
~~~
