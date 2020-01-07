## 백준 13458 - [시험감독](https://www.acmicpc.net/problem/13458)

### 풀이법

1. 시험장에는 총 감독관이 무조건 1명은 들어간다.
2. 시험장에 있는 인원에서 총 감독관이 커버 가능한 인원을 빼주고 그 수가 양수라면 그만큼 보조 감독관을 추가해 주면 되는 쉬운 문제


```JAVA
long answer = 0;

    for (int p : people) {
        int rest = p - b;
        answer++;
        if (rest > 0) {
            if (rest % c == 0) {
                answer += rest / c;
            } else {
                answer += rest / c + 1;
            }

        }
    }
```
