## 백준 5813 - [이상적인 도시](https://www.acmicpc.net/problem/5813)

어려웠던 문제. 구현이 쉽지 않았다.

### 초기 잘못된 접근법

1. 인풋으로 받은 좌표들을 x, y 기준으로 정렬 후 인접리스트를 생성.
2. 인접 리스트 생성 후 bfs로 모든 최단 거리들을 더해주었다. -> 당연히 시간초과
3. 메모이제이션을 사용하려 했지만 실패... 조건이 많았다.
4. 다 풀었지만 자료형을 잘못 선택하여 실패...
    - 50000 * 50000 는 25억, 32비트 오버플로우 당연... 10억으로 나눈 나머지에 속지말자...

### 풀이법 

1. 인풋받은 이상적인 도시에는 구멍이 없다.
2. x값이 같고, y값이 연속되는 좌표들을 하나로 묶어 node라고 생각한다.
3. node의 x축으로 위, 아래로 인접해 있는 노드들은 연결되어 있다고 할 수 있다 -> 간선이 존재한다 (길이는 1).
4. 이상적인 도시에는 구멍이 존재하지 않기 때문에 이 노드들은 트리를 구성하게 된다.
5. 서로 다른 노드 사이의 거리는 노드의 한 좌표에서 다른 노드의 한 좌표로 갈때의 x값의 이동 횟수 이다.
6. 노드에는 여러개의 좌표가 존재하므로, 결국 모든 x값의 이동 횟수 서로다른 모든 노드 a, b에 대하여
  - 노드 a 의 좌표의 갯수 * 노드 b의 좌표의 갯수 * 노드 a와 b 사이의 거리.
7. y축을 기준으로도 똑같이 y값의 이동횟수 전체를 구할 수 있다.
8. 모든 x와 y의 이동횟수를 더해주면 정답.


#### JAVA 코드

1. 먼저 x -> y 에 대해 오름차순으로 정렬.
~~~JAVA
Arrays.sort(spot, (o1, o2) -> {
    if (o1[0] > o2[0]) {
        return 1;
    } else if (o1[0] < o2[0]) {
        return -1;
    } else {
        if (o1[1] > o2[1]) {
            return 1;
        } else if (o1[1] < o2[1]) {
            return -1;
        }
    }
    return 0;
});
~~~

2. 정렬된 x 값을 기준으로 y값에 쉽게 접근하기 위해여 ranges를 만듬.
~~~JAVA
int compare = -1;
int cid = -1;

List<int[]> ranges = new ArrayList<>();
for (int i = 0; i < n; i++) {
    if (spot[i][0] != compare) {
        compare = spot[i][0];
        if (i != 0) {
            ranges.add(new int[]{cid, i - 1});
        }
        cid = i;
    }
    if (i == n - 1) {
        ranges.add(new int[]{cid, i});
    }
}
~~~

3. 트리를 구성한다.

~~~JAVA
static class Node {
    int id;
    int from;
    int to;
    long count;

    public Node(int id, int from, int to, long count) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.count = count;
    }

    boolean check(Node node) {
        return this.from <= node.to && node.from <= this.to;
    }
}
~~~

~~~JAVA
int tid = 0;
int lastStart = 0;
int lastEnd = 0;

List<Integer>[] graph = new ArrayList[n];
List<Node> nodeList = new ArrayList<>();
for (int[] range : ranges) {
    int w = 0;
    int size = nodeList.size();
    int count = 0;

    for (int i = range[0]; i <= range[1]; i++) {

        int[] s = spot[i];


        if (range[0] == range[1]) {
            Node node = new Node(tid, s[1], s[1], 1);
            nodeList.add(node);
            for (int id = lastStart; id < lastEnd; id++) {
                if (nodeList.get(id).check(node)) {
                    if (graph[id] == null) {
                        graph[id] = new ArrayList<>();
                    }
                    if (graph[tid] == null) {
                        graph[tid] = new ArrayList<>();
                    }
                    graph[id].add(tid);
                    graph[tid].add(id);
                }
            }
            tid++;
            break;
        }


        if (i == range[0]) {
            w = s[1];
            count = 1;
        } else {

            if (s[1] - w == 1) {
                count++;
            } else {
                Node node = new Node(tid, w - count + 1, w, count);
                nodeList.add(node);
                for (int id = lastStart; id < lastEnd; id++) {
                    if (nodeList.get(id).check(node)) {

                        if (graph[id] == null) {
                            graph[id] = new ArrayList<>();
                        }
                        if (graph[tid] == null) {
                            graph[tid] = new ArrayList<>();
                        }
                        graph[id].add(tid);
                        graph[tid].add(id);
                    }
                }
                tid++;
                count = 1;
            }

            w = s[1];

            if (range[1] == i) {

                Node node = new Node(tid, w - count + 1, w, count);
                for (int id = lastStart; id < lastEnd; id++) {
                    if (nodeList.get(id).check(node)) {

                        if (graph[id] == null) {
                            graph[id] = new ArrayList<>();
                        }
                        if (graph[tid] == null) {
                            graph[tid] = new ArrayList<>();
                        }

                        graph[id].add(tid);
                        graph[tid].add(id);
                    }

                }
                nodeList.add(node);
                tid++;
                count = 1;

            }
        }
    }
    lastStart = tid - (nodeList.size() - size);
    lastEnd = tid;
}
~~~

- 먼저 해당 range에서 y값이 연속되는 count를 사용하여 노드로 만들어 준다.
- node를 생성한 뒤, 바로 앞의 range에서 만든 노드들을 탐색하여 y값이 겹치는 노드와 간선을 만들어준다.
- 간선은 graph 에 저장.

4. x의 변화량(이동횟수)을 구한다.

- 초기에는 bfs로 각 노드사이의 거리들을 모두 구해주었다 -> 하지만 시간초과.
- 생성한 간선을 기준으로 전체 이동횟수를 구하는게 가능하다.
    - 한 간선을 제거 했을때, 두개의 서브트리를 얻을 수 있다.
    - 두개의 서브트리의 count(좌표의 갯수)의 합의 곱은 결국 이 간선을 선택하여 다른 좌표로 이동하는 횟수이다.
    - 각 간선마다 이 값을 구해서 모두 더해준다.
    - dfs를 재귀적으로 이용하여 간선의 갯수 O(N)으로 계산할 수 있다.
    
~~~JAVA
static long dfs(List<Node> nodeList, List<Integer>[] graph, int current, int n, boolean[] visited) {

    visited[current] = true;

    int countSum = 0;
    if (graph[current] != null) {

        for (int next : graph[current]) {

            if (visited[next]) {
                continue;
            }

            long a = dfs(nodeList, graph, next, n, visited);
            answer =  (answer + ((a * (n - a))% 1000000000)) % 1000000000;
            countSum += a;

        }

    }

    if (countSum == 0) {
        return nodeList.get(current).count;
    } else {
        return countSum + nodeList.get(current).count;
    }


}
~~~

5. 반대로 똑같이 정렬하여 답을 구하면 됨.

