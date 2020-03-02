## 백준 3028 - [창영 마을](https://www.acmicpc.net/problem/3028)

### 풀이법

1. 섞으면서 포지션을 갱신.
~~~JAVA
for (char c : mix) {
    switch (c) {
        case 'A':
            if (position == 1) {
                position = 2;
            } else if (position == 2) {
                position = 1;
            }
            break;
        case 'B':
            if (position == 2) {
                position = 3;
            } else if (position == 3) {
                position = 2;
            }
            break;
        case 'C':
            if (position == 1) {
                position = 3;
            } else if (position == 3) {
                position = 1;
            }
            break;
    }
}
~~~
