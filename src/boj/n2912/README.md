## 백준 2912 - [백설공주와 난쟁이](https://www.acmicpc.net/problem/2912)


많이 해맸던 문제... 결국 솔루션을 보고 풀이했다.

### 초기 잘못된 접근

1. 구간에 대한 문제 -> 세그먼트 트리를 이용하자.
2. 구간에 대해 모자의 갯수를 저장하는 세그먼트 트리를 구성.
~~~JAVA
static class SegmentTree {
    int[][] tree;
    int c;
    int[] zero;

    SegmentTree(int[] arr, int c) {
        this.c = c;
        this.zero = new int[c];
        tree = new int[arr.length * 4][c];

        init(arr, 1, 0, arr.length - 1);

    }

    private int[] init(int[] arr, int node, int left, int right) {

        if (left == right) {
            tree[node][arr[left]]++;
            return tree[node];
        }

        int mid = (left + right) / 2;
        int[] leftChild = init(arr, node * 2, left, mid);

        for (int i = 0; i < c; i++) {
            tree[node][i] += leftChild[i];
        }

        int[] rightChild = init(arr, node * 2 + 1, mid + 1, right);

        for (int i = 0; i < c; i++) {
            tree[node][i] += rightChild[i];
        }

        return tree[node];
    }

    int[] query(int node, int left, int right, int a, int b) {

        if (a > right || b < left) {
            return zero;
        } else if (a <= left && right <= b) {
            return tree[node];
        }
        int mid = (left + right) / 2;

        int[] result = zero.clone();

        int[] leftResult = query(node * 2, left, mid, a, b);
        int[] rightResult = query(node * 2 + 1, mid + 1, right, a, b);

        for (int i = 0; i < c; i++) {
            result[i] += leftResult[i] + rightResult[i];
        }


        return result;
    }

}
~~~

- 모자의 갯수 c가 최대 10,000 개가 나오므로 당연히 메모리 초과...
- 또한, segment tree를 합칠때 c 만큼의 시간복잡도가 추가된다.

### 풀이법

1. 어떤 구간에 대해 그 구간에서 가장 많이 나오는 수를 Segment Tree로 저장할 수 있다.
- 어떤 구간에서 서로 다른 수를 2개씩 지워나간다면 남는 수는 그 구간에서 가장 많이 존재하는 수 이다.
- 이를 이용해 다음과 같이 세그먼트 트리를 구성.
~~~JAVA
private int[] init(int[] arr, int node, int left, int right) {

    if (left == right) {
        tree[node][0] = arr[left];
        tree[node][1]++;
        return tree[node];
    }

    int mid = (left + right) / 2;
    int[] leftChild = init(arr, node * 2, left, mid);
    int[] rightChild = init(arr, node * 2 + 1, mid + 1, right);

    if (leftChild[0] == rightChild[0]) {
        tree[node][0] = leftChild[0];
        tree[node][1] = leftChild[1] + rightChild[1];

    } else {

        if (leftChild[1] > rightChild[1]) {
            tree[node][0] = leftChild[0];
            tree[node][1] = leftChild[1] - rightChild[1];
        } else {
            tree[node][0] = rightChild[0];
            tree[node][1] = rightChild[1] - leftChild[1];
        }

    }

    return tree[node];
}
~~~
2. 그 구간에서 가장 많이 나오는 수를 O(logN)을 통해서 알 수 있으므로, 그 구간에서 그 수에 대한 과반을 체크한다.
- 초기에는 이 과정을 간단하게 O(N)으로 처리 -> 시간초과.
- logN 으로 구하는 방식에 대해 의문을 가짐.
3. 과반을 체크하는 방법은 따로 색에 대해 인덱스를 오름차순으로 저장하고 있는 리스트를 이용하여 logN으로 구한다.
- 리스트에 대해 lower bound, upper bound를 이용해 appearance 를 logN으로 구한다.

~~~JAVA
static int getLowerBound(ArrayList<Integer> list, int start, int end, int key) {

    int mid = (start + end) / 2;
    int current = list.get(mid);

    if (current >= key) {
        if (mid == 0 || list.get(mid - 1) < key) {
            return mid;
        }
        return getLowerBound(list, start, mid, key);
    } else {
        return getLowerBound(list, mid + 1, end, key);
    }

}

static int getUpperBound(ArrayList<Integer> list, int start, int end, int key) {

    if (list.get(list.size() - 1) <= key) {
        return list.size();
    }

    int mid = (start + end) / 2;
    int current = list.get(mid);

    if (current > key) {
        if (mid == 0 || list.get(mid - 1) <= key) {
            return mid;
        }
        return getUpperBound(list, start, mid, key);
    } else {
        return getUpperBound(list, mid + 1, end, key);
    }

}
~~~