package boj.n7682;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class N7682 {

    static StringBuilder ANS = new StringBuilder();
    static boolean[] CHECK = new boolean[100000];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bfs();
        while (true) {
            String in = br.readLine();
            if (in.equals("end")) {
                break;
            }

            char[] board = in.toCharArray();
            int value = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == 'X') {
                    value += (int) Math.pow(3, 8 - i);
                } else if (board[i] == 'O') {
                    value += ((int) Math.pow(3, 8 - i) * 2);
                }
            }

            if (CHECK[value]) {
                valid();
            } else {
                invalid();
            }

        }

        System.out.println(ANS.toString());
    }

    static void bfs() {
        char[] board = new char[9];
        Arrays.fill(board, '.');
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(board, true, 0, 0));
        boolean[] visited = new boolean[100000];
        visited[0] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            char[] cb = current.board;
            boolean ct = current.turn;
            int cc = current.cnt;
            int cv = current.value;

            for (int i = 0; i < 9; i++) {
                if (cb[i] != '.') {
                    continue;
                }
                int nv;
                if (ct) {
                    nv = cv + (int) Math.pow(3, 8 - i);
                } else {
                    nv = cv + ((int) Math.pow(3, 8 - i) * 2);
                }

                if (visited[nv]) {
                    continue;
                }
                visited[nv] = true;
                char[] nb = cb.clone();
                if (ct) {
                    nb[i] = 'X';
                } else {
                    nb[i] = 'O';
                }

                if (checkEnd(nb) || cc + 1 == 9) {
                    CHECK[nv] = true;
                    continue;
                }
                queue.add(new Node(nb, !ct, cc + 1, nv));
            }
        }

    }

    static void valid() {
        ANS.append("valid\n");
    }

    static void invalid() {
        ANS.append("invalid\n");
    }

    static class Node {
        char[] board;
        boolean turn;
        int cnt;
        int value;

        public Node(char[] board, boolean turn, int cnt, int value) {
            this.board = board;
            this.turn = turn;
            this.cnt = cnt;
            this.value = value;
        }
    }

    static boolean checkEnd(char[] board) {

        return (board[0] != '.' && board[0] == board[3] && board[3] == board[6]) ||
                (board[0] != '.' && board[0] == board[1] && board[1] == board[2]) ||
                (board[0] != '.' && board[0] == board[4] && board[4] == board[8]) ||
                (board[3] != '.' && board[3] == board[4] && board[4] == board[5]) ||
                (board[6] != '.' && board[6] == board[7] && board[7] == board[8]) ||
                (board[1] != '.' && board[1] == board[4] && board[4] == board[7]) ||
                (board[2] != '.' && board[2] == board[5] && board[5] == board[8]) ||
                (board[2] != '.' && board[2] == board[4] && board[4] == board[6]);
    }


}
