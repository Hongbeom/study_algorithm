## 백준 1813 - [마지막 한마디](https://www.acmicpc.net/problem/1813)

### 풀이법

1. n개가 참이다 라는개 n개 있는것들 중 최댓값을 구한다.
~~~JAVA
for (int i = 0; i < n; i++) {
    int c = Integer.parseInt(st.nextToken());
    check[c]++;
}

for (int i = 0; i < check.length; i++) {
    if (i == check[i]) {
        ans = Math.max(ans, i);
    }
}
~~~
