## 백준 3663 - [고득점](https://www.acmicpc.net/problem/3663)

많이 해맨 문제... 초기 시작 방향만 정해주면 쉬운 문제. 다시 풀어보자.

### 풀이법

1. 각 알파벳이 타겟과 다른 자리를 최소한의 이동만 찾으면 되는 문제.
- 착각하여 초기 MST로 해결하려함.

2. 처음 시작 방향만 잘 정해주면 될것같다.
- 아래의 코드는 각 지점에서 최단으로 계속 이동해주는 코드 - 다시 풀어보자.

~~~JAVA
static void findPath(int now, int length, int distance, int count, List<Integer> change, boolean[] visited) {
    visited[now] = true;

    if (count == visited.length - 1) {
        finded = Math.min(finded, distance);
        return;
    }

    int to = -1;
    int dist = Integer.MAX_VALUE;
    List<int[]> list = new ArrayList<>();

    for (int i = 0; i < change.size(); i++) {
        int next = change.get(i);

        if (visited[i]) {
            continue;
        }

        int diff = Math.abs(next - change.get(now));
        int tmDist = Math.min(diff, length - diff);

        if (tmDist < dist) {
            to = i;
            dist = tmDist;
            list.clear();
        } else if (tmDist == dist) {
            list.add(new int[]{i, tmDist});
        }
    }
    findPath(to, length, distance + dist, count + 1, change, visited.clone());
    for (int[] l : list) {
        findPath(l[0], length, distance + l[1], count + 1, change, visited.clone());
    }
}
~~~