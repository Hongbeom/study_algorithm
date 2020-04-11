## 백준 14792 - [칠무해](https://www.acmicpc.net/problem/14792)


### 풀이법

1. 시간을 줄이기 위해서, 최대 우선순위큐의 크기가 7을 넘지 않게 하고, max값을 이용해서 판단.

~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    double max = Double.MIN_VALUE;
    PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        double v = Double.parseDouble(br.readLine());
        if (cnt < 7) {
            cnt++;
            pq.add(v);
            max = Math.max(max, v);
            continue;
        }
        if (max < v) {
            continue;
        }
        pq.add(v);
        pq.poll();
        max = pq.peek();
    }
    double[] answer = new double[7];
    int id = 0;
    while (!pq.isEmpty()) {
        answer[id++] = pq.poll();
    }
    for (int i = answer.length - 1; i >= 0; i--) {
        System.out.println(String.format("%.3f", answer[i]));
    }
}
~~~
