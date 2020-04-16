## 백준 2381 - [최대 거리](https://www.acmicpc.net/problem/2381)

### 풀이법

1. 좌표(x, y)의 합(x + y)에서 최대, 최소의 간격, 차(x - y)에서 최대, 최소의 간격의 최댓값. 
```JAVA
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st;
    int pMax = Integer.MIN_VALUE;
    int pMin = Integer.MAX_VALUE;

    int sMax = Integer.MIN_VALUE;
    int sMin = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        pMax = Math.max(pMax, x + y);
        pMin = Math.min(pMin, x + y);

        sMax = Math.max(sMax, x - y);
        sMin = Math.min(sMin, x - y);
    }

    System.out.println(Math.max(Math.abs(pMax - pMin), Math.abs(sMax - sMin)));

}
```

