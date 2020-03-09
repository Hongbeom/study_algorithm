## 백준 10253 - [헨리](https://www.acmicpc.net/problem/10253)

### 풀이법

1. b, a를 구한 뒤, 최대 공약수로 약분하여 재귀적으로 계산하면 된다.

~~~JAVA
static long solve(long a, long b) {

    if (b % a == 0) {
        return b / a;
    }

    long n = b / a + 1;

    a = a * n - b;
    b = b * n;

    long gcd = gcd(n, a);

    a /= gcd;
    b /= gcd;
    return solve(a, b);
}

static long gcd(long a, long b) {

    if (b == 0) {
        return a;
    } else {
        return gcd(b, a % b);
    }
}
~~~

