## 백준 5557 - [1학년](https://www.acmicpc.net/problem/5557)

### 풀이법 

1. 자리의 수가 100개 이므로 brute force 당연히 시간초과.
2. 수의 범위가 0 이상 20 이하 이므로 dp
  - 현재까지 계산된 값의 count를 2차원 배열에 저장.
  - 각 스텝마다 이전의 결과에서 minus, plus한 결과를 저장.
  - memo[i][j] = memo[i - number][j - 1] + memo[i + number][j - 1];
    
~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] numbers = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
        numbers[i] = Integer.parseInt(st.nextToken());
    }

    long[][] memo = new long[21][numbers.length - 1];
    memo[numbers[0]][0] = 1;
    for (int j = 1; j < memo[0].length; j++) {
        int number = numbers[j];
        for (int i = 0; i < memo.length; i++) {
            long plusCnt = 0;
            long minusCnt = 0;
            if(i - number >= 0){
                plusCnt = memo[i - number][j - 1];
            }
            if(i + number <= 20){
                minusCnt = memo[i + number][j - 1];
            }
            memo[i][j] = plusCnt + minusCnt;
        }
    }

    System.out.println(memo[numbers[n - 1]][numbers.length - 2]);
}
~~~