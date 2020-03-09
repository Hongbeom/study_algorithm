## 백준 6091 - [핑크 플로이드](https://www.acmicpc.net/problem/6091)

### 풀이법

1. 간선이 총 n(n-1)/2 개 있으므로 크루스칼 알고리즘을 이용하여 최소 스패닝 트리를 구한다.

```JAVA
int[] parent = new int[n];
for (int i = 0; i < parent.length; i++) {
    parent[i] = i;
}

PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
    @Override
    public int compare(int[] o1, int[] o2) {
        if (o1[2] > o2[2]) {
            return 1;
        } else if (o1[2] < o2[2]) {
            return -1;
        }
        return 0;
    }
});
for (int i = 0; i < n - 1; i++) {
    st = new StringTokenizer(br.readLine());
    for (int j = i + 1; j < n; j++) {
        int w = Integer.parseInt(st.nextToken());
        pq.add(new int[]{i, j, w});
    }
}

List<Integer>[] graph = new ArrayList[n];
int count = 0;

while (!pq.isEmpty()) {
    int[] current = pq.poll();

    int from = current[0];
    int to = current[1];

    if (union(parent, from, to)) {

        if (graph[from] == null) {
            graph[from] = new ArrayList<>();
        }

        if (graph[to] == null) {
            graph[to] = new ArrayList<>();
        }

        graph[from].add(to);
        graph[to].add(from);

        if (++count == n - 1) {
            break;
        }

    }
}

for (List<Integer> list : graph) {
    if (list == null) {
        continue;
    }
    Collections.sort(list);
    StringBuilder sb = new StringBuilder();
    sb.append(list.size());
    sb.append(" ");
    for (int to : list) {
        sb.append(to + 1);
        sb.append(" ");
    }
    System.out.println(sb.toString());
}
```

~~~JAVA
static int getParent(int[] parent, int a) {
    if (parent[a] == a) {
        return a;
    }

    return parent[a] = getParent(parent, parent[a]);
}

static boolean union(int[] parent, int a, int b) {
    a = getParent(parent, a);
    b = getParent(parent, b);

    if (a == b) {
        return false;
    }

    if (a < b) {
        parent[b] = a;
    } else {
        parent[a] = b;
    }

    return true;
}
~~~

