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
