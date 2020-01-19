## 백준 3745 - [오름세](https://www.acmicpc.net/problem/3745)

LIS 문제, O(N^2)의 풀이로는 시간초과

### 풀이법

1. LIS - O(NlogN) 풀이 - 리스트를 이용
- 리스트가 비어있거나, 맨 뒤의 원소가 비교하려는 원소보다 작다면 리스트의 맨 뒤에 추가.
- 아니라면 lower_bound에 set.
~~~JAVA
for (int i = 0; i < n; i++) {
    price[i] = Integer.parseInt(st.nextToken());
    if (i == 0) {
        lis.add(price[i]);
    } else {
        if (lis.get(lis.size() - 1) < price[i]) {
            lis.add(price[i]);
        } else {
            int id = getLowerBound(lis, 0, lis.size() - 1, price[i]);
            lis.set(id, price[i]);
        }

    }
}
~~~
```JAVA
static int getLowerBound(List<Integer> list, int start, int end, int key) {

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
```
