## 백준 4195 - [친구 네트워크](https://www.acmicpc.net/problem/4195)

### 풀이법

1. 친구 관계가 최대 100,000개 이므로 사람의 최대값은 200,000으로 설정.
2. hash map을 이용하여 사람과 인덱스 매핑.
3. union-find 알고리즘 이용하여 같은 root가 같은 사람을 count

```JAVA
static int countRoot(int[] parent, int root, int size) {

    int cnt = 0;

    for (int i = 0; i <= size; i++) {
        if (getParent(parent, i) == root) {
            cnt++;
        }
    }

    return cnt;

}

static int union(int[] parent, int a, int b) {
    a = getParent(parent, a);
    b = getParent(parent, b);

    if (a < b) {
        parent[b] = a;
        return a;
    } else {
        parent[a] = b;
        return b;
    }
}
```

- root가 같은 사람을 다 탐색하기 때문에 매우 느린 속도.

4. count 배열을 이용하여 union시 count 갱신

```JAVA
static int union(int[] parent, int a, int b, int[] count) {
    a = getParent(parent, a);
    b = getParent(parent, b);

    if (a < b) {
        parent[b] = a;
        count[a] += count[b];
        return count[a];
    } else if(a > b){
        parent[a] = b;
        count[b] += count[a];
        return count[b];
    } else{
        return count[a];
    }
}
```