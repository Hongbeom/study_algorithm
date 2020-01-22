## 백준 2866 - [자리 전쟁](https://www.acmicpc.net/problem/2866)


그리디로 풀어야 하는 문제. 초기 문제이해를 잚못하여 시간이 꽤 걸림.

### 초기 잘못된 접근

1. 자기와 가장 가까운 자석을 획득한 사람은 다른 사람의 좌석을 선택하는데 영향을 주지 않는다.
- 이걸 이해하지 못하고 좌석에 가장 가까운 손님 카운트.

~~~JAVA
for (int[] seat : seats) {
    double min = Double.MAX_VALUE;
    int minCnt = 0;
    for (int[] person : people) {
        double distance = getDistance(seat, person);

        if (distance < min) {
            min = distance;
            minCnt = 1;
        } else if (distance == min) {
            minCnt++;
        }
    }
    if (minCnt > 1) {
        answer++;
    }
}
~~~


### 풀이법

1. 좌석과 사람의 거리를 모두 구해 거리가 가장 낮은 순으로 탐색한다.
- 가장 짧은 거리를 distance 배열에 기억하고 그 사람은 좌석을 가졌으므로 peopleCheck배열에 true로 설정해 그 사람과 관련된 거리는 모두 무시한다.
- 만약 distance 배열에 기억된 값과 거리가 같다면 그 좌석은 전쟁이 일어난다.

~~~JAVA
boolean[] personCheck = new boolean[people.size()];
boolean[] seatCheck = new boolean[seats.size()];

int[] distance = new int[seats.size()];
Arrays.fill(distance, Integer.MAX_VALUE);

PriorityQueue<Edge> pq = new PriorityQueue<>();

for (int i = 0; i < seats.size(); i++) {
    int[] seat = seats.get(i);
    for (int j = 0; j < people.size(); j++) {
        int[] person = people.get(j);
        pq.add(new Edge(i, j, getDistance(seat, person)));
    }
}
int answer = 0;
while (!pq.isEmpty()) {
    Edge edge = pq.poll();

    if(personCheck[edge.person]){
        continue;
    }

    if(distance[edge.seat] > edge.distance){
        distance[edge.seat] = edge.distance;
        personCheck[edge.person] = true;
    }else if(distance[edge.seat] == edge.distance){
        personCheck[edge.person] = true;
        if(!seatCheck[edge.seat]){
            seatCheck[edge.seat] = true;
            answer++;
        }

    }
}
~~~
