## 백준 2504 - [괄호의 값](https://www.acmicpc.net/problem/2504)

### 풀이법

1. 스택을 이용하여 옳은 괄호인지 아닌지 판단한다.
2. 각각의 괄호쌍에 id를 부여하고, parent-child 관계를 설정.
- pop될때 만약 자식들이 존재한다면 multiply 수행.
- pop될때 만약 부모가 존재한다면 values[부모 id]에 값을 더해준다.
- pop될때 만약 부모가 존재하지 않는다면 answer에 값을 더해준다. 

```JAVA
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] brackets = br.readLine().toCharArray();
    Stack<Node> stack = new Stack<>();
    int answer = 0;
    int[] values = new int[brackets.length];
    for (int i = 0; i < brackets.length; i++) {
        char b = brackets[i];
        if (b == '(' || b == '[') {
            if (stack.isEmpty()) {
                stack.push(new Node(b, i, -1));
            } else {
                stack.push(new Node(b, i, stack.peek().id));
            }
        } else {
            if (stack.isEmpty() || !check(stack.peek().b, b)) {
                System.out.println(0);
                return;
            }
            Node node = stack.pop();

            if (values[node.id] != 0) {
                node.value *= values[node.id];
            }

            if (node.pid == -1) {
                answer += node.value;
            } else {
                values[node.pid] += node.value;
            }
        }
    }

    if (stack.isEmpty()) {
        System.out.println(answer);
    } else {
        System.out.println(0);
    }
}

static class Node {
    char b;
    int id;
    int value;
    int pid;

    public Node(char b, int id, int pid) {
        this.b = b;
        this.value = b == '(' ? 2 : 3;
        this.id = id;
        this.pid = pid;
    }
}

static boolean check(char open, char close) {
    boolean check = false;
    switch (open) {
        case '(':
            check = close == ')';
            break;
        case '[':
            check = close == ']';
            break;

    }
    return check;
}
```