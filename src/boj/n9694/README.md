## 백준 9694 - [무엇을 아느냐가 아니라 누구를 아느냐가 문제다](https://www.acmicpc.net/problem/9694)

### 풀이법

1. 다익스트라 이용 최단거리.
2. 노드에 최단거리의 before 저장 후 경로를 구함.

~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;

    for (int tc = 1; tc <= TC; tc++) {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]>[] graph = new ArrayList[m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            if (graph[x] == null) {
                graph[x] = new ArrayList<>();
            }

            if (graph[y] == null) {
                graph[y] = new ArrayList<>();
            }
            graph[x].add(new int[]{y, z});
            graph[y].add(new int[]{x, z});
        }
        answer(tc, dijkstra(graph));
    }


}

static void answer(int tc, int[] before) {
    StringBuilder sb = new StringBuilder();
    sb.append("Case #");
    sb.append(tc);
    sb.append(": ");
    if (before == null) {
        sb.append(-1);
    } else {
        getPath(before, before.length - 1, sb);
    }
    System.out.println(sb.toString());
}

static void getPath(int[] before, int current, StringBuilder sb){
    if(current == 0){
        sb.append(current);
        sb.append(' ');
        return;
    }
    getPath(before, before[current], sb);
    sb.append(current);
    if(current != before.length - 1){
        sb.append(' ');
    }
}


static int[] dijkstra(List<int[]>[] graph) {
    int[] distance = new int[graph.length];
    int[] before = new int[graph.length];
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(before, -1);
    distance[0] = 0;


    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1[1] < o2[1]) {
            return -1;
        } else if (o1[1] > o2[1]) {
            return 1;
        }
        return 0;
    });

    pq.add(new int[]{0, 0});

    while (!pq.isEmpty()) {
        int[] current = pq.poll();

        int currentNode = current[0];
        int currentDist = current[1];

        if (distance[currentNode] < currentDist) {
            continue;
        }

        if (graph[currentNode] != null) {
            for (int[] next : graph[currentNode]) {
                int nextNode = next[0];
                int nextDist = next[1];

                if (distance[currentNode] + nextDist < distance[nextNode]) {
                    distance[nextNode] = distance[currentNode] + nextDist;
                    before[nextNode] = currentNode;
                    pq.add(new int[]{nextNode, distance[nextNode]});
                }
            }
        }
    }

    if (distance[distance.length - 1] == Integer.MAX_VALUE) {
        return null;
    }

    return before;
}
~~~

