## 백준 13904 - [과제](https://www.acmicpc.net/problem/13904)

### 풀이법

1. 가장 긴 과제 기한을 중점으로, priority queue를 이용하여 각 기한중에 max값을 계속 더해준다.
~~~JAVA
int bid = 0;
int answer = 0;
int beforeDay = maxDay;

while (beforeDay > 0) {

    if (bid < assignments.size() && beforeDay == assignments.get(bid)[0]) {
        for (int i = bid; i < assignments.size(); i++) {
            if (assignments.get(i)[0] != beforeDay) {
                bid = i;
                break;
            }
            if (i == assignments.size() - 1) {
                bid = assignments.size();
            }
            pq.add(assignments.get(i));
        }
    }

    if (!pq.isEmpty()) {
        answer += pq.poll()[1];
    }

    beforeDay--;
}

System.out.println(answer);
~~~
