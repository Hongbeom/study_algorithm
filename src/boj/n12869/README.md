## 백준 12869 - [뮤탈리스크](https://www.acmicpc.net/problem/12869)

### 풀이법

1. 뮤탈리스크의 공격에 대한 경우의 수를 만든다.
~~~JAVA
static void candidate(List<int[]> list, int[] tm, int[] check, int start) {
    if (start == 3) {
        list.add(tm.clone());
        return;
    }
    for (int i = 1; i <= 9; i *= 3) {
        if (check[i] == 0) {
            check[i]--;
            tm[start] = i;
            candidate(list, tm.clone(), check.clone(), start + 1);
            check[i]++;
        }
    }
}
~~~
2. 공격 경우에 수에 대해 BFS -> 모든 scv의 체력이 0이 도달할때까지.
```JAVA
Queue<int[]> queue = new LinkedList<>();
queue.add(scv);
boolean[][][] visited = new boolean[61][61][61];
int sc = 0;
while (!queue.isEmpty()) {
    int[] current = queue.poll();
    int s1 = current[0];
    int s2 = current[1];
    int s3 = current[2];
    int step = current[3];

    if (sc + 1 == step) {
        sc++;
        visited = new boolean[61][61][61];
    }

    if (s1 <= 0 && s2 <= 0 && s3 <= 0) {
        System.out.println(step);
        return;
    }

    for (int[] can : candidate) {

        int ns1 = s1 - can[0] <= 0 ? 0 : s1 - can[0];
        int ns2 = s2 - can[1] <= 0 ? 0 : s2 - can[1];
        int ns3 = s3 - can[2] <= 0 ? 0 : s3 - can[2];

        if (visited[ns1][ns2][ns3]) {
            continue;
        }
        visited[ns1][ns2][ns3] = true;
        queue.add(new int[]{ns1, ns2, ns3, step + 1});
    }
}
```
