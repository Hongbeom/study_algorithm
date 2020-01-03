package boj.n4949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class N4949 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        root:
        while (true) {
            char[] sentence = br.readLine().toCharArray();

            if (sentence.length == 1 && sentence[0] == '.') {
                return;
            }

            Stack<Character> stack = new Stack<>();
            for (char c : sentence) {
                if (c == '(' || c == '[') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.isEmpty()) {
                        no();
                        continue root;
                    }

                    if (stack.pop() != '(') {
                        no();
                        continue root;
                    }

                } else if (c == ']') {
                    if (stack.isEmpty()) {
                        no();
                        continue root;
                    }

                    if (stack.pop() != '[') {
                        no();
                        continue root;
                    }
                }
            }

            if (stack.isEmpty()) {
                yes();
            } else {
                no();
            }

        }

    }

    static void yes() {
        System.out.println("yes");
    }

    static void no() {
        System.out.println("no");
    }
}
