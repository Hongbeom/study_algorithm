package boj.n1039;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N1039 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] number = st.nextToken().toCharArray();
        int k = Integer.parseInt(st.nextToken());

        List<int[]> changes = getChanges(number.length);
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(number, 0));

        boolean[] visited = new boolean[1000001];

        int answer = -1;
        int cnt = 0;

        while (!queue.isEmpty()) {

            Node current = queue.poll();
            int step = current.step;

            if (cnt + 1 == step) {
                cnt++;
                Arrays.fill(visited, false);
            }

            if (step == k) {
                answer = Math.max(answer, getValue(current.number));
                continue;
            }

            for (int[] change : changes) {

                char[] changed = swap(current.number, change);

                if (changed == null) {
                    continue;
                }

                int value = getValue(changed);

                if (visited[value]) {
                    continue;
                }

                visited[value] = true;
                queue.add(new Node(changed, step + 1));
            }

        }

        System.out.println(answer);

    }

    static int getValue(char[] number) {
        int value = 0;
        for (int i = 0; i < number.length; i++) {
            value += (number[i] - '0') * Math.pow(10, number.length - i - 1);
        }

        return value;
    }

    static char[] swap(char[] number, int[] change) {

        if ((change[0] == 0 || change[1] == 0) && (number[change[0]] == '0' || number[change[1]] == '0')) {
            return null;
        }

        char[] re = number.clone();
        char tmp = re[change[1]];

        re[change[1]] = re[change[0]];
        re[change[0]] = tmp;

        return re;
    }

    static List<int[]> getChanges(int n) {
        List<int[]> changes = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                changes.add(new int[]{i, j});
            }
        }

        return changes;
    }

    static class Node {

        char[] number;
        int step;

        Node(char[] number, int step) {
            this.number = number;
            this.step = step;
        }
    }
}
