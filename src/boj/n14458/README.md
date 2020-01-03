## 백준 14458 - [소가 길을 건너간 이유 10](https://www.acmicpc.net/problem/14458)

처음 잘못된 접근으로 이틀동안 해맸던 문제. 결국 Solution을 보고 해결하였다...


### 초기 잘못된 접근법

1. 각 왼쪽 라인과 오른쪽 라인의 소의 번호와 위치를 이용해 나만의 weight를 만들었었다.
- weight는 weight[i]는 i - 2 * i번째 소의 건너편 위치
- weight를 순서대로 어떤 자료구조에 집어 넣어 자신보다 자신보다 작은 값이 바로 intersection의 개수
- n이 100,000 이기 때문에, 이 자료구조의 시간복잡도가 중요하다고 생각 -> bst를 변형하여 nlogn으르 뽑는 트리를 구현해보자.

2. 다시 생각해보니, weight는 필요한 자료가 아니라 그냥 i번째 위차한 소의 건너편 위치가 이 weigth를 충분히 대신할 수 있었다.
- 이 위치는 반대로 자신보다 큰 갯수를 필요로함

3. 아래서부터 이 weight를 조정해주어 계속 tree를 이용해 intersection의 최솟값을 갱신해주는 풀이법 생각
- 하지만, 너무나 당연히 n X n X nlogn이 될듯...
- 만들었던 Tree
~~~JAVA
static class HTree {
        int value;
        int more;
        boolean isRoot;

        HTree leftChild;
        HTree rightChild;

        HTree(int value, boolean isRoot) {
            this.value = value;
            this.isRoot = isRoot;
            this.more = 0;
        }

        int insert(int value) {

            HTree t = this;

            while (true) {

                if (t.value > value) {
                    if (t.leftChild == null) {
                        t.leftChild = new HTree(value, false);
                        t.leftChild.more += t.more + 1;
                        t = t.leftChild;
                        break;
                    }
                    t = t.leftChild;
                } else {
                    t.more++;
                    if (t.leftChild != null) {
                        t.leftPlus();
                    }
                    if (t.rightChild == null) {
                        t.rightChild = new HTree(value, false);
                        t.rightChild.more += t.more - 1;

                        t = t.rightChild;

                        break;
                    }
                    t = t.rightChild;
                }
            }

            return t.more;
        }


        void leftPlus() {

            Queue<HTree> queue = new LinkedList<>();
            queue.add(this.leftChild);

            while (!queue.isEmpty()) {
                HTree t = queue.poll();
                t.more++;
                if (t.leftChild != null) {
                    queue.add(t.leftChild);
                }
                if (t.rightChild != null) {
                    queue.add(t.rightChild);
                }
            }
        }
    }
~~~

- 자세히 보면, leftPlus()라는 함수에서 왼쪽자식들의 more값을 끝까지 갱신해주기 때문에 nlogn이 성립하지 않을듯 하다...

### 풀이법

1. merge sort를 이용하여 inversion counting을 이용 --> inversion 은 곧 intersection을 의미
- merge 하는 과정에서, 오른쪽에 존재하는 i가 왼쪽에 존재하는것보다 작을경우, 왼쪽에 아직 merge가 되지않은 인자의 갯수만큼 inversion

~~~JAVA
static void merge(int[] arr, int start, int middle, int end) {
    int i = start;
    int k = start;
    int j = middle + 1;
    
    while (i <= middle && j <= end) {
        if (arr[i] < arr[j]) {
            TMP[k++] = arr[i++];
        } else {
            TMP[k++] = arr[j++];
            INVERSION += middle - i + 1;
        }
    }

    while (i <= middle) {
        TMP[k++] = arr[i++];
    }

    while (j <= end) {
        TMP[k++] = arr[j++];
    }

    for (int id = start; id <= end; id++) {
        arr[id] = TMP[id];
    }
}
~~~

2. 단순히 끝에서 목초지를 맨 위로 옮기는 것이기 때문에, 건너편에 있는 위치를 활용해 맨 뒤의 목초지가 맨 앞으로 이동했을때의 intersection의 변화를 알 수 있다.
- 맨 아래가 맨 위로 이동했을때, 반대편 그 소의 목초지가 p번째에 위치하고 있다.
- 여기서 증가되는 intersection은 p - 1이 된다.
- 기존에 있었던(감소되는) intersection N-P 가 된다.

~~~JAVA
mergeCount(left.clone(), 0, left.length - 1);
long leftInter = INVERSION;
INVERSION = 0;
long answer = leftInter;

for (int k = n - 1; k > 0; k--) {
    int p = left[k] + 1;
    leftInter += p - 1 - (n - p);
    answer = Math.min(answer, leftInter);
}

mergeCount(right.clone(), 0, right.length - 1);
long rightInter = INVERSION;

for (int k = n - 1; k > 0; k--) {
    int p = right[k] + 1;
    rightInter += p - 1 - (n - p);
    answer = Math.min(answer, rightInter);
}
~~~

 
