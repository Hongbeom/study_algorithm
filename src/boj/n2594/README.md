## 백준 2594 - [놀이공원](https://www.acmicpc.net/problem/2594)

### 풀이법 

1. 시작시간 - 10분 오름차순, 끝나는시간 + 10분 오름차순으로 정렬.
2. 정렬된 리스트를 앞의 끝나는시간 - 뒤의 시작시간의 최대값을 찾는다.
3. 오후 10시에 일과가 끝나니 탐색 후 이 경우도 생각을 해준다.

```JAVA

for (int i = 0; i < n; i++) {
    st = new StringTokenizer(br.readLine());
    int startTime = Integer.parseInt(st.nextToken());
    int endTime = Integer.parseInt(st.nextToken());
    time.add(new int[]{subTime(startTime, 10), addTime(endTime, 10)});
}
        
time.sort((o1, o2) -> {
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
        return 0;
    }
});

int rest = 0;
int beforeEnd = 1000;

for (int[] t : time) {
    if (t[0] > beforeEnd) {
        rest = Math.max(rest, getRest(beforeEnd, t[0]));
    }
    beforeEnd = Math.max(beforeEnd, t[1]);
}

rest = Math.max(rest, getRest(beforeEnd, 2200));
```

~~~JAVA
static int addTime(int time, int add) {

    int hour = time / 100;
    int minute = (time - hour * 100) + add;

    if (minute >= 60) {
        hour += minute / 60;
        minute %= 60;
    }

    return hour * 100 + minute;
}

static int subTime(int time, int sub) {

    int hour = time / 100;
    int minute = time - (hour * 100);
    if (sub >= 60) {
        hour -= sub / 60;
        sub %= 60;
    }

    minute -= sub;

    if (minute < 0) {
        hour--;
        minute += 60;
    }

    return hour * 100 + minute;
}

static int getRest(int t1, int t2) {
    if (t1 > t2) {
        return 0;
    }

    int h1 = t1 / 100;
    int h2 = t2 / 100;

    int m1 = t1 - h1 * 100;
    int m2 = t2 - h2 * 100;

    int h = h2 - h1;
    int m = m2 - m1;

    if (m < 0) {
        h--;
        m += 60;
    }

    return m + h * 60;
}
~~~


