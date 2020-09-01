package boj.N2257;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class N2257 {

    static final int H = 1;
    static final int C = 12;
    static final int O = 16;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cf = br.readLine().toCharArray();
        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        for (char c : cf) {
            if (c == '(') {
                stack.add(-1);
            } else if (c == ')') {
                int tmp = 0;
                while (true) {
                    int value = stack.pop();
                    if (value == -1) {
                        break;
                    }
                    tmp += value;
                }
                stack.add(tmp);
            } else if (c == 'H') {
                stack.add(H);
            } else if (c == 'C') {
                stack.add(C);
            } else if (c == 'O') {
                stack.add(O);
            } else {
                stack.add(stack.pop() * (c - '0'));
            }
        }

        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        System.out.println(ans);
    }
}
