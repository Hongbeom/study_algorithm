package boj.n12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N12100 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];

        StringTokenizer st;
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                answer = Math.max(board[i][j], answer);
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(board, answer, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int step = current.step;

            if (step == 5) {
                answer = Math.max(answer, current.max);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int[][] b = clone(current.board);
                int m = move(b, i);
                queue.add(new Node(b, m, step + 1));
            }

        }

        System.out.println(answer);


    }

    static class Node {
        int[][] board;
        int max;
        int step;

        Node(int[][] board, int max, int step) {
            this.board = board;
            this.max = max;
            this.step = step;
        }
    }

    static int[][] clone(int[][] input) {
        int[][] re = new int[input.length][input[0].length];

        for (int i = 0; i < input.length; i++) {
            System.arraycopy(input[i], 0, re[i], 0, input[i].length);
        }

        return re;
    }


    static int move(int[][] board, int direction) {
        int max = Integer.MIN_VALUE;
        boolean[][] summed = new boolean[board.length][board[0].length];

        switch (direction) {
            case 0:
                for (int j = 0; j < board[0].length; j++) {
                    for (int i = 1; i < board.length; i++) {
                        int id = i;
                        while (id >= 1) {
                            if (board[id][j] == 0) {
                                id--;
                                continue;
                            } else if (board[id - 1][j] == 0) {
                                board[id - 1][j] = board[id][j];
                                board[id][j] = 0;
                                if (summed[id][j]) {
                                    summed[id - 1][j] = summed[id][j];
                                    summed[id][j] = false;
                                }
                            } else if (board[id - 1][j] == board[id][j] && !summed[id - 1][j] && !summed[id][j]) {
                                board[id - 1][j] *= 2;
                                summed[id - 1][j] = true;
                                board[id][j] = 0;

                                max = Math.max(max, board[id - 1][j]);
                            }
                            id--;
                        }

                    }
                }
                break;
            case 1:
                for (int j = 0; j < board[0].length; j++) {
                    for (int i = board.length - 2; i >= 0; i--) {
                        int id = i;
                        while (id <= board.length - 2) {
                            if (board[id][j] == 0) {
                                id++;
                                continue;
                            } else if (board[id + 1][j] == 0) {
                                board[id + 1][j] = board[id][j];
                                board[id][j] = 0;
                                if (summed[id][j]) {
                                    summed[id + 1][j] = summed[id][j];
                                    summed[id][j] = false;
                                }
                            } else if (board[id + 1][j] == board[id][j] && !summed[id + 1][j] && !summed[id][j]) {
                                board[id + 1][j] *= 2;
                                summed[id + 1][j] = true;
                                board[id][j] = 0;

                                max = Math.max(max, board[id + 1][j]);
                            }
                            id++;
                        }

                    }
                }
                break;

            case 2:
                for (int i = 0; i < board.length; i++) {
                    for (int j = 1; j < board[0].length; j++) {
                        int id = j;
                        while (id >= 1) {
                            if (board[i][id] == 0) {
                                id--;
                                continue;
                            } else if (board[i][id - 1] == 0) {
                                board[i][id - 1] = board[i][id];
                                board[i][id] = 0;
                                if (summed[i][id]) {
                                    summed[i][id - 1] = summed[i][id];
                                    summed[i][id] = false;
                                }
                            } else if (board[i][id - 1] == board[i][id] && !summed[i][id - 1] && !summed[i][id]) {
                                board[i][id - 1] *= 2;
                                summed[i][id - 1] = true;
                                board[i][id] = 0;

                                max = Math.max(max, board[i][id - 1]);
                            }
                            id--;
                        }

                    }
                }
                break;
            case 3:
                for (int i = 0; i < board.length; i++) {
                    for (int j = board[0].length - 2; j >= 0; j--) {
                        int id = j;
                        while (id <= board[0].length - 2) {
                            if (board[i][id] == 0) {
                                id++;
                                continue;
                            } else if (board[i][id + 1] == 0) {
                                board[i][id + 1] = board[i][id];
                                board[i][id] = 0;
                                if (summed[i][id]) {
                                    summed[i][id + 1] = summed[i][id];
                                    summed[i][id] = false;
                                }
                            } else if (board[i][id + 1] == board[i][id] && !summed[i][id + 1] && !summed[i][id]) {
                                board[i][id + 1] *= 2;
                                summed[i][id + 1] = true;
                                board[i][id] = 0;

                                max = Math.max(max, board[i][id + 1]);
                            }
                            id++;
                        }

                    }
                }
                break;
        }

        return max;
    }
}
