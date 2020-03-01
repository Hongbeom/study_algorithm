## 백준 14488 - [준오는 급식충이야!!](https://www.acmicpc.net/problem/14488)

### 풀이법

1. 각 친구들의 위치와 속도를 인풋으로 받을때, 친구들이 움직일수있는 최소 오른쪽, 최대 왼쪽을 찾는다.
2. 각 친구들이 이 범위로 움직일 수 있는지 검사한다.
3. 소수 4자리에서 반올림해야한다.


```JAVA

for (int i = 0; i < n; i++) {
    loc[i] = Double.parseDouble(st.nextToken());
    vec[i] = Double.parseDouble(vst.nextToken());

    left = Math.max(left, Math.round((loc[i] - t * vec[i]) * 10000) / 10000.0);
    right = Math.min(right, loc[i] + t * vec[i]);

}

if (left > right) {
    System.out.println(0);
    return;
}

boolean answer = true;

for (int i = 0; i < n; i++) {
    if (loc[i] > right) {
        if (Math.round((loc[i] - t * vec[i]) * 10000) / 10000.0 > right) {
            answer = false;
            break;
        }
    }
    if (loc[i] < left) {
        if (loc[i] + t * vec[i] < left) {
            answer = false;
            break;
        }
    }
}
```
