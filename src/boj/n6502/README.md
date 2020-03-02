## 백준 6502 - [동혁 피자](https://www.acmicpc.net/problem/6502)


### 풀이법 

1. 피자의 대각 길이의 반과 식탁의 반지름을 비교
```JAVA
if (r < getRadius(w, l)) {
    no(n);
} else {
    yes(n);
}
```

```JAVA
static double getRadius(double w, double l) {
    return Math.sqrt(Math.pow(w / 2, 2) + Math.pow(l / 2, 2));
}

static void yes(int n) {
    System.out.println("Pizza " + n + " fits on the table.");
}

static void no(int n) {
    System.out.println("Pizza " + n + " does not fit on the table.");
}
```
