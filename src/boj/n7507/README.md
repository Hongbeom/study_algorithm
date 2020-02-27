## 백준 7507 - [올림픽 게임](https://www.acmicpc.net/problem/7507)


### 풀이법

1. 날짜 -> 시작 시간 -> 끝나는 시간 순으로 sorting
2. sort된 리스트를 탐색하며 이전 끝나는 시각보다 현재의 경기가 늦으면 선택.
3. 그렇지 않은데 이 경기가 이전 경기보다 끝나는 시각이 더 먼저면 경기를 바꿈.
~~~JAVA
matches.sort((o1, o2) -> {
    if (o1[0] > o2[0]) {
        return 1;
    } else if (o1[0] < o2[0]) {
        return -1;
    } else {
        if (o1[1] > o2[1]) {
            return 1;
        } else if (o1[1] < o2[1]) {
            return -1;
        }
    }
    return 0;
});


int d = matches.get(0)[0] - 1;
int beforeStart = 0;
int beforeEnd = 0;

for (int[] match : matches) {

    if (d != match[0]) {
        d = match[0];
        beforeEnd = 0;
        beforeStart = 0;
    }

    if (beforeStart == 0 && beforeEnd == 0) {
        count++;
        beforeStart = match[1];
        beforeEnd = match[2];
    } else {
        if (beforeEnd <= match[1]) {
            count++;
            beforeStart = match[1];
            beforeEnd = match[2];
        } else if (match[2] < beforeEnd) {
            beforeStart = match[1];
            beforeEnd = match[2];
        }
    }
}
~~~

