package boj.n7662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N7662 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            int k = Integer.parseInt(br.readLine());
            BST root = null;

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String q = st.nextToken();
                long n = Long.parseLong(st.nextToken());

                if (q.equals("I")) {
                    if (root == null) {
                        root = new BST(n);
                    } else {
                        root.add(n);
                    }

                } else {
                    if (n == 1) {
                        if (root == null) {
                            continue;
                        }
                        root = root.removeMax();

                    } else if (n == -1) {
                        if (root == null) {
                            continue;
                        }
                        root = root.removeMin();
                    }
                }
            }

            if (root == null) {
                System.out.println("EMPTY");
            } else {
                System.out.println(root.getMax() + " " + root.getMin());
            }
        }
    }


    static class BST {
        long value;
        int count;

        BST parent;
        BST left;
        BST right;

        BST(long value) {
            this.value = value;
            this.count = 1;
        }

        BST(long value, BST parent) {
            this.value = value;
            this.count = 1;
            this.parent = parent;
        }

        void add(long v) {
            BST t = this;
            while (true) {
                if (v > t.value) {
                    if (t.right == null) {
                        t.right = new BST(v, t);
                        break;
                    }
                    t = t.right;
                } else if (v < t.value) {
                    if (t.left == null) {
                        t.left = new BST(v, t);
                        break;
                    }
                    t = t.left;
                } else {
                    t.count++;
                    break;
                }
            }
        }

        BST removeMax() {
            BST t = this;
            while (t.right != null) {
                t = t.right;
            }
            t.count--;
            if (t.count == 0) {
                if (t.parent == null) {
                    if (t.left == null) {
                        return null;
                    } else {
                        t.left.parent = null;
                        return t.left;
                    }
                } else {
                    if (t.left != null) {
                        t.parent.right = t.left;
                        t.left.parent = t.parent;
                    } else {
                        t.parent.right = null;
                    }

                }
            }
            return this;
        }

        BST removeMin() {
            BST t = this;

            while (t.left != null) {
                t = t.left;
            }
            t.count--;
            if (t.count == 0) {
                if (t.parent == null) {
                    if (t.right == null) {
                        return null;
                    } else {
                        t.right.parent = null;
                        return t.right;
                    }
                } else {
                    if(t.right != null){
                        t.parent.left = t.right;
                        t.right.parent = t.parent;
                    }else{
                        t.parent.left = null;
                    }
                }
            }
            return this;
        }

        long getMax() {
            BST t = this;
            while (t.right != null) {
                t = t.right;
            }

            return t.value;
        }

        long getMin() {
            BST t = this;
            while (t.left != null) {
                t = t.left;
            }
            return t.value;
        }
    }
}
