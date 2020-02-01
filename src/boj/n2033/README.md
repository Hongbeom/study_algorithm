## 백준 2033 - [반올림](https://www.acmicpc.net/problem/2033)

### 풀이법 

1. Math 라이브러리를 사용하여 계속 반올림 해준다.

~~~JAVA
int exp = 1;
double compare = Math.pow(10, exp);

while (n > compare) {
    n = Math.round(n / compare) * compare;
    exp++;
    compare = Math.pow(10, exp);
}
~~~