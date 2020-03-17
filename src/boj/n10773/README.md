## 백준 10773 - [제로](https://www.acmicpc.net/problem/10773)

### 풀이법

1. 스택을 이용하면 되는 문제.

~~~JAVA
Stack<Integer> stack = new Stack<>();

for (int i = 0; i < k; i++) {
    int q = Integer.parseInt(br.readLine());
    if (q == 0) {
        stack.pop();
    } else {
        stack.add(q);
    }
}
int ans = 0;
while (!stack.isEmpty()) {
    ans += stack.pop();
}
~~~

