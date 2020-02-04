## 백준 3584 - [가장 가까운 공통조상](https://www.acmicpc.net/problem/3584)

### 풀이법

1. 배열을 이용하여 각자 노드의 부모를 표시해준 후, 루트노드까지의 경로를 구한 후 단순히 비교해 주었다. 

```JAVA
int[] parent = new int[n + 1];
for (int i = 0; i < n - 1; i++) {
    st = new StringTokenizer(br.readLine());
    int p = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    parent[c] = p;
}

st = new StringTokenizer(br.readLine());

int a = Integer.parseInt(st.nextToken());
int b = Integer.parseInt(st.nextToken());


List<Integer> aPath = new ArrayList<>();
List<Integer> bPath = new ArrayList<>();

createPath(aPath, parent, a);
createPath(bPath, parent, b);

aP:
for (int pa : aPath) {
    for (int pb : bPath) {
        if (pa == pb) {
            System.out.println(pa);
            break aP;
        }
    }
}
```

~~~JAVA
static void createPath(List<Integer> list, int[] parent, int a) {

    while (a != 0) {
        list.add(a);
        a = parent[a];
    }
}
~~~

- 문제의 조건이 타이트하지 않아서 이 코드가 정답이 된것같다.
- 효율적인 LCA 알고리즘을 공부해서 다시 풀어보자.

### LCA 풀이법

1. 쿼리당 logN으로 처리가 가능한 LCA 풀이법. 근데 왜 실행시간은 위의 코드보다 느린걸까...?
- 각 노드는 자신의 깊이와 자신의 조상을 logN개씩 알고있다.
- 먼저 깊이를 맞춰준 후, 같은 높이에서 하나씩 올려가며 찾는다!
- tree 의 조상들은 이차원배열, 메모이제이션, dfs 를 통해 초기화 해준다.

~~~JAVA
static void getTree(List<Integer>[] graph, int[][] ac, int[] depth, int here, int parent) {

    depth[here] = depth[parent] + 1;

    ac[here][0] = parent;

    for (int i = 1; i < ac[0].length; i++) {
        int tm = ac[here][i - 1];
        ac[here][i] = ac[tm][i - 1];
    }

    if (graph[here] != null) {

        for (int there : graph[here]) {
            if (there != parent) {
                getTree(graph, ac, depth, there, here);
            }
        }
    }

}
~~~

- dfs로 트리를 초기화. 조상들을 기억해 놓는다.

~~~JAVA
if (depth[a] != depth[b]) {
    if (depth[a] > depth[b]) {
        // b를 올려주기 위해서
        int tm = a;
        a = b;
        b = tm;
    }

    for (int id = ac[0].length - 1; id >= 0; id--) {
        if (depth[a] <= depth[ac[b][id]]) {
            b = ac[b][id];
            if (depth[a] == depth[ac[b][id]]) {
                break;
            }
        }
    }
}
~~~

- 먼저 a 와 b의 높이를 맞춰준다.

~~~JAVA
int lca = a;
if (a != b) {
    for (int id = ac[0].length - 1; id >= 0; id--) {
        if (ac[a][id] != ac[b][id]) {
            a = ac[a][id];
            b = ac[b][id];
        }
        lca = ac[a][id];
    }
}
~~~
- 위에서부터 같은 조상들을 찾는다.