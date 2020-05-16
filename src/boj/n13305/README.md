## 백준 13305 - [주유소](https://www.acmicpc.net/problem/13305)

### 풀이법

1. 제일 싼 가격을 만나면 그 가격으로 계속 기름을 사주면 된다.

~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    int[] distance = new int[n - 1];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < distance.length; i++) {
        distance[i] = Integer.parseInt(st.nextToken());
    }

    int[] price = new int[n];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < distance.length; i++) {
        price[i] = Integer.parseInt(st.nextToken());
    }
    int p = price[0];

    long answer = 0;
    for (int i = 0; i < distance.length; i++) {
        answer += (long) distance[i] * (long) p;
        p = Math.min(p, price[i + 1]);
    }

    System.out.println(answer);

}
~~~