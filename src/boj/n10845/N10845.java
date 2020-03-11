package boj.n10845;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class N10845 {

    public static void main(String[] args) throws IOException {

        LinkedList<Integer> queue = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String query = st.nextToken();

            if (query.equals("push")) {
                queue.add(Integer.parseInt(st.nextToken()));
            } else {
                int ans = -1;
                if (query.equals("pop")) {
                    if (!queue.isEmpty()) {
                        ans = queue.poll();
                    }

                } else if (query.equals("size")) {
                    ans = queue.size();
                } else if (query.equals("empty")) {
                    if (queue.isEmpty()) {
                        ans = 1;
                    } else {
                        ans = 0;
                    }
                } else if (query.equals("front")) {
                    if (!queue.isEmpty()) {
                        ans = queue.peekFirst();
                    }
                } else if (query.equals("back")) {
                    if (!queue.isEmpty()) {
                        ans = queue.peekLast();
                    }
                }

                System.out.println(ans);

            }


        }
    }
}
