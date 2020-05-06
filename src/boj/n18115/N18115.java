package boj.n18115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N18115 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] flags = br.readLine().split(" ");

        Node top = null;
        Node back = null;

        for (int i = n - 1; i >= 0; i--) {
            int card = n - i;
            if (i == n - 1) {
                top = new Node(card);
                back = top;
            } else {
                Node node = new Node(card);
                switch (flags[i]) {
                    case "1":
                        node.back = top;
                        top.front = node;
                        top = node;
                        break;
                    case "2":
                        if (top.back == null) {
                            node.front = top;
                            top.back = node;
                            back = node;
                        } else {
                            node.front = top;
                            node.back = top.back;

                            top.back = node;
                            top.back.front = node;
                        }
                        break;
                    case "3":
                        back.back = node;
                        node.front = back;
                        back = node;
                        break;
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        while (top != null) {
            sb.append(top.value);
            sb.append(' ');
            top = top.back;
        }

        System.out.println(sb.toString());

    }

    static class Node {
        int value;
        Node front;
        Node back;

        Node(int value) {
            this.value = value;
        }
    }

}
