## 백준 1039 - [교환](https://www.acmicpc.net/problem/1039)

### 초기 잘못된 접

1. 바꿀수 있는 모든 자리의 수를 2차원 배열으로 생성 - nC2 ^ k개가 나올수 있음.

```JAVA
static List<int[]> getChanges(int n) {
    List<int[]> changes = new ArrayList<>();
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            changes.add(new int[]{i, j});
        }
    }

    return changes;
}

static void getCandidates(List<int[][]> candidates, int[][] tmp, List<int[]> changes, int start, int k) {

    if (start == k) {
        candidates.add(tmp);
        return;
    }

    for (int[] change : changes) {
        tmp[start] = change;
        getCandidates(candidates, clone(tmp), changes, start + 1, k);
    }
}
```
- 각각의 경우에 대하여 swap을 완료한 뒤 max값을 계속 갱신하는 방법으로 진행.
- nC2 ^ k 개의 경우가 존재함. n은 쵀대 1,000,000, k는 최대 10 이므로 너무나 많은 경우 -> 메모리 초과

### 풀이법

1. k번을 swap 한다 -> 1 부터 k 까지의 각각의 step에 동일한 수가 등장 필요가 없다. -> 각 step에서의 visited 이용 BFS 풀이
2. Node 클래스를 만들어 step과 현재 swap상태를 표시

```JAVA
static class Node {

    char[] number;
    int step;

    Node(char[] number, int step) {
        this.number = number;
        this.step = step;
    }
}
```

3. Queue를 이용한 BFS, 새로 증가된 step이 등장할때 visited 배열 초기화.
~~~JAVA
while (!queue.isEmpty()) {

    Node current = queue.poll();
    int step = current.step;

    if (cnt + 1 == step) {
        cnt++;
        Arrays.fill(visited, false);
    }

    if (step == k) {
        answer = Math.max(answer, getValue(current.number));
        continue;
    }

    for (int[] change : changes) {

        char[] changed = swap(current.number, change);

        if (changed == null) {
            continue;
        }

        int value = getValue(changed);

        if (visited[value]) {
            continue;
        }

        visited[value] = true;
        queue.add(new Node(changed, step + 1));
    }

}
~~~