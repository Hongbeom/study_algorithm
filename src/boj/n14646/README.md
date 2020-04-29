## 백준 14646 - [욱제는 결정장애야!!](https://www.acmicpc.net/problem/14646)


### 풀이법

1. 배열을 이용해 최대 스티커 체크

```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int cnt = 0;
    int ans = 0;
    boolean[] pan = new boolean[n];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < 2 * n; i++) {
        int p = Integer.parseInt(st.nextToken()) - 1;

        if (pan[p]) {
            cnt--;
        } else {
            pan[p] = true;
            cnt++;
            ans = Math.max(cnt, ans);
        }
    }

    System.out.println(ans);
}
```