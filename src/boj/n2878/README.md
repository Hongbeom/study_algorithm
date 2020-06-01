## 백준 2878 - [캔디캔디](https://www.acmicpc.net/problem/2878)

솔루션을 보고 해결한 문제.


### 초기 잘못된 접근

1. 정렬 후, 제일 원하는 애들부터 분배한다. -> 반례가 너무 많다.


### 풀이법

1. 문제가 원하는 것은 골고루 분배하여 화의 최솟값을 구한는 것.
2. 몇개가 부족한지 먼저 알고 있음. -> SUM - M
3. 그렇다면, 이 부족한 것을 얼마나 고르게 분배하면 되는 문제.
  - 원하는 갯수가 적은순으로 배열을 정렬.
  - 이 사람에게 부족한 캔디수를 주는 것은 min(남은 부족한 캔디 수 / 남은 사람, 원하는 캔디)

~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int m = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    int[] wants = new int[n];

    long sum = 0;
    for (int i = 0; i < wants.length; i++) {
        wants[i] = Integer.parseInt(br.readLine());
        sum += wants[i];
    }
    sum -= m;
    Arrays.sort(wants);
    long answer = 0;

    for(int i = 0; i < wants.length; i++){
        long w = Math.min(wants[i], sum / (wants.length - i));
        answer += w * w;
        sum -= w;
    }

    System.out.println(answer);
}
~~~
