## 백준 12999 - [화려한 마을3](https://www.acmicpc.net/problem/12999)


세그먼트 트리를 이용하려함. -> 시간초과 및 완전탐색보다 비효율적 -> mo's algorithm

### 초기 잘못된 풀이법

1. [N2912](../n2912) 와 비슷한 문제라고 생각해, 구간에서의 과반 후보를 O(logN)으로 검색 가능한 세그먼트 트리를 구성
- O(logN)으로 후보를 검색 후, 페인트의 밝기당 인덱스를 오름차순으로 저장하여 구간에서의 갯수를 O(logN)으로 구할 수 있는 풀이를 생각.
- 하지만, 과반의 후보이지 가장 많이 등장하는 수의 후보가 아니다.

2. 다양하게 트리를 구성해봄.
- 아예 밝기의 갯수를 저장하는 세그먼트 트리 -> 당연히 메모리 초과 및 시간초과(트리를 합칠때 배열을 n번 돌아야 한다.)
- 구간에 존재하는 밝기를 Set을 이용해 중복을 피해봄 -> 시간초과. 


### 풀이법

1. mo's algorithm 이용
- sqrt decomposition (평방 분할) 개념을 이용. series를 sqrt(n)개로 묶어서 생각한다.
- 오프라인으로 쿼리를 다룬다.
  - 쿼리를 정렬: (구간 시작 / sqrt(n)) 오름차순으로, 값이 같다면 구간 끝 값을 오름차순으로.

~~~JAVA
Arrays.sort(queries, (o1, o2) -> {
    int size = (int) Math.sqrt(n);
    if (o1[0] / size > o2[0] / size) {
        return 1;
    } else if (o1[0] / size < o2[0] / size) {
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

2. 정렬된 쿼리를 실행 -> 쿼리가 들어오면 이전 쿼리와 구간을 비교하여 실행한다.

- 이전 쿼리보다 지금 쿼리의 구간이 더 넓은만틈 그 페인트의 밝기의 갯수를 더해준다.
- 이전 쿼리보다 지금 쿼리의 구간이 더 좁은만큼 그 페인트의 밝기의 갯수를 빼준다.

~~~JAVA
static void add(int p) {

    if(COUNT[p] != 0){
        TABLE[COUNT[p]]--;
    }
    COUNT[p]++;
    TABLE[COUNT[p]]++;
    MAX = Math.max(MAX, COUNT[p]);

}

static void erase(int p) {
    TABLE[COUNT[p]]--;

    if (COUNT[p] == MAX && TABLE[COUNT[p]] == 0) {
        MAX--;
    }

    COUNT[p]--;

    TABLE[COUNT[p]]++;

}
~~~

- COUNT는 페인트 밝기의 갯수를 저장
- TABLE은 특정 갯수를 가지고 있는 페인트의 갯수를 저장.
- add 와 erase 연산중 max값을 갱신해준다. 


~~~JAVA
int preL = 0;
int preR = 0;
add(paint[0] + ADJ);
for (int[] query : queries) {
    int l = query[0];
    int r = query[1];

    // 왼쪽 추가
    for (int i = preL - 1; i >= l; i--) add(paint[i] + ADJ);

    // 오른쪽 추가
    for (int i = preR + 1; i <= r; i++) add(paint[i] + ADJ);

    // 왼쪽 삭제
    for (int i = preL; i < l; i++) erase(paint[i] + ADJ);

    // 오른쪽 삭제
    for (int i = preR; i > r; i--) erase(paint[i] + ADJ);

    preL = l;
    preR = r;
    answer[query[2]] = MAX;
}
~~~

3. 시간복잡도 -> O((N + Q) * sqrt(N))
- [참고하여 풀었다.](https://www.weeklyps.com/entry/%EB%AA%A8%EC%8A%A4-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Mos-algorithm)