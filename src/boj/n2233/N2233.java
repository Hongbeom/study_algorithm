package boj.n2233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N2233 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] search = br.readLine().toCharArray();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        Node a = null;
        Node b = null;
        int id = 0;
        Node root = new Node(null, 0);
        root.setVisit(1);
        root.setExit(search.length);

        Node node = root;
        for (int i = 0; i < search.length; i++) {
            char c = search[i];
            if (c == '0') {
                id++;
                Node child = new Node(node, node.height + 1);
                child.setVisit(i + 1);
                node.addChild(child);
                node = child;
                if (i + 1 == x || i + 1 == y) {
                    if (a == null) {
                        a = node;
                    } else {
                        b = node;
                    }
                }
            } else {
                node.setExit(i + 1);
                if (i + 1 == x || i + 1 == y) {
                    if (a == null) {
                        a = node;
                    } else {
                        b = node;
                    }
                }
                node = node.parent;
            }
        }


        if (a.height > b.height) {
            while (a.height != b.height) {
                a = a.parent;
            }
        } else if (a.height < b.height) {
            while (a.height != b.height) {
                b = b.parent;
            }
        }

        while (a != b) {
            a = a.parent;
            b = b.parent;
        }

        System.out.println(a.visit + " " + a.exit);


    }


    static class Node {
        int visit;
        int exit;
        int height;
        Node parent;
        List<Node> children;

        public Node(Node parent, int height) {
            this.parent = parent;
            this.height = height;
            this.children = new ArrayList<>();
        }

        public void setVisit(int visit) {
            this.visit = visit;
        }

        public void setExit(int exit) {
            this.exit = exit;
        }

        public void addChild(Node child) {
            this.children.add(child);
        }
    }
}
