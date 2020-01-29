## 백준 10815 - [숫자 카드](https://www.acmicpc.net/problem/10815)

### 풀이법

1. 이분탐색을 이용해 카드를 검색

~~~JAVA
static int binarySearch(int[] card, int start, int end, int target) {

    int mid = (start + end) / 2;

    int value = card[mid];

    if (value >= target) {
        if (mid == 0 || card[mid - 1] < target) {
            return value;
        }
        return binarySearch(card, start, mid, target);
    } else {
        if (mid == card.length - 1) {
            return card[card.length - 1];
        }

        return binarySearch(card, mid + 1, end, target);
    }

}
~~~

