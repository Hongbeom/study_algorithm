## 백준 5625 - [페스트리](https://www.acmicpc.net/problem/5625)

라인 스위핑의 개념을 이용한 문제... sweep line에 대한 이해가 필요하다. -> solution을 보고 해결함


### 초기 잘못된 접근
1. 인풋을 받고, 각 페스트리에 대한 x의 min, max, y의 min, max를 구한다.
2. range를 구했으니, 각 width와 hegiht 배열에 페스트리가 존재한다고 ++ 해준다.

~~~JAVA
for (int i = 0; i < n; i++) {

    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;

    int minY = Integer.MAX_VALUE;
    int maxY = Integer.MIN_VALUE;

    st = new StringTokenizer(br.readLine());
    for (int j = 0; j < 6; j++) {
        if (j % 2 == 0) {
            int x = Integer.parseInt(st.nextToken());
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
        } else {
            int y = Integer.parseInt(st.nextToken());
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
    }

    for (int k = minX + 1; k < maxX; k++) {
        width[k]++;
    }

    for (int k = minY + 1; k < maxY; k++) {
        height[k]++;
    }

}
~~~

- 인풋을 받으면서 for 문으로 ++를 해준다.
- n^2의 시간복잡도... -> 인풋이 n이 최대 100,000 이므로 당연히 시간초과...


### 풀이법

1. 어떤 위치에 수직으로 잘랐을때 잘리는 페스트리의 갯수
- 총 페스트리의 수 - (x 보다 왼쪽에 있는 페스트리의 갯수) - (x 보다 오른에 존재하는 페스트리의 갯수)
- x보다 왼쪽에 있는 페스트리의 갯수 = x-1 보다 왼쪽에 있는 페스트리 갯수 + x로 끝나는 페스트리의 갯수
2. 위의 방식을 왼쪽, 오른쪽 -> x축, y축에 적 

```JAVA
int[] wLeft = new int[1000000];
int[] wRightMost = new int[1000000];
int[] wRight = new int[1000000];
int[] wLeftMost = new int[1000000];

int[] hLeft = new int[1000000];
int[] hRightMost = new int[1000000];
int[] hRight = new int[1000000];
int[] hLeftMost = new int[1000000];


StringTokenizer st;
for (int i = 0; i < n; i++) {
    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;

    int minY = Integer.MAX_VALUE;
    int maxY = Integer.MIN_VALUE;

    st = new StringTokenizer(br.readLine());
    for (int j = 0; j < 6; j++) {
        if (j % 2 == 0) {
            int x = Integer.parseInt(st.nextToken());
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
        } else {
            int y = Integer.parseInt(st.nextToken());
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
    }

    // for width
    wLeftMost[minX]++;
    wRightMost[maxX]++;

    // for height;
    hLeftMost[minY]++;
    hRightMost[maxY]++;

}

// for width
for (int i = 1; i < wLeft.length; i++) {
    wLeft[i] = wLeft[i - 1] + wRightMost[i];
}

for (int i = wRightMost.length - 2; i >= 0; i--) {
    wRight[i] = wRight[i + 1] + wLeftMost[i];
}

// for height;
for (int i = 1; i < hLeft.length; i++) {
    hLeft[i] = hLeft[i - 1] + hRightMost[i];
}

for (int i = hRightMost.length - 2; i >= 0; i--) {
    hRight[i] = hRight[i + 1] + hLeftMost[i];
}
```

- 비효율적인 코드... 조금 더 개념을 이해한 후 다시 풀어보자.
