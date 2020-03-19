## 백준 4435 - [중간계 전쟁](https://www.acmicpc.net/problem/4435)

### 풀이법 

1. 간단한 구현 문제


```JAVA
for (int tc = 1; tc <= T; tc++) {
    int g = 0;
    st = new StringTokenizer(br.readLine());
    for (int value : GANDALF) {
        g += Integer.parseInt(st.nextToken()) * value;
    }
    int s = 0;
    st = new StringTokenizer(br.readLine());
    for (int value : SAURON) {
        s += Integer.parseInt(st.nextToken()) * value;
    }

    if (g > s) {
        ans(tc, -1);
    } else if (g < s) {
        ans(tc, 1);
    } else {
        ans(tc, 0);
    }

}
```
