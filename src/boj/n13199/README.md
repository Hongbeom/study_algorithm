## 백준 13199 - [치킨 먹고 싶다](https://www.acmicpc.net/problem/13199)

### 풀이법

1. 두영이의 치킨은 일단 돈으로 다 먹은 다음 쿠폰으로 먹는 갯수이다.


```JAVA
int moneyChicken = m / p;

int coupon = moneyChicken * c;

int doYoung = moneyChicken + (coupon / f);
```

2. 상언이는 치킨을 돈으로 다 먹은 후 쿠폰으로 시켜도 또 쿠폰으로 받기 때문에 반복을 통해 갯수를 세어주었다.
~~~JAVA
int sangUn = moneyChicken;

while (coupon >= f) {
    sangUn += (coupon / f);
    coupon = coupon / f * c +  coupon % f;
}

~~~
- 시간초과 - 문제의 조건이 크기 때문에 루프가 많이 도는 문제.

3. 생각해보면, 상언이는 (돈으로 시켰을때 받은 쿠폰 수) / (치킨당 필요한 쿠폰 수 - 치킨 한마리당 받는 쿠폰 갯수) 만큼 치킨을 더 먹는다.
- 반례 존재 - 위 식을 보면 쿠폰으로 치킨을 시킬수 없는데도 미리 쿠폰을 땡겨서 치킨을 시키는 경우도 포함된다.
- 그래서 먼저 (쿠폰수 - 치킨당 필요한 쿠폰 수) 를 해주고 나눠준다.

~~~JAVA
int sangUn = moneyChicken;

if (coupon - f >= 0) {
    sangUn += (coupon - f) / (f - c) + 1;
}
~~~