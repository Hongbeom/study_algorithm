## 백준 2757 - [엑셀](https://www.acmicpc.net/problem/2757)

### 풀이법

1. 26진법으로 변환. 0이 가르키는게 없으므로, 각각 나눠줄때 1을 빼준다.

```JAVA
static StringBuilder make(int c) {
    StringBuilder sb = new StringBuilder();
    c -= 1;
    while (c >= 0) {
        sb.append((char) ((c % 26) + 65));
        c /= 26;
        c -= 1;
    }
    return sb.reverse();
}
```

