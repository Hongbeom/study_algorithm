package boj.n2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class N2504 {

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
}
