## 백준 1673 - [치킨 쿠폰](https://www.acmicpc.net/problem/1673)
 
### 풀이법
 
1. 치킨을 쿠폰으로 바꿔주고 일단 한번 시킨다음에 k - 1로 나눠준다.
 
```JAVA
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String in;
StringTokenizer st;
while ((in = br.readLine()) != null) {
    st = new StringTokenizer(in);
    long n = Long.parseLong(st.nextToken());
    long k = Long.parseLong(st.nextToken());
    n *= k;
    n -= k;
    k -= 1;
    System.out.println(n / k + 1);
}
```