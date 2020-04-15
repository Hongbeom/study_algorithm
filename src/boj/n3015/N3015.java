package boj.n3015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class N3015 {

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

}
