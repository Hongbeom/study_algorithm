## 백준 1931 - [회의실배정](https://www.acmicpc.net/problem/1931)

### 풀이법

1. 인풋으로 회의정보를 받고, 시작시간을 기준으로 오름차순으로 정렬해준다.

```JAVA
Arrays.sort(meetings, (o1, o2) -> {
    if (o1[0] > o2[0]) {
        return 1;
    } else if (o1[0] < o2[0]) {
        return -1;
    }
    return 0;
});
```
2. 정렬된 회의를 탐색한다.
- 만약 현재 회의의 시작시간이 전 회의의 끝나는 시간보다 적고, 끝나는 시간도 적다면 그 회의를 택한다.
~~~JAVA
int answer = 0;
int compare = 0;
for (int[] meeting : meetings) {
    if (compare == 0) {
        compare = meeting[1];
        answer++;
    } else {
        if (compare > meeting[0]) {
            if(compare > meeting[1]){
                compare = meeting[1];
            }
        } else {
            compare = meeting[1];
            answer++;
        }
    }
}
~~~
