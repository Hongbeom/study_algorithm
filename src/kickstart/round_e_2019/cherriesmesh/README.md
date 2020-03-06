## kick start 2019 round E - [Cherries Mesh](https://codingcompetitions.withgoogle.com/kickstart/round/0000000000050edb?show=progress)

### 풀이법

1. black strand는 길이가 1인 간선, red strand는 길이가 2인 간선.
2. 현재 체리들은 모두 연결되어 있다.
3. black strands로 최소 스패닝 트리를 만들면 된다 - union find 이용
4. 나머지 간선은 red로 채우면 된다.

~~~JAVA
boolean check = false;
st = new StringTokenizer(br.readLine());

// number of cherries
int n = Integer.parseInt(st.nextToken());

// number of black strands
int m = Integer.parseInt(st.nextToken());

int[] parent = new int[n];

for (int p = 0; p < n; p++) {
    parent[p] = p;
}

int bCount = 0;

for (int i = 0; i < m; i++) {
    if (check) {
        br.readLine();
        continue;
    }
    st = new StringTokenizer(br.readLine());

    int from = Integer.parseInt(st.nextToken()) - 1;
    int to = Integer.parseInt(st.nextToken()) - 1;
    if (union(parent, from, to)) {
        if (++bCount == n - 1) {
            check = true;
        }
    }

}
~~~

```JAVA
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
```