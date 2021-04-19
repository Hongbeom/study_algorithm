## 백준 1967 - [트리의 지름](https://www.acmicpc.net/problem/1967)


### 풀이법

1. 트리의 성격은 사이클이 없고, 무방향이며, 어떤 두 노드간 경로가 반드시 한개 존재한다.

2. 그렇다는 말은 어느 한 노드에서 제일 먼 노드는 트리의 지름이 되는 두 노드중 하나이다.

3. dfs를 이용해 임의의 노드에서 제일 먼 노드를 구한 뒤, 그 노드에서 제일 먼 노드까지의 거리가 트리의 지름이 된다.

~~~JAVA
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    List<Info>[] graph = new ArrayList[n];

    for (int i = 0; i < n - 1; i++) {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int from = Integer.parseInt(st.nextToken()) - 1;
    int to = Integer.parseInt(st.nextToken()) - 1;
    int w = Integer.parseInt(st.nextToken());

    if (graph[from] == null) graph[from] = new ArrayList<>();
    if (graph[to] == null) graph[to] = new ArrayList<>();
    graph[from].add(new Info(to, w));
    graph[to].add(new Info(from, w));
    }

    int[] distance = new int[n];
    boolean[] visited = new boolean[n];
    visited[0] = true;

    dfs(graph, 0, 0, distance, visited);

    int node = 0;
    int dist = 0;

    for (int i = 1; i < distance.length; i++) {
    if (distance[i] > dist) {
    node = i;
    dist = distance[i];
    }
    }

    distance = new int[n];
    visited = new boolean[n];
    visited[node] = true;

    dfs(graph, node, 0, distance, visited);

    int answer = 0;
    for (int d : distance)
    answer = Math.max(answer, d);

    System.out.println(answer);
    }

static class Info {
    int node;
    int w;

    public Info(int node, int w) {
        this.node = node;
        this.w = w;
    }
}

static void dfs(List<Info>[] graph, int current, int dist, int[] distance, boolean[] visited) {
    List<Info> nextList = graph[current];
    if (nextList != null) {
        for (Info info : nextList) {
            int next = info.node;
            if (visited[next]) continue;
            visited[next] = true;
            distance[next] = dist + info.w;
            dfs(graph, next, distance[next], distance, visited);
        }
    }
}
~~~
