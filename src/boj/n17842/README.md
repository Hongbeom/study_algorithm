## 백준 17842 - [버스 노선](https://www.acmicpc.net/problem/17842)

### 풀이법

1. 리프노드 -> 리프노드로 가는 노선 계속 선택
2. 리프토드의 갯수 / 2 + 리프노드의 갯수 % 2

~~~JAVA
for (int i = 0; i < n - 1; i++) {
    st = new StringTokenizer(br.readLine());
    int from = Integer.parseInt(st.nextToken());
    int to = Integer.parseInt(st.nextToken());

    if (graph[from] == null) {
        graph[from] = new ArrayList<>();
    }

    if (graph[to] == null) {
        graph[to] = new ArrayList<>();
    }

    graph[from].add(to);
    graph[to].add(from);
}

int answer = 0;
for (List<Integer> g : graph) {
    if (g.size() == 1) {
        answer++;
    }
}
~~~