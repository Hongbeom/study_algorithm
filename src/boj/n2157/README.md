## 백준 2157 - [여행](https://www.acmicpc.net/problem/2157)

초기에 bfs로 하였지만 시간초과... DP도 많이 헤맴.

### 풀이법 

1. x도시에서 y로가는 항공편중 제일 기내식이 맛있는 항공편만 사용한다.
- y가 x보다 큰 경우에만 선택.
- taste[x][y] 에 저장.

~~~JAVA
int[][] taste = new int[n + 1][n + 1];

for (int i = 0; i < k; i++) {
    st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    if (b > a) {
        taste[a][b] = Math.max(taste[a][b], Integer.parseInt(st.nextToken()));
    }
}
~~~

2. memo[city][count] 배열을 이용하여 메모이제이션 수행
- city 현재 도착한 도시, count는 1번도시에서 city로 왔을때 이용한 항공편의 갯수.
- memo[city][count] = 0 이면, count번 항공편을 이용해서 현재 city로 올 수 없다는 의미.
- count 가 1일때 -> 1번도시에서 해당 city로의 항공편이 존재할때 초기화
- count 가 city보다 크거나 같을때 -> 자신보다 수가 적은 도시로 이동할 수 없으므로 당연히 0
- memo[city][count] = Max(memo[city][count], memo[middle][count - 1] + taste[middle][city])
  - middle은 city보다 수가 적은 도시
  - memo[middle][count - 1]과 taste[middle][city] 둘중 하나라도 0 이라면 초기화를 시켜주지 않는다.

```JAVA
int answer = 0;
for (int city = 1; city <= n; city++) {
    for (int count = 1; count < m; count++) {
        if (count >= city) {
            continue;
        }
        if (count == 1) {
            if (taste[1][city] != 0) {
                memo[city][count] = taste[1][city];
            }
        } else {
            for (int middle = 2; middle < city; middle++) {
                if (memo[middle][count - 1] != 0 && taste[middle][city] != 0) {
                    memo[city][count] = Math.max(memo[city][count], memo[middle][count - 1] + taste[middle][city]);
                }
            }
        }

        if (city == n) {
            answer = Math.max(answer, memo[city][count]);
        }
    }
}
```

- 무조건 1번으로 시작해 n번 도시로 도착해야 하므로 city 가 n일때의 최댓값을 찾는다.
