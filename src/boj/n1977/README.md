## 백준 1977 - [완전제곱수](https://www.acmicpc.net/problem/1977)

### 풀이법

1. 제곱근을 이용.

```JAVA
int sqrtM = (int) Math.sqrt(m);
int sqrtN = (int) Math.sqrt(n);
if (Math.pow(sqrtM, 2) < m) {
    sqrtM++;
}

int ans = sqrtN - sqrtM + 1;
if (ans == 0) {
    System.out.println(-1);
} else {
    int sum = 0;
    for (int i = sqrtM; i <= sqrtN; i++) {
        sum += (int) Math.pow(i, 2);
    }

    System.out.println(sum + "\n" + (int) Math.pow(sqrtM, 2));
}
```
