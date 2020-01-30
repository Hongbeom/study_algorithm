## 백준 5010 - [수화물](https://www.acmicpc.net/problem/5010)

풀이를 떠올리기 정말 힘들었던 문제... 결국 솔루션을 보고 해결하였다... 
 
### 초기 잘못된 풀이법
 
1. 지도의 왼쪽 위부터 시작하여 X가 나타나면 종이를 더하고 checked 배열에 종이의 위치를 표시하였다.
 
 ```JAVA
static void check(boolean[][] checked, int a, int b, int h, int w) {

    int hEnd;
    int wEnd;

    if (a + h > checked.length) {
        hEnd = checked.length;
    } else {
        hEnd = a + h;
    }

    if (b + w > checked[0].length) {
        wEnd = checked[0].length;
    } else {
        wEnd = b + w;
    }


    for (int i = a; i < hEnd; i++) {
        for (int j = b; j < wEnd; j++) {
            checked[i][j] = true;
        }
    }
}
```

- 종이가 겹치는 경우도 발생하고, 종이를 덧대려면 한 변을 전체로 공유해야만 한다,
  - 당연히 잘못된 풀이!
  
### 풀이법

1. 인쇄할 종이를 이으려면 한변 전체를 공유해야 하므로, 종이를 잇는 경우의 수를 탐색하면 된다.
- 그 케이스마다 이 종이에 X가 포함되어 있는지 판단하는게 문제.

2. 왼쪽 위의 지점을 기준으로 그 공간이 빈 공간인지 표시해 주면 되는 문제이다.
- 이 과정은 O((N + H) * (M + W))의 시간복잡도로 표시해 줄수 있다.

~~~JAVA
// 배열의 크기를 확장시켜 준다.
for (int i = 0; i < n; i++) {
    char[] tm = br.readLine().toCharArray();
    for (int j = 0; j < m; j++) {
        map[i + h][j + w] = tm[j];
    }
}

// 빈 공간을 배열에 표시해 준다. - 왼쪽 위 기준의 페이지.

// 지도의 오른쪽 부터 그 빈 row가 종이의 width 만큼 감당됨을 표시해 준다.
for (int i = 0; i < n + h; i++) {
    int cnt = w;
    for (int j = m + w - 1; j >= 0; j--) {
        if (map[i][j] != 'X') {
            if (++cnt >= w) {
                map[i][j] = '1';
            }
        } else {
            cnt = 0;
        }
    }
}

// 지도의 밑에서 부터, 그 빈 자리의 공간이 page가 감당됨을 표시해 준다 -> 빈공간 표시.
for (int j = 0; j < m + w; j++) {
    int cnt = h;
    for (int i = n + h - 1; i >= 0; i--) {
        if (map[i][j] == '1') {
            if (++cnt >= h) {
                map[i][j] = '0';
            }
        } else {
            cnt = 0;
        }
    }
}
~~~

3. 종이를 잇는 경우의 수를 탐색할때, 왼쪽 위 지점을 기준으로 지도를 탐색하며 만약 그자리가 비어있는 자리라면 카운트 해주지 않는다.

~~~JAVA
for (int hs = 0; hs < h; hs++) {
    for (int ws = 0; ws < w; ws++) {
        int count = 0;

        for (int i = hs; i < n + h; i += h) {
            for (int j = ws; j < m + w; j += w) {
                if (map[i][j] != '0') {
                    count++;
                }
            }
        }
        answer = Math.min(answer, count);

    }
}
~~~