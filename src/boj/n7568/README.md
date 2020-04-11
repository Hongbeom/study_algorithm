## 백준 7568 - [덩치](https://www.acmicpc.net/problem/7568)

### 풀이법 

1. 서로 모두 덩치를 비교하고 랭크를 구한다.
```JAVA
for (int i = 0; i < n - 1; i++) {
    int[] p1 = bodies[i];
    for (int j = i + 1; j < n; j++) {
        int[] p2 = bodies[j];
        int c = compare(p1, p2);
        if (c == -1) {
            rank[j]++;
        } else if (c == 1) {
            rank[i]++;
        }
    }
}
```
