## 백준 16567 - [바이너리 왕국](https://www.acmicpc.net/problem/16567)

### 풀이법

1. 길의 청결 상태를 boolean 배열로 저장.
2. 초기 flip의 횟수를 구한 다음 쿼리가 들어올때 flip을 변경.

```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int flip = 0;
    boolean continual = false;
    boolean[] way = new boolean[n];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
        if (Integer.parseInt(st.nextToken()) == 1) {
            way[i] = true;
            if (!continual) {
                flip++;
                continual = true;
            }
        } else {
            if (continual) {
                continual = false;
            }
        }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());
        if (q == 0) {
            sb.append(flip);
            sb.append('\n');
        } else {
            int id = Integer.parseInt(st.nextToken()) - 1;
            if (!way[id]) {
                way[id] = true;
                if (id == 0) {
                    if (!way[id + 1]) {
                        flip++;
                    }
                } else if (id == way.length - 1) {
                    if (!way[id - 1]) {
                        flip++;
                    }
                } else {
                    if (!way[id - 1] && !way[id + 1]) {
                        flip++;
                    } else if (way[id - 1] && way[id + 1]) {
                        flip--;
                    }
                }
            }
        }
    }
    System.out.println(sb.toString());
}
```
