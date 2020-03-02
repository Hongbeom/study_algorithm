## 백준 3029 - [경고](https://www.acmicpc.net/problem/3029)


### 풀이법

1. 단순히 시간을 계산한다.
- 조건을 주의. 최소 1초를 기다리고 최대 24시간을 기다린다.
~~~JAVA
String wt = br.readLine();
String bt = br.readLine();

if(wt.equals(bt)){
    System.out.println("24:00:00");
    return;
}

int[] start = new int[3];
int[] bomb = new int[3];

StringTokenizer sst = new StringTokenizer(wt, ":");
StringTokenizer bst = new StringTokenizer(bt, ":");
for (int i = 0; i < 3; i++) {
    start[i] = Integer.parseInt(sst.nextToken());
    bomb[i] = Integer.parseInt(bst.nextToken());
}

int h = bomb[0] - start[0];
int m = bomb[1] - start[1];
int s = bomb[2] - start[2];

String hour;
String minute;
String second;

if (s < 0) {
    s += 60;
    m--;
}
if (m < 0) {
    m += 60;
    h--;
}
if (h < 0) {
    h += 24;
}

if (s < 10) {
    second = "0" + s;
} else {
    second = "" + s;
}

if (m < 10) {
    minute = "0" + m;
} else {
    minute = "" + m;
}

if (h < 10) {
    hour = "0" + h;
} else {
    hour = "" + h;
}
~~~
