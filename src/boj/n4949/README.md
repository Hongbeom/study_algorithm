## 백준 4949 - [균형잡힌 세상](https://www.acmicpc.net/problem/4949)

### 풀이법

1. stack 이용 풀이. 

```JAVA
Stack<Character> stack = new Stack<>();
for (char c : sentence) {
    if (c == '(' || c == '[') {
        stack.push(c);
    } else if (c == ')') {
        if (stack.isEmpty()) {
            no();
            continue root;
        }

        if(stack.pop() != '('){
            no();
            continue root;
        }

    } else if (c == ']') {
        if (stack.isEmpty()) {
            no();
            continue root;
        }

        if(stack.pop() != '['){
            no();
            continue root;
        }
    }
}
```
