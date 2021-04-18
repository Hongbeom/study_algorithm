## 백준 5397 - [키로거](https://www.acmicpc.net/problem/5397)

### 풀이법

1. LinkedList 이용, 왼쪽 노드, 오른쪽 노드, 삭제 노드 기능 구현.

```java
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    for (int tc = 0; tc < TC; tc++) {
        char[] input = br.readLine().toCharArray();
        Node root = new Node();
        Node current = root;
        for (char c : input) {
            switch (c) {
                case '<':
                    if (current.prev != null)
                        current = current.prev;
                    break;
                case '>':
                    if (current.next != null)
                        current = current.next;
                    break;
                case '-':
                    if (current.prev != null) {
                        current.prev.next = current.next;
                        if (current.next != null)
                            current.next.prev = current.prev;
                    }
                    if (current != root)
                        current = current.prev;
                    break;
                default:
                    Node add = new Node(c);
                    if (current.next != null) {
                        current.next.prev = add;
                        add.next = current.next;
                    }
                    current.next = add;
                    add.prev = current;
                    current = add;
            }
        }
        StringBuilder sb = new StringBuilder();
        Node search = root.next;
        while (search != null) {
            sb.append(search.value);
            search = search.next;
        }
        System.out.println(sb);
    }
}

static class Node {
    char value;
    Node prev;
    Node next;

    public Node() {

    }

    public Node(char value) {
        this.value = value;
    }
}
```
