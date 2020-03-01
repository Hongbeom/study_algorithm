## 백준 1440 - [타임머신](https://www.acmicpc.net/problem/1440)


### 풀이법 

1. 시간에 대한 후보들을 만든다.
2. 후보를 제외한 2개의 숫자를 봤을때 두 숫자 모두 0이상 59이하라면 2를 더한다.


```JAVA
for (int i = 0; i < 3; i++) {
    time[i][0] = Integer.parseInt(st.nextToken());
    if (time[i][0] >= 1 && time[i][0] <= 12) {
        hour.add(i);
    }
    if (time[i][0] >= 0 && time[i][0] <= 59) {
        time[i][1] = 1;
    }
}

if (hour.isEmpty()) {
    System.out.println(0);
    return;
}

int answer = 0;
origin: for (int h : hour) {
    for (int i = 0; i < 3; i++) {
        if (i == h) {
            continue;
        }
        if(time[i][1] != 1){
            continue origin;
        }
    }
    answer += 2;
}
```
