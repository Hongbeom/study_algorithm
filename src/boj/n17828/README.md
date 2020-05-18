## 백준 17878 - [문자열 화폐](https://www.acmicpc.net/problem/17878)

### 풀이법

1. x가 n보다 작고, n * 26 보다 크다면 !출력.
2. n * 26의 차이만큼 앞에서부터 25 씩 빼줌.


~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int x = Integer.parseInt(st.nextToken());

    int limit = n * 26;
    if (limit < x || x < n) {
        System.out.println('!');
        return;
    }

    StringBuilder sb = new StringBuilder();
    int left = limit - x;
    for (int i = 0; i < n; i++) {
        if (left - 25 >= 0) {
            sb.append('A');
            left -= 25;
        } else if (left > 0) {
            sb.append((char) (90 - left));
            left = 0;
        } else {
            sb.append('Z');
        }
    }
    System.out.println(sb.toString());
}
~~~