## 백준 11438 - [LCA 2](https://www.acmicpc.net/problem/11438)

logN으로 쿼리를 처리하는 LCA 알고리즘 문제

### 풀이법
1. LCA 알고리즘 이용 풀이

~~~JAVA
static void getTree(int here, int parent) {
    // 먼저 depth 설정
    depth[here] = depth[parent] + 1;

    // 2^0 번째 조상을 바로위로 선정
    ac[here][0] = parent;

    // 조상들을 초기화 해줌.
    for (int i = 1; i < ac[0].length; i++) {
        int tmp = ac[here][i - 1];
        ac[here][i] = ac[tmp][i - 1];
    }


    // dfs로 트리를 계속 만든다.
    if (graph[here] != null) {
        for (int there : graph[here]) {
            if (there != parent) {
                getTree(there, here);
            }
        }
    }
}
~~~

- dfs 를 이용해 각 노드와 depth를 초기화
- 조상들을 dp로 초기화

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

- 쿼리의 a와 b를 같은 깊이로 맞춰준다.

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

- 위에서부터 같은 조상을 찾는다.