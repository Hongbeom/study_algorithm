package boj.n5397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N5397 {

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
}
