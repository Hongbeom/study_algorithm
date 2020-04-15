## 백준 3015 - [오아시스 재결합](https://www.acmicpc.net/problem/3015)

### 초기 잘못된 접근

1. 초기 리스트를 이용하여 이분탐색으로 리스트를 탐색하여 해결하려 했다.
- 고려해 줄게 너무 많다... 하지만, 이분탐색을 이용한 풀이도 가능할듯 하다.

### 풀이법

1. 스택을 이용한다. 스택은 {값, 갯수} 의 오브젝트를 형태
- 스택의 원소들은 항상 내림차순을 유지한다.
- 스택의 max(bottom)값 보다 현재 값이 더 크면 stack 을 clear 하고 stack의 사이즈(스택안에 존재하는 갯수의 합) 만큼 answer 증가.
- 아닐 경우, 자신보다 크거나 같은 값이 나올때까지 stack을 pop 시키고 pop한 횟수만큼 answer 증가.
    - pop을 모두 수행 후, 스택의 top값이 현재 비교값보다 큰 경우 answer 1 증가.
    - 같은 경우 그 top의 갯수만큼 answer 증가.
        - 여기서 또, 그 top이 bottom이 아닐 경우(스택의 사이즈가 1일 경우) answer 1 증가. -> 그 전의 값과도 쌍을 이룰 수 있기 때
~~~JAVA
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int size = 0;
    long answer = 0;
    int max = Integer.MIN_VALUE;
    Stack<Node> stack = new Stack<>();

    for (int i = 0; i < n; i++) {
        int value = Integer.parseInt(br.readLine());
        if (i == 0) {
            max = value;
            size++;
            stack.push(new Node(value, 1));
            continue;
        }

        if (value > max) {
            max = value;
            answer += size;
            stack.clear();
            size = 1;
            stack.push(new Node(value, 1));
        } else {
            int pop = 0;
            while (stack.peek().value < value) {
                Node p = stack.pop();
                pop += p.n;
                size -= p.n;
            }
            // 이제 여기서 라스트랑 같은지 안같은지 봐주면 되는거 아닌감
            answer += pop;
            if (stack.peek().value == value) {
                answer += stack.peek().n;
                stack.peek().add();
                if (stack.size() > 1) {
                    answer++;
                }
            } else {
                answer++;
                stack.add(new Node(value, 1));
            }
            size++;
        }
    }
    System.out.println(answer);
}


static class Node {
    int value;
    int n;

    public Node(int value, int n) {
        this.value = value;
        this.n = n;
    }

    void add() {
        n++;
    }
}
~~~
