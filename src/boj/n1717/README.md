## 백준 1717 - [집합의 표현](https://www.acmicpc.net/problem/1717)

### 풀이법

1. union find 이용 root(parent)로 합집합 판별.

~~~JAVA
for (int i = 0; i < m; i++) {
    st = new StringTokenizer(br.readLine());

    int q = Integer.parseInt(st.nextToken());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());

    if (q == 0) {
        union(parent, a, b);
    } else {
        if(check(parent, a, b)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}
~~~

~~~JAVA
static int getParent(int[] parent, int a) {
    if (parent[a] == a) {
        return a;
    }

    return parent[a] = getParent(parent, parent[a]);
}

static void union(int[] parent, int a, int b) {
    a = getParent(parent, a);
    b = getParent(parent, b);

    if (a < b) {
        parent[b] = a;
    } else {
        parent[a] = b;
    }
}

static boolean check(int[] parent, int a, int b) {
    a = getParent(parent, a);
    b = getParent(parent, b);

    return a == b;
}
~~~

