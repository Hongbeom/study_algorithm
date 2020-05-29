## 백준 3973 - [Time To Live](https://www.acmicpc.net/problem/3973)

### 초기 잘못된 접근법

1. TTL을 구하기 위해 다익스트라를 사용.
  - 0을 루트로 잡고, height가 절반이 되는 지점에서 시행하면 된다고 생각 -> 반례존재
  - 모든 지점에 대해서 다익스트라를 사용하면 N^2logN -> N이 최대 100,000 이므로 시간초과.

### 풀이법

1. 0을 루트로 하는 트리를 구성.
2. height가 가장 큰 leaf를 구한다.
2. 그 leaf에서 다른 지점까지의 거리를 완전탐색으로 구한 뒤, 그 거리의 절반이 답.

~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;

    for (int tc = 0; tc < TC; tc++) {
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new ArrayList[n];
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

        boolean[] visited = new boolean[graph.length];
        visited[0] = true;
        int[] leaf = getMaxHeightAndId(graph, 0, 0, visited);

        visited = new boolean[graph.length];
        visited[leaf[1]] = true;

        int length = search(graph, leaf[1], 0, visited);
        if(length % 2 == 1){
            length = length / 2 + 1;
        }else{
            length = length / 2;
        }
        System.out.println(length);
    }

}

static int[] getMaxHeightAndId(List<Integer>[] graph, int id, int height, boolean[] visited) {
    int[] result = new int[]{height, id};

    for (int next : graph[id]) {
        if (visited[next]) {
            continue;
        }
        visited[next] = true;

        int[] tmp = getMaxHeightAndId(graph, next, height + 1, visited);
        if(tmp[0] > result[0]){
            result = tmp;
        }
    }

    return result;
}

static int search(List<Integer>[] graph, int id, int step, boolean[] visited){
    int cnt = step;
    for(int next : graph[id]){
        if(visited[next]){
            continue;
        }
        visited[next] = true;
        cnt = Math.max(cnt, search(graph, next, step + 1, visited));
    }
    return cnt;
}
~~~