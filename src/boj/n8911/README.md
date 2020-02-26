## 백준 8911 - [거북이](https://www.acmicpc.net/problem/8911)
 
 
### 풀이법
 
1. 거북이의 방향을 잘 고려하여 이동 시킨 후 상, 하, 좌, 우의 가장 끝점을 기준으로 직사각형의 넓이를 구함.
 
 ```JAVA
int d = 0;
int[] spot = new int[2];
int maxH = 0;
int minH = 0;
int maxW = 0;
int minW = 0;
char[] moves = br.readLine().toCharArray();

for (char move : moves) {
    switch (move) {
        case 'F':
            spot[0] += H[d];
            spot[1] += W[d];
            break;
        case 'B':
            spot[0] += H[(d + 2) % 4];
            spot[1] += W[(d + 2) % 4];
            break;
        case 'L':
            d = (d + 3) % 4;
            break;
        case 'R':
            d = (d + 1) % 4;
            break;
    }
    maxH = Math.max(spot[0], maxH);
    minH = Math.min(spot[0], minH);

    maxW = Math.max(spot[1], maxW);
    minW = Math.min(spot[1], minW);
}
System.out.println((maxH - minH) * (maxW - minW));
```
