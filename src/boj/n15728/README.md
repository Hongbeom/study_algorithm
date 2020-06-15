## 백준 15728 - [에리 - 카드](https://www.acmicpc.net/problem/15728)

### 풀이법

1. N의 범위가 1 ~ 100 이므로 모든 결과를 도출하여 k번째로 큰 결과를 뽑는다.

```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int[] share = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
        share[i] = Integer.parseInt(st.nextToken());
    }

    int[] team = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
        team[i] = Integer.parseInt(st.nextToken());
    }

    int[] result = new int[n];
    for (int i = 0; i < team.length; i++) {
        int t = team[i];
        int sMax = Integer.MIN_VALUE;
        for (int s : share) {
            sMax = Math.max(sMax, s * t);
        }
        result[i] = sMax;
    }
    Arrays.sort(result);
    System.out.println(result[result.length - 1 - k]);
}
```
