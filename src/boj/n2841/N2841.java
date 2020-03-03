package boj.n2841;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class N2841 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        List<Integer>[] melody = new ArrayList[6];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken()) - 1;
            int fret = Integer.parseInt(st.nextToken());
            if (melody[line] == null) {
                melody[line] = new ArrayList<>();
            }
            melody[line].add(fret);
        }

        int answer = 0;
        for (int i = 0; i < 6; i++) {
            if (melody[i] == null) {
                continue;
            }
            Stack<Integer> frets = new Stack<>();

            melody:
            for (int fret : melody[i]) {

                if (frets.isEmpty()) {
                    frets.add(fret);
                    answer++;
                } else {
                    while (!frets.isEmpty()) {
                        if (frets.peek() > fret) {
                            answer++;
                            frets.pop();
                        } else {
                            if (frets.peek() != fret) {
                                answer++;
                                frets.add(fret);
                            }
                            continue melody;
                        }
                    }
                    answer++;
                    frets.add(fret);
                }

            }

        }

        System.out.println(answer);
    }


}
