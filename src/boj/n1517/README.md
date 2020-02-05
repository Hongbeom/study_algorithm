## 백준 1517 - [버블 소트](https://www.acmicpc.net/problem/1517)


### 풀이법

1. 머지소트를 이용해 인버전 카운팅.

```JAVA
static void merge(int left, int right, int mid) {

    int lid = left;
    int tid = left;
    int rid = mid + 1;

    while (lid <= mid && rid <= right) {
        if (arr[lid] <= arr[rid]) {
            tmp[tid++] = arr[lid++];
        } else {
            ans += (mid - lid) + 1;
            tmp[tid++] = arr[rid++];
        }
    }

    while (lid <= mid) {
        tmp[tid++] = arr[lid++];
    }


    while (rid <= right) {
        tmp[tid++] = arr[rid++];
    }

    for (int i = left; i <= right; i++) {
        arr[i] = tmp[i];
    }

}

static void mergeSort(int left, int right) {

    if (left != right) {
        int mid = (left + right) / 2;

        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, right, mid);

    }

}
```
