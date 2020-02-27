## 백준 9763 - [마을의 친밀도](https://www.acmicpc.net/problem/9763)

### 초기 잘못된 접근

1. 3개의 마을을 뽑아서 brute force? -> 당연히 시간초과 (O(NC3), N은 최대 10,000)
2. 친밀도 공식을 보면 두 마을의 distance에서 가장 작은 두 값을 더한것.
3. 마을의 distance를 모두 구한 후 priority queue를 이용해서 연결되어있는 가장 작은 2개의 거리를 뽑는다.
- 하지만 메모리초과.

### 풀이법

1. 마을의 distance를 모두 구해서 저장할 필요가 없다.
- 저장하지 않고, 그 마을에 대한 최소 distance를 계속 갱신하며 최솟값을 구한다.

~~~JAVA
int[] minDist = new int[n];
Arrays.fill(minDist, Integer.MAX_VALUE);

int answer = Integer.MAX_VALUE;

for (int i = 0; i < n - 1; i++) {
    for (int j = i + 1; j < n; j++) {

        int distance = Math.abs(towns[i][0] - towns[j][0]) + Math.abs(towns[i][1] - towns[j][1]) + Math.abs(towns[i][2] - towns[j][2]);

        if (minDist[i] != Integer.MAX_VALUE || minDist[j] != Integer.MAX_VALUE) {
            answer = Math.min(answer, Math.min(minDist[i], minDist[j]) + distance);
        }

        minDist[i] = Math.min(minDist[i], distance);
        minDist[j] = Math.min(minDist[j], distance);
    }
}
~~~

