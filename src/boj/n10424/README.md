## 백준 10424 - [알고리즘 기말고사](https://www.acmicpc.net/problem/10424)

### 풀이법

1. 만족도를 계산하는 공식은 결국 중간고사 등수 - 기말고사 등수이다.

~~~JAVA
int[] rank = new int[n];
StringTokenizer st = new StringTokenizer(br.readLine());
for (int i = 0; i < n; i++) {
    int r = Integer.parseInt(st.nextToken()) - 1;
    rank[r] = r - i;
}
~~~

