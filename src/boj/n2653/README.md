## 백준 2653 - [안정된 집단](https://www.acmicpc.net/problem/2653)

### 풀이법

1. 2명 이상의 소집단이 서로 좋아하고, 다른 사람들은 모두 싫어해야 한다.
2. 결국, 소집단을 형성하려면 똑같은 형태를 지니고 있어야 한다. -> 인접행렬이 같아야 한다.

```JAVA
boolean[] visited = new boolean[n];
List<List<Integer>> answer = new ArrayList<>();
for (int i = 0; i < n; i++) {
    if (visited[i]) {
        continue;
    }
    List<Integer> can = new ArrayList<>();
    can.add(i + 1);
    visited[i] = true;
    for (int j = 0; j < n; j++) {
        if (i == j) {
            continue;
        }
        if (visited[j]) {
            continue;
        }
        if (Arrays.equals(relations[i], relations[j])) {
            visited[j] = true;
            can.add(j + 1);
        }
    }
    if (can.size() == 1) {
        System.out.println(0);
        return;
    } else {
        answer.add(can);
    }
}

System.out.println(answer.size());
for (List<Integer> list : answer) {
    for (int a : list) {
        System.out.print(a + " ");
    }
    System.out.println();
}
~~~

