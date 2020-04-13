## 백준 2212 - [센서](https://www.acmicpc.net/problem/2212)

### 풀이법
1. 센서의 위치를 정렬 후, 각 센서의 연속된 거리를 구한다. 그 후, k-1개까지의 가장 큰 거리를 제외하고의 모든 거리의 합을 구한다.

~~~JAVA
public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] sensors = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (k >= n) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensors);
        int[] dist = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            dist[i] = sensors[i + 1] - sensors[i];
        }
        Arrays.sort(dist);
        for (int i = 0; i < k - 1; i++) {
            dist[dist.length - 1 - i] = 0;
        }
        int answer = 0;
        for (int i = 0; i < n - 1; i++) {
            answer += dist[i];
        }
        System.out.println(answer);
    }
~~~
