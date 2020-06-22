## 백준 14908 - [구두 수선공](https://www.acmicpc.net/problem/14908)

### 풀이법

1. Greedy - Exchange Argument
  - 임의의 상태에서 매 순간 손해보지 않으면서 원소끼리의 교환을 통해 최적의 해를 구한다.
2. 어떤 일의 배치던간에 완료시간은 동일하다.
3. 만약 i번째 일과 i + 1 번째 일의 배치를 바꿔보자.
  - i - 1 번째까지의 작업의 보상금은 두가지 경우 서로 같다.
  - i + 2 부터 마지막 작업까지의 보상금도 두가지 경우 서로 같다.
  - 다른 값은 서로의 보상금 차이.
  - 보상금에 대해 정렬을 수행한다.

```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st;

    int[][] taskInfos = new int[n][3];

    for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        taskInfos[i][0] = Integer.parseInt(st.nextToken());
        taskInfos[i][1] = Integer.parseInt(st.nextToken());
        taskInfos[i][2] = i + 1;
    }
    Arrays.sort(taskInfos, (o1, o2) -> {
        if (o1[0] * o2[1] < o2[0] * o1[1]) {
            return -1;
        } else if (o1[0] * o2[1] > o2[0] * o1[1]) {
            return 1;
        } else {
            if (o1[2] < o2[2]) {
                return -1;
            } else if (o1[2] > o2[2]) {
                return 1;
            }
        }
        return 0;
    });

    StringBuilder ans = new StringBuilder();
    for (int i = 0; i < taskInfos.length; i++) {
        ans.append(taskInfos[i][2]);
        if (i != taskInfos.length - 1) {
            ans.append(' ');
        }
    }
    System.out.println(ans.toString());
}
```
