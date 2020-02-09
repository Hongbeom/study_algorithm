## 백준 6416 - [트리인가?](https://www.acmicpc.net/problem/6416)


### 풀이법

1. 문제의 조건에 맞는 트리는 엣지의 갯수가 n-1개 이어야 하고, 어느 지점에서 어느 지점까지 도달 가능하여야 한다.
- union-find 를 이용
~~~JAVA
while (st.hasMoreTokens()) {
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());


    if (a == b && b == 0) {
        boolean check;

        if (edge == 0) {
            check = true;
        } else if (edge != node.size() - 1) {
            check = false;
        } else {
            check = check(tree, node);
        }

        ans(tc, check);
        tc++;

        node.clear();
        tree = parent.clone();
        edge = 0;
        continue root;
    } else {
        edge++;
        node.add(a);
        node.add(b);
        union(tree, a, b);
    }
}
~~~

- 인풋이 0, 0 이 들어왔을때 트리를 판단.
  - 엣지가 없는경우 - 트리라고 생각해야함.
  - 엣지의 갯수 != n-1인 경우 : 트리가 아님
  - 나머지 - 경로(root) 체크
  
~~~JAVA
static void union(int[] parent, int a, int b) {
    a = getParent(parent, a);
    b = getParent(parent, b);

    if (a < b) {
        parent[b] = a;
    } else {
        parent[a] = b;
    }

}

static boolean rootCheck(int[] parent, int a, int b) {
    a = getParent(parent, a);
    b = getParent(parent, b);
    return a == b;
}

static boolean check(int[] parent, Set<Integer> node) {
    for (int a : node) {

        for (int b : node) {
            if (a == b) {
                continue;
            }

            if (!rootCheck(parent, a, b)) {
                return false;
            }
        }
        break;
    }
    return true;
}
~~~

- set에 저장되어 있는 노드들을 활용